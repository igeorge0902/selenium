package pageObjects;

import org.openqa.selenium.WebDriver;

import main.BaseUrls;
import main.ElementScreenshot;
import main.TestBase;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

public class Google extends TestBase {
	public Google(WebDriver driver){
		super(driver); 
	}
	private static String Searchbox = ".//*[@id='gs_htif0']";
	
	public Google test(String input) throws Exception {

	    driver.get(BaseUrls.GOOGLE.get() + "?gws_rd=ssl");
	    
	    if(driver.getTitle().equals("Google"))
	    	{
	    Log.info("Verification Passed for Title");	 
	    	}
	    else
	    	{
	    Log.info("Verification Failed for Title"); 
	    }
	    

	    verifyEquals(driver.getTitle(), "Hello-Bello");

	    verifyTrue(driver.getTitle().equals("Google"), "Hello-Bello");
	    Log.info(driver.getTitle().equals("Google"));
	    
	    verifySuccess(driver.getTitle().equals("Google"));
	    
	    driver.findElement(By.xpath(Searchbox)).clear();    
		driver.findElement(By.xpath(Searchbox)).sendKeys(input);
	    driver.findElement(By.xpath(Searchbox)).sendKeys(Keys.ENTER);
	    
	    element = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.id("rso")));
	    isElementPresent(By.id("rso"));
	    ElementScreenshot.captureElementScreenshot(driver.findElement(By.id("rso")));
	    Log.info("ScreenShot done");
	    
	    
	    driver.findElement(By.id(Searchbox)).clear();
		driver.findElement(By.id(Searchbox)).sendKeys("Milo");
	    driver.findElement(By.id(Searchbox)).sendKeys(Keys.ENTER);
	    
		/*if (driver instanceof JavascriptExecutor) {
			((JavascriptExecutor) driver)
				.executeScript("alert('hello world');");
		}*/
			
		//getAlertConfirmation();
	    	
	    	JavascriptExecutor js = (JavascriptExecutor) driver;
	    	 
		   //// The readyState property returns the (loading) status of the current document: 'document.readyState'
		   System.out.println("Document state : "+js.executeScript("return document.readyState"));
		    
		   //// Return the domain name of the server that loaded the document: 'document.domain;'
		   System.out.println("Domain : "+js.executeScript("return document.domain"));
		    
		   //// The title property returns the title of the current document (the text inside the HTML title element): 'document.title'
		   System.out.println("Page Title : "+js.executeScript("return document.title"));
		    
		   //// The URL property returns the full URL of the current document: 'document.URL'
		   System.out.println("URL : "+js.executeScript("return document.URL"));
		    
		   //// Return the cookies associated with the current document: 'document.cookie'
		   System.out.println("Cookie : "+js.executeScript("return document.cookie"));
		    
		   //// Returns the width of a window screen: 'screen.width'
		   System.out.println("Screen Width : "+js.executeScript("return screen.width"));
		    
		   //// Return JavaScript Errors associated with the current window: 'window.jsErrors'
		   System.out.println("Windows js errors : "+js.executeScript("return window.jsErrors")); 
	        
	    	    	    	
			Reporter.log("<p>Test done</p>");
			Log.info("Test done");	    
		  
			return new Google(driver);

	}
}
