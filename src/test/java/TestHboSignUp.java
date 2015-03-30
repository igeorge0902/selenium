package test.java;



import main.ElementScreenshot;
import main.TestBase;
import org.openqa.selenium.By;
import org.testng.annotations.*;

import pageObjects.HboSignUp;
import pageObjects.HboSignUpForm;
import testng.LoggingListener;
import testng.TestListeners;
import testng.TestMethodListener;
import testng.MethodInterceptor;


@Listeners({ MethodInterceptor.class, TestListeners.class, main.CaptureScreenshotOnFailureListener.class, TestMethodListener.class, LoggingListener.class})


public class TestHboSignUp extends TestBase{
	
	  
	  public static int m_numberOfTimes;
	  public TestHboSignUp (int numberOfTimes) {
		    m_numberOfTimes = numberOfTimes;
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
				
		ElementScreenshot.captureElementScreenshot(driver.findElement(By.xpath(".//*[@id='operatorTypeNormal']/div[2]")));
				
 
	}
}

