package test.java;

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
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterMethod;
import org.testng.Reporter;
import org.apache.log4j.Logger;



@Listeners({ test.java.TestListeners.class, test.java.CaptureScreenshotOnFailureListener.class })


public class TestTest extends TestBase {
  //FirefoxProfile profile = new FirefoxProfile();
  //profile.setAssumeUntrustedCertificateIssuer(false);
	
  public static WebDriver driver;
  private static boolean acceptNextAlert = true;
  private static StringBuffer verificationErrors = new StringBuffer();
  private static Logger Log = Logger.getLogger(Logger.class.getName());


  
  public enum BaseUrls {
	  
	  GOOGLE("https://www.google.hu/"),
	  YAHOO("https://www.yahoo.com/"),
	  HBO("http://www.hbogo.hu/");
  
  private String myUrls;
  
  BaseUrls (String url) {
	  myUrls = url;
  	}
  public String get() {
	  return myUrls;
  	}
  
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
  
  
 /*
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
    
	Assert.assertEquals("Expected page title not found", "Gooogle", driver.getTitle());

    Assert.assertTrue(isElementPresent(By.id("gbqfq")));
    
    //Assert.assertSame("myValue", allOf(startsWith("my"), containsString("Val")));
    
    try{
	element = driver.findElement (By.id("gbqfq"));
	}catch (Exception e){
	}
	Assert.assertNotNull(element);
	System.out.println("Ending test " + new Object(){}.getClass().getEnclosingMethod().getName());
    
    driver.findElement(By.id("gbqfq")).clear();
    driver.findElement(By.id("gbqfq")).sendKeys("hello");
    driver.findElement(By.id("gbqfb")).sendKeys(Keys.ENTER);
    
	if (driver instanceof JavascriptExecutor) {
		((JavascriptExecutor) driver)
			.executeScript("alert('hello world');");
	}
	
	
	
	Reporter.log("Test done | ");
    
  }
  
  
  
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
    
	assertEquals("Expected page title not found", "Yahoo România", driver.getTitle());
	
	assertTrue(isElementPresent(By.id("p_13838465-p")));
    
    try{
	element = driver.findElement (By.id("p_13838465-p"));
	}catch (Exception e){
	}
	Assert.assertNotNull(element);
	System.out.println("Ending test " + new Object(){}.getClass().getEnclosingMethod().getName());
    
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
  
  @Test
  public static void testHbogoWebdriverTest1() throws Exception {
	  
	  Actions action = new Actions(driver);	  
	  
	  int m_numberOfTimes = WebDriverManager.m_numberOfTimes;
	  			for (int i = 0; i < m_numberOfTimes; i++) {
	  				// access the web page
		}
	  			
		driver.get(BaseUrls.HBO.get() + "/group/offers");
	    
	    /*
		for (int i=1; i<10; i++)
	    {
	     //To verify element is present on page or not.
	     String XPath = "//input[@id='text"+i+"']";
	     */
	     
	     Boolean title_is = driver.getTitle().equals("HBO GO. Bárhol. Bármikor.");
	     if (title_is == true)
	     {
	      Reporter.log("\nTitle"+" Is Present On The Page | ");
	     }
	     else
	     {
	      Reporter.log("\nTitle"+" Is Not Present On The Page | ");
	     }
	    	   
	    
	    verifyTrue(driver.getTitle().equals("HBO. Bárhol. Bármikor."));
	  			
    	
	    
	    for (int second = 0;; second++) {
	    	if (second >= 60) fail("timeout");
	    	try { if (isElementPresent(By.id("slide_categories"))) break; } catch (Exception e) {}
	    	Thread.sleep(1000);
	    }
	    
	    
	    
	    for (int second = 0;; second++) {
	    	if (second >= 60) fail("timeout");
	    	try { if (isElementPresent(By.xpath("(//a[contains(text(),'Sorozatok')])[2]"))) break; } catch (Exception e) {}
	    	Thread.sleep(1000);
	    }
	    
	    for (int second = 0;; second++) {
	    	if (second >= 60) fail("timeout");
	    	try { if (isElementPresent(By.xpath(".//*[@id='slide_categories']/a[2]"))) break; } catch (Exception e) {}
	    	Thread.sleep(1000);
	    }

	    //elementScreenshot.driver.findElement(By.xpath("(//a[contains(text(),'Sorozatok')])[2]"));
	    
	    driver.findElement(By.xpath("(//a[contains(text(),'Sorozatok')])[2]")).click();

	    
	    driver.findElement(By.id("selection_button")).click();
	    for (int second = 0;; second++) {
	    	if (second >= 60) fail("timeout");
	    	try { if (isElementPresent(By.xpath("(//a[@onclick='trackItem()'])[2]"))) break; } catch (Exception e) {}
	    	Thread.sleep(1000);
	    }


	    driver.findElement(By.xpath("(//a[@onclick='trackItem()'])[2]")).click();
	    for (int second = 0;; second++) {
	    	if (second >= 60) fail("timeout");
	    	try { if (isElementPresent(By.id("episode_chooser"))) break; } catch (Exception e) {}
	    	Thread.sleep(1000);
	    }

	  
	    driver.findElement(By.xpath("//html/body/div[9]/div[3]/div[1]/div[1]/div[7]/div[2]/div/div[1]/div/a[1]")).click();
	    
	    for (int second = 0;; second++) {
	    	if (second >= 60) fail("timeout");
	    	try { if (isElementPresent(By.xpath("//div[@id='content-inner']/div/div/div[2]"))) break; } catch (Exception e) {}
	    	Thread.sleep(1000);
	    }

	    for (int second = 0;; second++) {
	    	if (second >= 60) fail("timeout");
	    	try { if (isElementPresent(By.id("play-button-894d224d-00d6-43f7-92df-6ac52c4cdfc4"))) break; } catch (Exception e) {}
	    	Thread.sleep(1000);
	    }
	    	    
	    WebElement mousehover;
	    mousehover = driver.findElement(By.id("play-button-894d224d-00d6-43f7-92df-6ac52c4cdfc4"));	            
        action.moveToElement(mousehover).build().perform();        
        Log.info("Action performed");
               
        
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
        
        
        //if (driver instanceof JavascriptExecutor) {
    	//	((JavascriptExecutor) driver)
    	//		.executeScript($('#play_dropdown').find('ul ul:first li:last a').attr("onclick"));
    	//}
    	    	    	
		Reporter.log("\nTest done");
	    
	  }



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
