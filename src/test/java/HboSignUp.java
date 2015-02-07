package test.java;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class HboSignUp extends TestBase {
	
	public HboSignUp(WebDriver driver){
		super(driver); 
	}
	
	  private static String voucher = "PHRPGV";

	
	public HboSignUp selectOperator() throws Exception{
		
			driver.get(BaseUrls.HBO.get() + "/group/offers");

			driver.findElement(By.id("headerButtonLogin")).click();
		    for (int second = 0;; second++) {
		    	if (second >= 60) fail("timeout");
		    	try { if (isElementPresent(By.cssSelector("div.settings_description_white > #headerButtonLogin"))) break; } catch (Exception e) {}
		    	Thread.sleep(1000);
		    }

		    driver.findElement(By.cssSelector("div.settings_description_white > #headerButtonLogin")).click();
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
		    	try { if (isElementPresent(By.name("SpecificData"))) break; } catch (Exception e) {}
		    	Thread.sleep(1000);
		    }

		    driver.findElement(By.name("SpecificData")).clear();
		    driver.findElement(By.name("SpecificData")).sendKeys(voucher);
		    for (int second = 0;; second++) {
		    	if (second >= 60) fail("timeout");
		    	try { if (isElementPresent(By.cssSelector("button.button_submit.next"))) break; } catch (Exception e) {}
		    	Thread.sleep(1000);
		    }

		    driver.findElement(By.cssSelector("button.button_submit.next")).click();
		  

		
		
		return new HboSignUp(driver); 
	}
	
}
