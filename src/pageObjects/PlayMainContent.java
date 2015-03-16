package pageObjects;


import main.BaseUrls;
import main.ElementScreenshot;
import main.TestBase;
import main.WebElements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Reporter;
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

	    	//start playback
	    	TestBase.playContents();
	    		    		
	    	//check title (it also will be logged by the method)
	    	WaitTool.waitForTextPresent(driver, By.id(playbackTitle), "THE DOG AND THE CAT: FROM HERE TO THERE", 20);	

		    for (int second = 0;; second++) {
		    	if (second >= 60) fail("timeout");
		    	try { if (isElementPresentAndDisplay(By.id(play_pause))) break; } catch (Exception e) {
		    		Log.info(e.getCause());
		    	}
		    }
		    
		    //mousehover the play/pause button
	    	TestBase.MouseHoverByJavaScript(By.id(play_pause));
		    
	    	ElementScreenshot.captureElementScreenshot(playback_Info);
	    	
	    	//pause
		    TestBase.playPause();
	    	
		    //mousehover the position seek dot, and drag&drop toward the given direction, which is set by int (+-)
		    TestBase.MouseHoverByJavaScript(By.id(positionsSeek));
		    action.dragAndDropBy(SeekDot, 0, 200).build().perform();
		    
		    //for cycle for elapsed time check
		    for (int second = 0;; second++) {
		    	if (second >= 60) Thread.sleep(1000);
		    	
		    	 if (isElementPresent(By.id(playbackElapsedTime))) {
		    		
				    String elapsedtime;
				    elapsedtime = driver.findElement(By.id(playbackElapsedTime)).getText();
				    System.out.println(elapsedtime);
				    Reporter.log("Playback works fine");
				    Log.info("Playback works fine");
		    	} break; 
		    }		    

	return new PlayMainContent(driver); 
	}
}
