package test.java;

import java.lang.reflect.Method;
import java.util.List;
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.hamcrest.CoreMatchers.*;

//import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestContext;
import org.testng.annotations.Listeners;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterMethod;
import org.testng.Reporter;
import org.apache.log4j.Logger;



@Listeners({ test.java.TestListeners.class, test.java.CaptureScreenshotOnFailureListener.class })


public class TestTest2 extends CustomVerification {
  //FirefoxProfile profile = new FirefoxProfile();
  //profile.setAssumeUntrustedCertificateIssuer(false);
	
  public static WebDriver driver;
  private static boolean acceptNextAlert = true;
  private static StringBuffer verificationErrors = new StringBuffer();
  private static Logger Log = Logger.getLogger(Logger.class.getName());

  
  public TestTest2() {
	// TODO Auto-generated constructor stub
  }

  
  
  
  @BeforeClass
  public void setUp(ITestContext context) throws Exception
  {
	  
	  // get the web driver parameters from the testng xml file
      String browser = context.getCurrentXmlTest().getParameter("browser");
      //String url         = context.getCurrentXmlTest().getParameter("url");

      driver = WebDriverManager.startDriver(browser, 40);  
      driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }
  
  public static int m_numberOfTimes;
  public TestTest2 (int numberOfTimes) {
	    m_numberOfTimes = numberOfTimes;
	}
 
  
  @Test
  	
  	public void test1() throws Exception {
    driver.get(BaseUrls.GOOGLE.get() + "?gws_rd=ssl");
    
    if(driver.getTitle().equals("Google"))
    	{
    Log.info("Verification Passed for Title");	 
    	}
    else
    	{
    Log.info("Verification Failed for Title"); 
    }
    
	//Assert.assertEquals("Google", "Googole", driver.getTitle());
	
	verifyFalse(driver.getTitle().equals("Google"));


    Assert.assertTrue(isElementPresent(By.id("gbqfq")));
    
    //Assert.assertSame("myValue", allOf(startsWith("my"), containsString("Val")));

    
    driver.findElement(By.id("gbqfq")).clear();
	driver.findElement(By.id("gbqfq")).sendKeys("hello");
    driver.findElement(By.id("gbqfb")).sendKeys(Keys.ENTER);
    
	if (driver instanceof JavascriptExecutor) {
		((JavascriptExecutor) driver)
			.executeScript("alert('hello world');");
	}
		
               
		Alert alert = driver.switchTo().alert(); alert.accept();
	
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
        
    	    	    	
		Reporter.log("\nTest done");
	    
	  }

  
  
/*
  @Test
  public void test2() throws Exception {
    driver.get(BaseUrls.YAHOO.get() + "/?p=us");
    
    if(driver.getTitle().equals("Google"))
    	{
    Log.info("Verification Passed for Title");	 
    	}
    else
    	{
    Log.info("Verification Failed for Title"); 
    }
    
	//assertEquals("Expected page title not found", "Yahoo România", driver.getTitle());
	
	assertTrue(isElementPresent(By.id("p_13838465-p")));
    
    
    driver.findElement(By.id("p_13838465-p")).clear();
    driver.findElement(By.id("p_13838465-p")).sendKeys("hello");
    driver.findElement(By.id("p_13838465-p")).sendKeys(Keys.ENTER);
    
	if (driver instanceof JavascriptExecutor) {
		((JavascriptExecutor) driver)
			.executeScript("alert('hello world');");
	}
	
	Reporter.log("Test done | ");
    
  }
  

*/

@AfterMethod

 
  
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }
  
  
  private static boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }

}
