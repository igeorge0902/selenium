package test.java;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

public class HboSignIn extends TestBase{

	public HboSignIn(WebDriver driver){
		super(driver); 
	}

	private static Logger Log = Logger.getLogger(Logger.class.getName());

		
	public HboSignIn selectOperator() throws Exception{
		
	    driver.get(BaseUrls.HBO.get() + "/group/offers");
		Log.info(BaseUrls.HBO.get() + "/group/offers");
	    
	    for (int second = 0;; second++) {
	    	if (second >= 60) fail("timeout");
	    	try { if (isElementPresent(By.id("headerButtonLogin"))) break; } catch (Exception e) {}
	    	Thread.sleep(1000);
	    }	    
	    driver.findElement(By.id("headerButtonLogin")).click();
	    
	    for (int second = 0;; second++) {
	    	if (second >= 60) fail("timeout");
	    	try { if (isElementPresent(By.id("OperatorId"))) break; } catch (Exception e) {}
	    	Thread.sleep(1000);
	    }	
	    driver.findElement(By.id("OperatorId")).click();
	    
	    for (int second = 0;; second++) {
	    	if (second >= 60) fail("timeout");
	    	try { if (isElementPresent(By.id("OperatorId_f320aa2c-e40e-49c2-8cdd-1ebef2ac6f26"))) break; } catch (Exception e) {}
	    	Thread.sleep(1000);
	    }	
	    driver.findElement(By.id("OperatorId_f320aa2c-e40e-49c2-8cdd-1ebef2ac6f26")).click();
	    
	    for (int second = 0;; second++) {
	    	if (second >= 60) fail("timeout");
	    	try { if (isElementPresent(By.name("EmailAddress"))) break; } catch (Exception e) {}
	    	Thread.sleep(1000);
	    }	
	    driver.findElement(By.name("EmailAddress")).clear();
	    driver.findElement(By.name("EmailAddress")).sendKeys("gyorgy.gaspar@mediaux.biz");
	    
	    driver.findElement(By.name("Password")).clear();	    
	    driver.findElement(By.name("Password")).sendKeys("common");
	    
	    Log.info("selectOperator is successful");
		return new HboSignIn(driver); 
	}    
	    
	public HboSignIn submit() throws Exception{
		
	    for (int second = 0;; second++) {
	    	if (second >= 60) fail("timeout");
	    	try { if (isElementPresent(By.cssSelector("#sign-in-buttons > button.button_submit"))) break; } catch (Exception e) {}
	    	Thread.sleep(1000);
	    }	
	    driver.findElement(By.cssSelector("#sign-in-buttons > button.button_submit")).click();
	    Log.info("submit is successful");
		return new HboSignIn(driver); 
	}
	    
	public boolean isNewDevice(boolean condition) {
			verifyFalse(isElementDisplayed((By.id("newDeviceInput"))));
			return condition;
	    }
	
	public HboSignIn enterNewDevice() {
		if (isNewDevice(false))
		{
			Log.info("No New Device");
		} else {
			driver.findElement(By.id("newDeviceInput")).clear();
			driver.findElement(By.id("newDeviceInput")).sendKeys("MacBook");
			driver.findElement(By.cssSelector("button.button_submit")).click();
			Log.info("New Device is named MacBook");
			Reporter.log("New Device is named MacBook");
			}	
		return new HboSignIn(driver);
		
	}
}


