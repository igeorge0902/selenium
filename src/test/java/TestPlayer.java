package test.java;


import main.TestBase;
import main.WebDriverManager;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.*;

import pageObjects.HboSignIn;
import pageObjects.PlayMainContent;
import testng.LoggingListener;
import testng.TestListeners;
import testng.TestMethodListener;
import utils.WaitTool;
 

@Listeners({TestListeners.class, main.CaptureScreenshotOnFailureListener.class, TestMethodListener.class, LoggingListener.class})


public class TestPlayer extends TestBase{
	
	  private static WebDriver driver = null;


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

	
  @Test (groups = { "signin" }, description= "HBO login" )
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
    @Test (dependsOnMethods = { "testSignInSuccess" },/*alwaysRun=true,*/ groups = { "player" }, description= "Play trailer after login")
    public void testPlayerSuccess() throws Exception{
		PlayMainContent PlayMainContent = new PlayMainContent(driver);
		
	    //playTrailer
	    PlayMainContent.playMainContent();
	    Log.info("playTrailer test is done"); 
	    Reporter.log("<p>playTrailer test is done<br></p>");
		
	}

}
