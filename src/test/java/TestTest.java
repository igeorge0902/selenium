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



@Listeners({ test.java.TestListeners.class, test.java.CaptureScreenshotOnFailureListener.class, TestMethodListener.class })


public class TestTest extends TestBase {
  //FirefoxProfile profile = new FirefoxProfile();
  //profile.setAssumeUntrustedCertificateIssuer(false);
	
  public static WebDriver driver;
  public static WebElement element;

  private static StringBuffer verificationErrors = new StringBuffer();
  private static Logger Log = Logger.getLogger(Logger.class.getName());

  
  public TestTest() {
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
  public TestTest (int numberOfTimes) {
	    m_numberOfTimes = numberOfTimes;
	}
  
  
  @Test
  public static void testHbogoWebdriverTest1() throws Exception {

	   Actions action = new Actions(driver);	  

		
		driver.get(BaseUrls.HBO.get() + "/group/offers");
	     
	    verifySuccess(driver.getTitle().equals("HBO. Bárhol. Bármikor"));
   
	    verifyEquals(driver.getTitle(), "HB. Brhol. Bármikor"); 	
	    
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
	        	
	    ElementScreenshot.captureElementScreenshot(driver.findElement(By.xpath("(//a[contains(text(),'Sorozatok')])[2]")));
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
        
        
        if (driver instanceof JavascriptExecutor) {
    		((JavascriptExecutor) driver)
    			.executeScript("PlayerManager.handlePlay('PlayContent',{$})");
    	}
    	    	    	
		Reporter.log("<p>Test done</p>");
	    
	  }



@AfterMethod

 
  
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }
  
	public WebDriver getWebDriver() {
	    return getWebDriver();
	}
  
  private static boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }


}
