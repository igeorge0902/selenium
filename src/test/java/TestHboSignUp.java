package test.java;




//import java.util.concurrent.TimeUnit;

import main.ElementScreenshot;
import main.TestBase;
import main.WebDriverManager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import pageObjects.HboSignUp;
import pageObjects.HboSignUpForm;
import testng.TestListeners;
import testng.TestMethodListener;

@Listeners({ TestListeners.class, main.CaptureScreenshotOnFailureListener.class, TestMethodListener.class})


public class TestHboSignUp extends TestBase{
	
	  public static int m_numberOfTimes;
	  public TestHboSignUp (int numberOfTimes) {
		    m_numberOfTimes = numberOfTimes;
		}
	
	
	  private static WebDriver driver;


	@BeforeClass
	  public void setUp(ITestContext context) throws Exception
	  {
		  
		  // get the web driver parameters from the testng xml file
	      String browser = context.getCurrentXmlTest().getParameter("browser");
	      //String url         = context.getCurrentXmlTest().getParameter("url");

	      driver = WebDriverManager.startDriver(browser, 40);  
	      //driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  }
	
  @Test 
  public static void testSignupFail() throws Exception{
	  for (int i = 0; i < m_numberOfTimes; i++) {
		  
		HboSignUp SignUpPage = new HboSignUp(driver); 
		HboSignUpForm SignUpForm = new HboSignUpForm(driver);
		
		//select operator
		SignUpPage.selectOperator();
		
		//enter data
		SignUpForm.fillCustomerData();
		
		//submit form
		SignUpForm.submitForm();
		
		verifyTrue(isTextPresent("<p>Az e-mail címek nem egyeznek<br></p>"), "Verify: the Valid Email error message displayed.");

		verifyTrue(isErrorMessageRequired_Check_TOS_displayed(), "ToS is checked");
				
		ElementScreenshot.captureElementScreenshot(driver.findElement(By.xpath(".//*[@id='operatorTypeNormal']/div[2]")));
		
 
	}
  }
}
