package pageObjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import main.BaseUrls;
import main.ElementScreenshot;
import main.TestBase;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import utils.WaitTool;


public class Yahoo extends TestBase {
	
	public Yahoo(WebDriver driver){
		super(driver); 
	}
	public static WebElement element;

	private static Logger Log = Logger.getLogger(Logger.class.getName());
	
	public Yahoo test(String input) throws Exception {

		driver.get(BaseUrls.YAHOO.get() + "/?p=us");
	    
	    if(driver.getTitle().equals("Yahoo"))
	    	{
	    Log.info("Verification Passed for Title");	 
	    	}
	    else
	    	{
	    Log.info("Verification Failed for Title"); 
	    }
	    	
		assertTrue(isElementPresent(By.id("p_13838465-p")));
	    
	    
	    driver.findElement(By.id("p_13838465-p")).clear();
	    driver.findElement(By.id("p_13838465-p")).sendKeys(input);
	    driver.findElement(By.id("p_13838465-p")).sendKeys(Keys.ENTER);
	    
	    //element = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.id("main")));
	    
        WaitTool.waitForElementPresent(driver, By.id("main"), 10);
	    
	    ElementScreenshot.captureElementScreenshot(driver.findElement(By.id("main")));
	    
		if (driver instanceof JavascriptExecutor) {
			((JavascriptExecutor) driver)
				.executeScript("alert('hello world');");
		}
			
	    Alert alert = driver.switchTo().alert();
		Reporter.log("<p>JavaScript text: </p>" + alert.getText() + "<br>", true);
	    alert.accept();
	    	
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
			
			
			return new Yahoo(driver);

	}

}
