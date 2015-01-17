package test.java;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import org.junit.*;
import org.openqa.selenium.WebElement;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestContext;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.Reporter;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

@Listeners(CaptureScreenshotOnFailureListener.class)



public class TestTest {
  //FirefoxProfile profile = new FirefoxProfile();
  //profile.setAssumeUntrustedCertificateIssuer(false);
  private static WebDriver driver;
  private WebElement element;
  private static String baseUrl;
  private static String baseUrl2;
  private static String baseUrl3;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  private static Logger Log = Logger.getLogger(Logger.class.getName());
/*
  @BeforeMethod
  public void setUp() throws Exception {
	DOMConfigurator.configure("log4j.xml");
    driver = new FirefoxDriver();
    
    Log.info("New driver instantiated");
    
    baseUrl = "https://www.google.hu/";
    baseUrl2 = "https://www.yahoo.com/";
    baseUrl3 = "https://www.hbogo.hu/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }*/

  @BeforeClass
  public static void setUp(ITestContext context, String FirefoxDriver) throws Exception
  {
      // get the web driver parameters from the testng xml file
      String browser = context.getCurrentXmlTest().getParameter("browser");
      String url         = context.getCurrentXmlTest().getParameter("url");

      driver = WebDriverManager.startDriver(FirefoxDriver, url, 40);
      DOMConfigurator.configure("log4j.xml");
      Log.info("New driver instantiated");
      
      baseUrl = "https://www.google.hu/";
      baseUrl2 = "https://www.yahoo.com/";
      baseUrl3 = "https://www.hbogo.hu/";
      driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }
  
  @Test
  public void test1() throws Exception {
    driver.get(baseUrl + "?gws_rd=ssl");
    
    if(driver.getTitle().equals("Google"))
    	{
    Log.info("Verification Passed for Title");	 
    	}
    else
    	{
    Log.info("Verification Failed for Title"); 
    }
    
	assertEquals("Expected page title not found", "Google", driver.getTitle());

    assertTrue(isElementPresent(By.id("gbqfq")));
    
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
	
	driver.quit();
	 
    Log.info("Browser closed");
	
	Reporter.log("Test done | ");
    
  }
  
  
  @Test
  public void test2() throws Exception {
    driver.get(baseUrl2 + "/?p=us");
    
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
	
	driver.quit();
	 
    Log.info("Browser closed");
	
	Reporter.log("Test done | ");
    
  }
  
  
  @Test
  public void testHbogoWebdriverTest1() throws Exception {
	    driver.get(baseUrl3 + "/group/offers");
	    for (int second = 0;; second++) {
	    	if (second >= 60) fail("timeout");
	    	try { if (isElementPresent(By.id("slide_categories"))) break; } catch (Exception e) {}
	    	Thread.sleep(1000);
	    }

	    try {
	      assertTrue(isElementPresent(By.xpath("(//a[contains(text(),'Sorozatok')])[2]")));
	    } catch (Error e) {
	      verificationErrors.append(e.toString());
	    }
	    for (int second = 0;; second++) {
	    	if (second >= 60) fail("timeout");
	    	try { if (isElementPresent(By.xpath(".//*[@id='slide_categories']/a[2]"))) break; } catch (Exception e) {}
	    	Thread.sleep(1000);
	    }

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

	    try {
	      assertTrue(isElementPresent(By.linkText("1. RÉSZ")));
	    } catch (Error e) {
	      verificationErrors.append(e.toString());
	    }
	    try {
	      assertTrue(isElementPresent(By.linkText("2. RÉSZ")));
	    } catch (Error e) {
	      verificationErrors.append(e.toString());
	    }
	    try {
	      assertTrue(isElementPresent(By.linkText("3. RÉSZ")));
	    } catch (Error e) {
	      verificationErrors.append(e.toString());
	    }
	    try {
	      assertTrue(isElementPresent(By.linkText("4. RÉSZ")));
	    } catch (Error e) {
	      verificationErrors.append(e.toString());
	    }
	    try {
	      assertTrue(isElementPresent(By.linkText("50. RÉSZ")));
	    } catch (Error e) {
	      verificationErrors.append(e.toString());
	    }
	    try {
	      assertTrue(isElementPresent(By.linkText("6. RÉSZ")));
	    } catch (Error e) {
	      verificationErrors.append(e.toString());
	    }
	    try {
	      assertTrue(isElementPresent(By.linkText("7. RÉSZ")));
	    } catch (Error e) {
	      verificationErrors.append(e.toString());
	    }
	    try {
	      assertTrue(isElementPresent(By.linkText("8. RÉSZ")));
	    } catch (Error e) {
	      verificationErrors.append(e.toString());
	    }
	    try {
	      assertTrue(isElementPresent(By.linkText("9. RÉSZ")));
	    } catch (Error e) {
	      verificationErrors.append(e.toString());
	    }
	    try {
	      assertTrue(isElementPresent(By.linkText("10. RÉSZ")));
	    } catch (Error e) {
	      verificationErrors.append(e.toString());
	    }
	    driver.findElement(By.linkText("1. RÉSZ")).click();
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
	    
        element = driver.findElement(By.id("play-button-894d224d-00d6-43f7-92df-6ac52c4cdfc4"));
	            
        Actions action = new Actions(driver);
        action.moveToElement(element).build().perform();
	    
        
	    try {
	      assertEquals("Előzetes lejátszása", driver.findElement(By.linkText("Előzetes lejátszása")).getText());
	    } catch (Exception e) {
	      verificationErrors.append(e.toString());
	    }
	    try {
	      assertTrue(isElementPresent(By.linkText("Előzetes lejátszása")));
	    } catch (Exception e) {
	      verificationErrors.append(e.toString());
	    }
	    
		driver.quit();
		 
	    Log.info("Browser closed");
		
		Reporter.log("Test done | ");
	    
	  }

  @AfterMethod
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
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
