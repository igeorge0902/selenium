package pageObjects;


import main.BaseUrls;
import main.TestBase;
import main.WebElements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Listeners;

import testng.TestListeners;
import testng.TestMethodListener;


@Listeners({ TestListeners.class, main.CaptureScreenshotOnFailureListener.class, TestMethodListener.class})

public class PlayMainContent extends TestBase implements WebElements{
	
	
	public PlayMainContent(WebDriver driver){
		super(driver); 
	}
	
	public PlayMainContent playMainContent() throws Exception {

		Actions action = new Actions(driver);
		
		
			driver.get(BaseUrls.PLAYER.get() + ContentUrl1);
		    
		    for (int second = 0;; second++) {
		    	if (second >= 60) fail("timeout");
		    	try { if (isElementPresent(By.id(PlayButton1))) break; } catch (Exception e) {
		    		Log.info(e);
		    	}
		    	Thread.sleep(2000);
		    }			

		    TestBase.verifyNotNull(playPuttony);
	    	Thread.sleep(2000);
	    	
	    	/*
	    	WebElement mousehover;
		    mousehover = driver.findElement(By.id(PlayButton1));	            
	        action.moveToElement(mousehover).build().perform();   
		    
	        TestBase.playContents();
	        */
	    	
	        TestBase.SmartClick(playPuttony, 3);  
		    
			Thread.sleep(5000);


	return new PlayMainContent(driver); 
	}
}
