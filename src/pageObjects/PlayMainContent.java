package pageObjects;


import main.BaseUrls;
import main.TestBase;
import main.WebElements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Listeners;

import testng.TestListeners;
import testng.TestMethodListener;


@Listeners({ TestListeners.class, main.CaptureScreenshotOnFailureListener.class, TestMethodListener.class})

public class PlayMainContent extends TestBase implements WebElements{
	
	
	public PlayMainContent(WebDriver driver){
		super(driver); 
	}

	
	public PlayMainContent playMainContent() throws Exception {

			driver.get(BaseUrls.PLAYER.get() + ContentUrl1);
		    
		    for (int second = 0;; second++) {
		    	if (second >= 60) fail("timeout");
		    	try { if (isElementPresent(By.id("play-button-07572899-1f5a-4f4e-9204-2fcc63a82775"))) break; } catch (Exception e) {}
		    	Thread.sleep(1000);
		    }			
			driver.findElement(By.id("play-button-07572899-1f5a-4f4e-9204-2fcc63a82775")).click();	            
		   	    		  
			Thread.sleep(5000);


	return new PlayMainContent(driver); 
	}
}
