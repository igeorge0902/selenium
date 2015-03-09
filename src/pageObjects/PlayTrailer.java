package pageObjects;


import java.util.List;

import main.BaseUrls;
import main.ElementScreenshot;
import main.TestBase;
import main.WebElements;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Listeners;

import testng.TestListeners;
import testng.TestMethodListener;


@SuppressWarnings("unused")
@Listeners({ TestListeners.class, main.CaptureScreenshotOnFailureListener.class, TestMethodListener.class})

public class PlayTrailer extends TestBase implements WebElements{
	
	
	public PlayTrailer(WebDriver driver){
		super(driver); 
	}

	
	public PlayTrailer playTrailer() throws Exception {

			driver.get(BaseUrls.HBO.get() + "/group/offers");
		     
		    verifySuccess(driver.getTitle().equals("HBO GO. Bárhol. Bármikor."));
	   		    
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
	  
		    for (int second = 0;; second++) {
		    	if (second >= 60) fail("timeout");
		    	try { if (isElementPresent(By.xpath("//div[2]/div/div/div/a"))) break; } catch (Exception e) {}
		    	Thread.sleep(1000);
		    }
		    driver.findElement(By.xpath("//div[2]/div/div/div/a")).click();
		    
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
		    		    
	        TestBase.MouseHoverByJavaScript(By.id("play-button-894d224d-00d6-43f7-92df-6ac52c4cdfc4"));
	        TestBase.playTrailers();
	        
	       
	return new PlayTrailer(driver); 
	}
}
