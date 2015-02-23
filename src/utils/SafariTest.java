package utils;


import java.util.concurrent.TimeUnit;

import main.TestBase;
import main.WebDriverManager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import testng.LoggingListener;
import testng.TestListeners;
import testng.TestMethodListener;



@Listeners({ TestListeners.class, main.CaptureScreenshotOnFailureListener.class, TestMethodListener.class, LoggingListener.class})


public class SafariTest extends TestBase {
		
	  private static WebDriver driver = null;


	@BeforeClass
	  public void setUp(ITestContext context) throws Exception
	  {
		  
		  // get the web driver parameters from the testng xml file
	      String browser = context.getCurrentXmlTest().getParameter("browser");
	      //String url         = context.getCurrentXmlTest().getParameter("url");

	      driver = WebDriverManager.startDriver(browser, 40);  
	      driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  }
	
    @Test
    public void spawnSafari() throws Exception {
    	        
        driver.get("http://www.google.com");
        
	    for (int second = 0;; second++) {
	    	if (second >= 60) fail("timeout");
	    	try { if (isElementPresent(By.name("q"))) break; } catch (Exception e) {}
	    	Thread.sleep(1000);
	    }        
        driver.findElement(By.name("q")).sendKeys("webdriver");
        driver.findElement(By.name("btnG")).click();
        
        new WebDriverWait(driver, 3)
            .until(ExpectedConditions.titleIs("webdriver - Google keresés"));
        driver.quit();
    }
}