package test.java;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Listeners;

@Listeners({ TestListeners.class, test.java.CaptureScreenshotOnFailureListener.class, TestMethodListener.class})


public class HboSignUpForm extends TestBase {
	

	public HboSignUpForm(WebDriver driver){
		super(driver); 
	}
	  
	public HboSignUpForm fillCustomerData(){

	    driver.findElement(By.name("EmailAddress")).clear();
	    driver.findElement(By.name("EmailAddress")).sendKeys("gyorgy.gaspar@mediaux.biz");
	    driver.findElement(By.name("EmailAddressAgain")).clear();
	    driver.findElement(By.name("EmailAddressAgain")).sendKeys("gyorgy.gaspar@mediaux.bi");
	    driver.findElement(By.name("Nick")).clear();
	    driver.findElement(By.name("Nick")).sendKeys("Gyuri07");
	    driver.findElement(By.name("Password")).clear();
	    driver.findElement(By.name("Password")).sendKeys("common");
	    driver.findElement(By.name("PasswordAgain")).clear();
	    driver.findElement(By.name("PasswordAgain")).sendKeys("common");
	    driver.findElement(By.id("Newsletter_theme")).click();
	    driver.findElement(By.id("Terms_theme")).click();	
		
		return new HboSignUpForm(driver); 
	}
	
	public HboSignUpForm submitForm(){
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
