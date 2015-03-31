package test.java;

import main.BaseUrls;
import main.TestBase;

import org.testng.Reporter;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pageObjects.HboSignIn;
import pageObjects.MouseHover;

public class TestMouseHover extends TestBase{
	
    
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
	
	@Test 
    public void testMouseHover() throws Exception{
		MouseHover MouseHover = new MouseHover(driver);
		
	    //playTrailer
		MouseHover.mouseHover();

		
	}

}
