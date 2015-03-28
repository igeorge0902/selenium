package test.java;


import main.BaseUrls;
import main.TestBase;
import main.WebDriverManager;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.*;

import pageObjects.HboSignIn;
import pageObjects.PlayMainContent;
import pageObjects.SelectMovieGroups;
import testng.LoggingListener;
import testng.TestListeners;
import testng.TestMethodListener;
 

@Listeners({TestListeners.class, main.CaptureScreenshotOnFailureListener.class, TestMethodListener.class, LoggingListener.class})


public class TestPlayer extends TestBase{
	
	  private static WebDriver driver = null;


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
	
  @Parameters ({"operator"})
  @Test (groups = { "signin" }, description= "HBO login" )
  public void testSignInSuccess(String operator) throws Exception{
		HboSignIn SignInPage = new HboSignIn(driver); 
		
		//select operator
		SignInPage.selectOperator(operator);
	    Log.info("selectOperator test is done");
	    Reporter.log("<p>selectOperator test is done<br></p>");
		
		//submit
		SignInPage.submit();
		Thread.sleep(3000);
		TestBase.assertTrue(driver.getCurrentUrl().equals(BaseUrls.PLAYER.get() + OffersScreen));
	    Log.info("submit test is done");
	    Reporter.log("<p>submit test is done<br></p>");

		
		//new device dialog
		SignInPage.enterNewDevice();
	    Log.info("newDevice test is done");
	    Reporter.log("<p>newDevice test is done<br></p>");
  
  }
    
    
	@Test (/*dependsOnMethods = { "testSignInSuccess" },*/ groups = { "player" }, description= "Selecting contents for playback tests.")
	public void testSelectContentsPlay() throws Exception{
		SelectMovieGroups SelectMovieGroups = new SelectMovieGroups(driver);
		
	    //playTrailer
		SelectMovieGroups.actionGroups();
	    Log.info("selectMovieGroups test is done"); 
	    Reporter.log("<p>selectMovieGroups test is done<br></p>");
		
	}
	
    @Test (dataProviderClass=utils.SampleDataProvider.class,dataProvider="fileDataProvider", /*dependsOnMethods = { "testSelectContentsPlay" },*/ description= "Playback test of main contents")
    public void testPlayerSuccess(String urls) throws Exception{
		PlayMainContent PlayMainContent = new PlayMainContent(driver);
		
	    //playTrailer
	    PlayMainContent.playMainContent(urls);
	    Log.info("playMainContent test is done"); 
	    Reporter.log("<p>playMainContent test is done<br></p>");
		
	}

}
