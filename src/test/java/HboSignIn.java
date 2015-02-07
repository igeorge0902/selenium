package test.java;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HboSignIn extends TestBase{

	public HboSignIn(WebDriver driver){
		super(driver); 
	}

	private static Logger Log = Logger.getLogger(Logger.class.getName());

		
	public HboSignIn selectOperator(){
		
	    driver.get(BaseUrls.HBO.get() + "/group/offers");
	    driver.findElement(By.id("headerButtonLogin")).click();
	    driver.findElement(By.id("OperatorId")).click();
	    driver.findElement(By.id("OperatorId_f320aa2c-e40e-49c2-8cdd-1ebef2ac6f26")).click();
	    driver.findElement(By.name("EmailAddress")).clear();
	    driver.findElement(By.name("EmailAddress")).sendKeys("gyorgy.gaspar@mediaux.biz");
	    driver.findElement(By.name("Password")).clear();	    
	    driver.findElement(By.name("Password")).sendKeys("common");
	    Log.info("selectOperator is successful");
		return new HboSignIn(driver); 
	}    
	    
	public HboSignIn submit(){
	    driver.findElement(By.cssSelector("#sign-in-buttons > button.button_submit")).click();
	    Log.info("submit is successful");
		return new HboSignIn(driver); 
	}
	    
		public HboSignIn newDevice() throws Exception{
			try 
			
			{ if (isElementPresent(By.xpath("newDeviceInput"))); 
			
			driver.findElement(By.id("newDeviceInput")).clear();
			driver.findElement(By.id("newDeviceInput")).sendKeys("MacBook");
			driver.findElement(By.cssSelector("button.button_submit")).click();
			
			Log.info("New device added is successful");}
			
			catch (Exception e) {Log.info("no new device");}
	    	Thread.sleep(1000);
						
		return new HboSignIn(driver); 
	}
	
}