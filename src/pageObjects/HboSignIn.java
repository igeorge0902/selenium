package pageObjects;


import main.BaseUrls;
import main.TestBase;
import main.WebElements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

public class HboSignIn extends TestBase implements WebElements{
		

	public HboSignIn(WebDriver driver){
		super(driver); 
	}

	private static String password = "";
	private static String email = "";

		
	public HboSignIn selectOperator(String operator) throws Exception{
				
	    driver.get(BaseUrls.PLAYER.get() + OffersScreen);
		Log.info(BaseUrls.PLAYER.get() + OffersScreen);
		
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
	    	try { if (isElementPresent(By.id(operator))) break; } catch (Exception e) {}
	    	Thread.sleep(1000);
	    }	
	    
	    driver.findElement(By.id("OperatorId_f320aa2c-e40e-49c2-8cdd-1ebef2ac6f26")).click();
	    	    
	    for (int second = 0;; second++) {
	    	if (second >= 60) fail("timeout");
	    	try { if (isElementPresent(By.name("EmailAddress"))) break; } catch (Exception e) {}
	    	Thread.sleep(1000);
	    }	
	    driver.findElement(By.name("EmailAddress")).clear();
	    driver.findElement(By.name("EmailAddress")).sendKeys(email);
	    
	    for (int second = 0;; second++) {
	    	if (second >= 60) fail("timeout");
	    	try { if (isElementPresent(By.name("Password"))) break; } catch (Exception e) {}
	    	Thread.sleep(1000);
	    }		    
	    driver.findElement(By.name("Password")).clear();	    
	    driver.findElement(By.name("Password")).sendKeys(password);
	    
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
	
	
	public HboSignIn enterNewDevice() {
		try {
			if (isNewDeviceDialog() == false) {
				Log.info("hello");
				return new HboSignIn(driver);
				
			} else if (isNewDeviceDialog() == true) 	{
				
				driver.findElement(By.id("newDeviceInput")).clear();
				driver.findElement(By.id("newDeviceInput")).sendKeys("MacBook");
				driver.findElement(By.cssSelector("button.button_submit")).click();
				Log.info("New Device is named MacBook");
				Reporter.log("New Device is named MacBook");
			}
				} catch (Exception e) {
					e.printStackTrace();
		}
		return new HboSignIn(driver);
		
	}
}


