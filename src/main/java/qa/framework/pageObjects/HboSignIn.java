package main.java.qa.framework.pageObjects;



import main.java.qa.framework.main.BaseUrls;
import main.java.qa.framework.main.TestBase;
import main.java.qa.framework.main.WebElements;
import main.java.qa.framework.utils.PropertyUtils;
import main.java.qa.framework.utils.WaitTool;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;


public class HboSignIn extends TestBase implements WebElements{
		

	public HboSignIn(WebDriver driver){
		super(driver); 
	}

	private static String email = PropertyUtils.getProperty("email");
	private static String password = PropertyUtils.getProperty("password");

		
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
	    
	    driver.findElement(By.id(operator)).click();
	    	    
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
	
	//TODO: change it to parental
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
	
	public HboSignIn checkLanguage() {
		try {
		
			if (driver.getTitle().equals("HBO GO. It's HBO. Anywhere.")) {
				Log.info("Language is  " + EN);
			return new HboSignIn(driver);
			
			} else if (!driver.getTitle().equals("HBO GO. It's HBO. Anywhere.")) {
				
				driver.findElement(By.id(HeaderButton)).click();
				TestBase.isElementPresent(By.id(Movies));
				driver.findElement(By.id(Settings)).click();
				TestBase.assertEquals(driver.findElement(By.id(CustomerMenu)).isDisplayed(), driver.findElement(By.id(CustomerMenu)).isDisplayed());
				TestBase.clickLinkByHref(language);
				
				driver.findElement(By.linkText("English")).click();
				
				driver.findElement(By.cssSelector("button.button_submit")).click();

				WaitTool.waitForElementRefresh(driver, By.cssSelector("button.button_submit"), 10);
				TestBase.assertTrue(TestBase.isTextPresent(EN));
				Log.info("Language has been changed to" + EN +"!");
		}
			} catch (Exception e) {
			
		}
		return new HboSignIn(driver);
	}
}
		


