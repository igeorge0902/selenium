package test.java;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({ TestListeners.class, test.java.CaptureScreenshotOnFailureListener.class, TestMethodListener.class})


public class TestHboSignIn extends TestBase{
	
	  private static WebDriver driver;
	  private static Logger Log = Logger.getLogger(Logger.class.getName());



	@BeforeClass
	  public void setUp(ITestContext context) throws Exception
	  {
		  
		  // get the web driver parameters from the testng xml file
	      String browser = context.getCurrentXmlTest().getParameter("browser");
	      //String url         = context.getCurrentXmlTest().getParameter("url");

	      driver = WebDriverManager.startDriver(browser, 40);  
	      driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  }
	
  @Test
  public void testSignInSuccess() throws Exception{
		HboSignIn SignInPage = new HboSignIn(driver); 
		//PlayTrailer PlayTrailer = new PlayTrailer(driver);
		
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

	    /*
	    //playTrailer
	    PlayTrailer.playTrailer();
	    Log.info("playTrailer test is done"); 
	    Reporter.log("<p>playTrailer test is done<br></p>");
		*/
	}
}
