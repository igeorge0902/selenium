package test.java;


import main.TestBase;
import main.WebDriverManager;

import org.openqa.selenium.WebDriver;
//import org.apache.log4j.Logger;
//import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import pageObjects.Google;
import pageObjects.Yahoo;
import testng.TestListeners;
import testng.TestMethodListener;
//import utils.WaitTool;

@Listeners({ TestListeners.class, main.CaptureScreenshotOnFailureListener.class, TestMethodListener.class})

public class TestSearchEngines extends TestBase{
		  
	private WebDriver driver = null;
	  

	@BeforeClass
	  public void setUp(ITestContext context) throws Exception
	  {
		  
		  // get the web driver parameters from the testng xml file
	      String browser = context.getCurrentXmlTest().getParameter("browser");
	      String url = context.getCurrentXmlTest().getParameter("url");

          driver = WebDriverManager.startDriver(browser, url, 30);
	      //WaitTool.setImplicitWait(driver, 30);
	  }

	
  @Test (groups = { "functional_test" }, dataProviderClass=utils.SampleDataProvider.class,dataProvider="getColors")
  public void Yahoo(String input) throws Exception {
	  Yahoo YahooSearch = new Yahoo(driver);
	  
	  YahooSearch.test(input);
  }
  
  @Test (groups = { "functional_test" }, dataProviderClass=utils.SampleDataProvider.class,dataProvider="getColors")
  public void Google(String input) throws Exception {
	  Google GoogleSearch = new Google(driver);
	  
	  GoogleSearch.test(input);
  }
}
