package test.java;


import main.TestBase;
import main.WebDriverManager;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.*;

import pageObjects.Google;
import pageObjects.Yahoo;
import testng.TestListeners;
import testng.TestMethodListener;

@Listeners({TestListeners.class, main.CaptureScreenshotOnFailureListener.class, TestMethodListener.class})

public class TestSearchEngines extends TestBase{
		  
	private WebDriver driver = null;
	  

	@BeforeClass
	  public void setUp(ITestContext context) throws Exception {
		
		  try {			  
		  // get the web driver parameters from the testng xml file
	      String browser = context.getCurrentXmlTest().getParameter("browser");
	      String url = context.getCurrentXmlTest().getParameter("url");
	      
	      driver = WebDriverManager.startDriver(browser, url, 40); 
	      TestBase.verifyNotNull(driver, "Driver setUp failed!");

		  } catch (Exception e) {
			
			  Log.info(e);
			  Log.info("Safari is reconnecting!");
			  // get the web driver parameters from the testng xml file
		      String browser = context.getCurrentXmlTest().getParameter("browser");
		      String url = context.getCurrentXmlTest().getParameter("url");
		      
		      driver = WebDriverManager.startDriver(browser, url, 40); 
		      TestBase.verifyNotNull(driver, "Driver setUp failed!");
		  }
		  
	}
		
	@AfterClass
	private void closeBrowser(ITestContext context) {
		WebDriverManager.stopDriver();
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
