package test.java;

import main.BaseUrls;
import main.TestBase;

import org.testng.Reporter;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pageObjects.HboSignIn;
import pageObjects.PlaySingleMainContent;

public class TestSingleContent extends TestBase {
	
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
	  
	  public static int m_numberOfTimes;
	  public TestSingleContent (int numberOfTimes) {
		    m_numberOfTimes = numberOfTimes;
		}
	
	@Parameters ({"contents"})
  	@Test (dependsOnMethods = {"testSignInSuccess" })
    public void testPlaySingleContent(String contents) throws Exception {
  		
  		for (int i = 0; i < m_numberOfTimes; i++) {
  		
  			PlaySingleMainContent PlayMainContent = new PlaySingleMainContent(driver);
  		
  			PlayMainContent.playMainContent(contents);
  		
  		}

  	}
}
