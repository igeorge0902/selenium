package test.java;

import java.util.List;

import main.BaseUrls;
import main.ElementScreenshot;
import main.TestBase;
import main.WebDriverManager;
import main.WebElements;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.testng.ITestContext;
import org.testng.annotations.*;
import org.testng.Reporter;

import testng.TestListeners;
import testng.TestMethodListener;
import utils.WaitTool;



@Listeners({ TestListeners.class, main.CaptureScreenshotOnFailureListener.class, TestMethodListener.class})


public class TestTest extends TestBase implements WebElements{
	
  private static WebDriver driver;
  //private static WebElement element;

  
  public TestTest() {
	// TODO Auto-generated constructor stub
  }
  
  
  @BeforeClass
  public void setUp(ITestContext context) throws Exception
  {
	  
	  // get the web driver parameters from the testng xml file
      String browser = context.getCurrentXmlTest().getParameter("browser");
      String url = context.getCurrentXmlTest().getParameter("url");

      driver = WebDriverManager.startDriver(browser, url, 40);  
      WaitTool.setImplicitWait(driver, 30);
  }
	
	@AfterClass
	private void closeBrowser(ITestContext context) {
		WebDriverManager.stopDriver();
	}
	
  
  @Test
  public void testHbogoWebdriverTest1() throws Exception { 

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
    	
    	List<WebElement> playbuttonmenu;
        playbuttonmenu = driver.findElement(By.id("play_dropdown")).findElements(By.tagName("a")); 
                
        for(int i =0; i<playbuttonmenu.size();i++)    
        {
            String onClick = playbuttonmenu.get(i).getAttribute("onclick");
            
            for(int j=0; j<playTrailer.length;j++)
            {
                if(onClick.contains(playTrailer[j]))
                {
                    js.executeScript(onClick);
                
                }
            }
            
        }
        
            	
            	       
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
         	
       driver.quit();
        
		Reporter.log("<p>Test done</p>");
		Log.info("Test done");
	    
	  }


}
