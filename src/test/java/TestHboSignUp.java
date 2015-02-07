package test.java;




import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({ TestListeners.class, test.java.CaptureScreenshotOnFailureListener.class, TestMethodListener.class})


public class TestHboSignUp extends TestBase{
	
	  private static WebDriver driver;


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
  public void testSignupFail(){
		HboSignUp SignUpPage = new HboSignUp(driver); 
		HboSignUpForm SignUpForm = new HboSignUpForm(driver);
		//select operator
		SignUpPage.selectOperator();
		//enter data
		SignUpForm.fillCustomerData();
		
		SignUpForm.submitForm();
		
		verifyTrue(isTextPresent("Az e-mail címek nem egyeznek"), "Verify: the Valid Email error message displayed.");
		
		
 
	}
}
