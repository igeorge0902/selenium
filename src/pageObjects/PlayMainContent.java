package pageObjects;


import java.util.List;

import main.BaseUrls;
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
import utils.WaitTool;


@SuppressWarnings("unused")
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
		    	try { if (isElementPresentAndDisplay(By.id(PlayButton1))) break; } catch (Exception e) {
		    		Log.info(e.getCause());
		    	}
		    }			

		    TestBase.verifyNotNull(playPuttony);
	    	 
	    	TestBase.MouseHoverByJavaScript(By.id(PlayButton1));

	    	TestBase.SmartClick(playPuttony, 1);
	    			    
			Thread.sleep(5000);


	return new PlayMainContent(driver); 
	}
}
