package test.java;


import main.TestBase;
import main.WebDriverManager;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.*;

import pageObjects.HboSignIn;
import pageObjects.PlayTrailer;
import testng.LoggingListener;
import testng.TestListeners;
import testng.TestMethodListener;
import utils.WaitTool;
 

@Listeners({TestListeners.class, main.CaptureScreenshotOnFailureListener.class, TestMethodListener.class, LoggingListener.class})


public class TestHboSignIn extends TestBase{
	
	  private static WebDriver driver = null;
	  private static Logger Log = Logger.getLogger(Logger.class.getName());


	@BeforeClass
	  public void setUp(ITestContext context) throws Exception
	  {
		  
		  // get the web driver parameters from the testng xml file
	      String browser = context.getCurrentXmlTest().getParameter("browser");
	      String url = context.getCurrentXmlTest().getParameter("url");

	      driver = WebDriverManager.startDriver(browser, url, 40);  
	      WaitTool.setImplicitWait(driver, 30);
	  }
		
	@AfterClass
	private void closeBrowser(ITestContext context) {
		WebDriverManager.stopDriver();
	}

	
  @Test (groups = { "functional_test1" }, description= "HBO login" )
  public void testSignInSuccess() throws Exception{
		HboSignIn SignInPage = new HboSignIn(driver); 
		
		//select operator
		SignInPage.selectOperator();
	    Log.info("selectOperator test is done");
	    Reporter.log("<p>selectOperator test is done<br></p>");
		
		//submit
		SignInPage.submit();
	    Log.info("submit test is done");
	    Reporter.log("<p>submit test is done<br></p>");

		
		//new device dialog
		SignInPage.enterNewDevice();
	    Log.info("newDevice test is done");
	    Reporter.log("<p>newDevice test is done<br></p>");
  
  }
    @Test (dependsOnMethods = { "testSignInSuccess" },/*alwaysRun=true,*/ groups = { "functional_test2" }, description= "Play trailer after login")
    public void testPlayerSuccess() throws Exception{
		PlayTrailer PlayTrailer = new PlayTrailer(driver);
		
	    //playTrailer
	    PlayTrailer.playTrailer();
	    Log.info("playTrailer test is done"); 
	    Reporter.log("<p>playTrailer test is done<br></p>");
		
	}

}
