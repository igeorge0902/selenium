package pageObjects;


import main.TestBase;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Listeners;

import testng.TestListeners;
import testng.TestMethodListener;

@Listeners({ TestListeners.class, main.CaptureScreenshotOnFailureListener.class, TestMethodListener.class})


public class HboSignUpForm extends TestBase {
	

	public HboSignUpForm(WebDriver driver){
		super(driver); 
	}
	  
	public HboSignUpForm fillCustomerData() throws Exception{

	    for (int second = 0;; second++) {
	    	if (second >= 60) fail("timeout");
	    	try { if (isElementPresent(By.name("EmailAddress"))) break; } catch (Exception e) {}
	    	Thread.sleep(1000);
	    }	    
		driver.findElement(By.name("EmailAddress")).clear();
	    driver.findElement(By.name("EmailAddress")).sendKeys("gyorgy.gaspar@mediaux.biz");
	    
	    for (int second = 0;; second++) {
	    	if (second >= 60) fail("timeout");
	    	try { if (isElementPresent(By.name("EmailAddressAgain"))) break; } catch (Exception e) {}
	    	Thread.sleep(1000);
	    }	    
	    driver.findElement(By.name("EmailAddressAgain")).clear();
	    driver.findElement(By.name("EmailAddressAgain")).sendKeys("gyorgy.gaspar@mediaux.bi");
	    
	    for (int second = 0;; second++) {
	    	if (second >= 60) fail("timeout");
	    	try { if (isElementPresent(By.name("Nick"))) break; } catch (Exception e) {}
	    	Thread.sleep(1000);
	    }	    
	    driver.findElement(By.name("Nick")).clear();
	    driver.findElement(By.name("Nick")).sendKeys("Gyuri07");
	    
	    for (int second = 0;; second++) {
	    	if (second >= 60) fail("timeout");
	    	try { if (isElementPresent(By.name("Password"))) break; } catch (Exception e) {}
	    	Thread.sleep(1000);
	    }	    
	    driver.findElement(By.name("Password")).clear();
	    driver.findElement(By.name("Password")).sendKeys("common");
	    
	    for (int second = 0;; second++) {
	    	if (second >= 60) fail("timeout");
	    	try { if (isElementPresent(By.name("PasswordAgain"))) break; } catch (Exception e) {}
	    	Thread.sleep(1000);
	    }	    
	    driver.findElement(By.name("PasswordAgain")).clear();
	    driver.findElement(By.name("PasswordAgain")).sendKeys("common");
	    
	    for (int second = 0;; second++) {
	    	if (second >= 60) fail("timeout");
	    	try { if (isElementPresent(By.id("Newsletter_theme"))) break; } catch (Exception e) {}
	    	Thread.sleep(1000);
	    }  
	    driver.findElement(By.id("Newsletter_theme")).click();
	    
	    for (int second = 0;; second++) {
	    	if (second >= 60) fail("timeout");
	    	try { if (isElementPresent(By.id("Terms_theme"))) break; } catch (Exception e) {}
	    	Thread.sleep(1000);
	    }    
	    driver.findElement(By.id("Terms_theme")).click();	
		
		return new HboSignUpForm(driver); 
	}
	
	public HboSignUpForm submitForm() throws Exception{	
	    driver.findElement(By.cssSelector("button.button_submit.submit")).click();
		return new HboSignUpForm(driver); 
	}
	
	public HboSignUpForm newDevice(){
	    driver.findElement(By.id("newDeviceInput")).clear();
	    driver.findElement(By.id("newDeviceInput")).sendKeys("MacBook");
	    driver.findElement(By.cssSelector("button.button_submit")).click();
		return new HboSignUpForm(driver); 
	}
	
}
