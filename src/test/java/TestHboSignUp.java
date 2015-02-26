package test.java;



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
import testng.LoggingListener;
import testng.TestListeners;
import testng.TestMethodListener;
import utils.WaitTool;

@Listeners({ TestListeners.class, main.CaptureScreenshotOnFailureListener.class, TestMethodListener.class, LoggingListener.class})


public class TestHboSignUp extends TestBase{
	
	  
	  public static int m_numberOfTimes;
	  public TestHboSignUp (int numberOfTimes) {
		    m_numberOfTimes = numberOfTimes;
		}
	  
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
	
  @Test 
  public static void testSignupFail() throws Exception{
		  
		HboSignUp SignUpPage = new HboSignUp(driver); 
		HboSignUpForm SignUpForm = new HboSignUpForm(driver);
		
		//select operator
		SignUpPage.selectOperator();
		
		//enter data
		SignUpForm.fillCustomerData();
		
		//submit form
		SignUpForm.submitForm();
		
		verifyTrue(isTextPresent("<p>Az e-mail címek nem egyeznek<br></p>"), "Verify: the Valid Email error message displayed.<br>");

		verifyFalse(isErrorMessageRequired_Check_TOS_displayed(), "ToS is not checked<br>");
				
		ElementScreenshot.captureElementScreenshot(driver.findElement(By.xpath(".//*[@id='operatorTypeNormal']/div[2]")));
				
 
	}
}

