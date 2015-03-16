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
import pageObjects.SelectMovieGroups;
import testng.LoggingListener;
import testng.TestListeners;
import testng.TestMethodListener;
 

@Listeners({TestListeners.class, main.CaptureScreenshotOnFailureListener.class, TestMethodListener.class, LoggingListener.class})


public class TestHboSignIn extends TestBase{
	
	  private static WebDriver driver = null;
	  private static Logger Log = Logger.getLogger(Logger.class.getName());

	@BeforeClass
	  public void setUp(ITestContext context) throws Exception {
		
		  try {			  
		  // get the web driver parameters from the testng xml file
	      String browser = context.getCurrentXmlTest().getParameter("browser");
	      String url = context.getCurrentXmlTest().getParameter("url");
	      
	      driver = WebDriverManager.startDriver(browser, url, 40); 
	      TestBase.verifyNotNull(driver, "Driver setUp failed!");
	      Log.info("Before setUp is SUCCESS!");

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

  @Parameters ({"operator"})
  @Test (groups = { "functional_test1" }, description= "HBO login" )
  public void testSignInSuccess(String operator) throws Exception{
		HboSignIn SignInPage = new HboSignIn(driver); 
		
		//select operator
		SignInPage.selectOperator(operator);
	    Log.info("selectOperator test is done");
	    Reporter.log("<p>selectOperator test is done<br></p>");
		
		//submit
		SignInPage.submit();
	    Log.info("submit test is done");
	    Reporter.log("<p>submit test is done<br></p>");

		
		//new device dialog
		SignInPage.enterNewDevice();
		Thread.sleep(3);
	    Log.info("newDevice test is done");
	    Reporter.log("<p>newDevice test is done<br></p>");
  
  }
  	
    @Test //(dataProviderClass=utils.SampleDataProvider.class,dataProvider="contenturls")
    public void testListActionGroupContents() throws Exception{
    	SelectMovieGroups ActionGroup = new SelectMovieGroups(driver);
    	
    	ActionGroup.actionGroups();
	    Log.info("testListActionGroupContents test is done"); 
	    Reporter.log("<p>testListActionGroupContents test is done<br></p>");
    	
    }
  
    @Test (/*dependsOnMethods = { "testSignInSuccess" }, alwaysRun=true,*/ groups = { "functional_test2" }, description= "Play trailer after login")
    public void testPlayerSuccess() throws Exception{
		PlayTrailer PlayTrailer = new PlayTrailer(driver);
		
	    //playTrailer
	    PlayTrailer.playTrailer();
	    Log.info("playTrailer test is done"); 
	    Reporter.log("<p>playTrailer test is done<br></p>");
		
	}

}
