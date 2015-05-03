package test.java;


import main.java.qa.framework.main.BaseUrls;
import main.java.qa.framework.main.TestBase;
import main.java.qa.framework.pageObjects.HboSignIn;
import main.java.qa.framework.pageObjects.PlayMainContent;
import main.java.qa.framework.pageObjects.SelectMovieGroups;
import main.java.qa.framework.testng.LoggingListener;
import main.java.qa.framework.testng.TestListeners;
import main.java.qa.framework.testng.TestMethodListener;

import org.testng.Reporter;
import org.testng.annotations.*;
 

@Listeners({TestListeners.class, main.java.qa.framework.main.CaptureScreenshotOnFailureListener.class, TestMethodListener.class, LoggingListener.class})


public class TestPlayer extends TestBase{
	
	
  @Parameters ({"operator"})
  @Test (description= "HBO login" )
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

	    //language check
	    SignInPage.checkLanguage();
	    Log.info("language check test is done");
	    Reporter.log("<p>language check test is done<br></p>");
  }
     
  
	@Test (dependsOnMethods = { "testSignInSuccess" }, description= "Selecting contents for playback tests.")
	public void testSelectContentsPlay() throws Exception{
		SelectMovieGroups SelectMovieGroups = new SelectMovieGroups(driver);
		
	    //select contents
		SelectMovieGroups.actionGroups();
	    Log.info("selectMovieGroups test is done"); 
	    Reporter.log("<p>selectMovieGroups test is done<br></p>");
		
	}
	
    @Test (dataProviderClass=main.java.qa.framework.utils.SampleDataProvider.class,dataProvider="fileDataProvider", dependsOnMethods = { "testSelectContentsPlay" }, description= "Playback test of main contents")
    public void testPlayerSuccess(String urls) throws Exception{
		PlayMainContent PlayMainContent = new PlayMainContent(driver);
		
	    //play mainContent
	    PlayMainContent.playMainContent(urls);
	    Log.info("playMainContent test is done"); 
	    Reporter.log("<p>playMainContent test is done<br></p>");
		
	}

}
