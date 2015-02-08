package test.java;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import static org.hamcrest.CoreMatchers.*;
//import org.openqa.selenium.interactions.Actions;
//import org.openqa.selenium.support.ui.Select;
//import org.openqa.selenium.support.PageFactory;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.Reporter;
import org.apache.log4j.Logger;



@Listeners({ TestListeners.class, test.java.CaptureScreenshotOnFailureListener.class, TestMethodListener.class, /*CustomTestListener.class*/})


public class TestTest2 extends TestBase {
  public static WebDriver driver;
  public static WebElement element;
  private static Logger Log = Logger.getLogger(Logger.class.getName());
  public TestTest2() {
  }
    
  @BeforeClass
  public void setUp(ITestContext context) throws Exception
  {
	  
	  // get the web driver parameters from the testng xml file
      String browser = context.getCurrentXmlTest().getParameter("browser");
      //String url         = context.getCurrentXmlTest().getParameter("url");

      driver = WebDriverManager.startDriver(browser, 40);  
      driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
      System.out.println(driver);
      
  }
  
  public static int m_numberOfTimes;
  public TestTest2 (int numberOfTimes) {
	    m_numberOfTimes = numberOfTimes;
	}
 
  
  @Test (groups = { "functional_test" }/*(dataProviderClass=SampleDataProvider.class,dataProvider="getColors")*/)
  	
  	public void test1(/*String input*/) throws Exception {

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
    
    driver.findElement(By.id("gbqfq")).clear();    
	driver.findElement(By.id("gbqfq")).sendKeys("Hello");
    driver.findElement(By.id("gbqfb")).sendKeys(Keys.ENTER);
    
    element = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.id("rso")));
    isElementPresent(By.id("rso"));
    ElementScreenshot.captureElementScreenshot(driver.findElement(By.id("rso")));
    Log.info("ScreenShot done");
    
    
    driver.findElement(By.id("gbqfq")).clear();
	driver.findElement(By.id("gbqfq")).sendKeys("Milo");
    driver.findElement(By.id("gbqfb")).sendKeys(Keys.ENTER);
    
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

	    
	  }
 

  @Test (groups = { "functional_test" }/*(dataProviderClass=SampleDataProvider.class,dataProvider="getColors")*/)
  public void test2(/*String input*/) throws Exception {
    driver.get(BaseUrls.YAHOO.get() + "/?p=us");
    
    if(driver.getTitle().equals("Google"))
    	{
    Log.info("Verification Passed for Title");	 
    	}
    else
    	{
    Log.info("Verification Failed for Title"); 
    }
    	
	assertTrue(isElementPresent(By.id("p_13838465-p")));
    
    
    driver.findElement(By.id("p_13838465-p")).clear();
    driver.findElement(By.id("p_13838465-p")).sendKeys("Hello");
    driver.findElement(By.id("p_13838465-p")).sendKeys(Keys.ENTER);
    
    element = (new WebDriverWait(driver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.id("main")));
    
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

    
  }


}
