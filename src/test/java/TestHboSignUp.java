package test.java;



import main.java.qa.framework.main.BaseUrls;
import main.java.qa.framework.main.ElementScreenshot;
import main.java.qa.framework.main.TestBase;
import main.java.qa.framework.pageObjects.HboSignUp;
import main.java.qa.framework.pageObjects.HboSignUpForm;
import main.java.qa.framework.testng.LoggingListener;
import main.java.qa.framework.testng.MethodInterceptor;
import main.java.qa.framework.testng.TestListeners;
import main.java.qa.framework.testng.TestMethodListener;

import org.openqa.selenium.By;
import org.testng.annotations.*;


@Listeners({ MethodInterceptor.class, TestListeners.class, main.java.qa.framework.main.CaptureScreenshotOnFailureListener.class, TestMethodListener.class, LoggingListener.class})


public class TestHboSignUp extends TestBase{
	
	  
	  public static int m_numberOfTimes;
	  public TestHboSignUp (int numberOfTimes) {
		    m_numberOfTimes = numberOfTimes;
		}
  @Parameters ({"operator"})	  
  @Test 
  public static void testSignupFail(String operator) throws Exception{
		  
		HboSignUp SignUpPage = new HboSignUp(driver); 
		HboSignUpForm SignUpForm = new HboSignUpForm(driver);
		
		//select operator
		SignUpPage.selectOperator(operator);
		
		//enter data
		SignUpForm.fillCustomerData();
		
		//submit form
		SignUpForm.submitForm();
		
		verifyTrue(isTextPresent("<p>Az e-mail címek nem egyeznek<br></p>"), "Verify: the Valid Email error message displayed.<br>");
				
		ElementScreenshot.captureElementScreenshot(driver.findElement(By.xpath(".//*[@id='operatorTypeNormal']/div[2]")));
				
 
	}
  
  @Parameters ({"operator"})	  
  @Test 
  public static void testSignupSuccess(String operator) throws Exception{
		  
		HboSignUp SignUpPage = new HboSignUp(driver); 
		HboSignUpForm SignUpForm = new HboSignUpForm(driver);
		
		//select operator
		SignUpPage.selectOperator(operator);
		
		//enter data
		SignUpForm.fillCustomerData();
		
		//submit form
		SignUpForm.submitForm();
		TestBase.assertEquals(driver.getCurrentUrl(),(BaseUrls.PLAYER.get() + OffersScreen));
 
	}
}

