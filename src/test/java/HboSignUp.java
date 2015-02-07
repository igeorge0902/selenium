package test.java;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class HboSignUp extends TestBase {
	
	public HboSignUp(WebDriver driver){
		super(driver); 
	}
	
	  private static String voucher = "PHRPGV";

	
	public HboSignUp selectOperator(){
		
			driver.get(BaseUrls.HBO.get() + "/group/offers");
		    driver.findElement(By.id("headerButtonLogin")).click();
		    driver.findElement(By.cssSelector("div.settings_description_white > #headerButtonLogin")).click();
		    driver.findElement(By.id("OperatorId")).click();
		    driver.findElement(By.id("OperatorId_f320aa2c-e40e-49c2-8cdd-1ebef2ac6f26")).click();
		    driver.findElement(By.name("SpecificData")).clear();
		    driver.findElement(By.name("SpecificData")).sendKeys(voucher);
		    driver.findElement(By.cssSelector("button.button_submit.next")).click();
		
		
		return new HboSignUp(driver); 
	}
	
}
