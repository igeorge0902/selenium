package pageObjects;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import main.BaseUrls;
import main.ElementScreenshot;
import main.TestBase;
import main.WebElements;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
		    	try { if (isElementPresentAndDisplay(By.xpath(PlayButton))) break; } catch (Exception e) {
		    		Log.info(e.getCause());
		    	}
		    }			

		    TestBase.verifyNotNull(playPuttony);
	    	 
	    	TestBase.MouseHoverByJavaScript(By.xpath(PlayButton));

	    	
	    	//start playback
	    	TestBase.playContents();
	    		
	    	//TODO: if any error happens, go to verification error case
	    	
	    	
	    	//check title (it also will be logged by the method) --TODO: check Title presence with regex
    	    String titleName = driver.findElement(By.id(playbackTitle)).getText();
	    	Pattern title = Pattern.compile(".");
	    	//*********
	    	
	    	WaitTool.waitForTextPresent(driver, By.id(playbackTitle), "TRÓNOK HARCA", 20);	
	    	
	    	//wait for element, where second = nr. of attempts
		    for (int second = 0;; second++) {
		    	if (second >= 60) fail("timeout");
		    	try { if (isElementPresentAndDisplay(By.id(play_pause))) break; } catch (Exception e) {
		    		Log.info(e.getCause());
		    	}
		    } 	Thread.sleep(1000);
 
		    
		    //mousehover the play/pause button
	    	TestBase.MouseHoverByJavaScript(By.id(play_pause));
		    
	    	//ElementScreenshot.captureElementScreenshot(playback_Info);
	    	
	    	//pause
		    TestBase.playPause();
	    	Log.info("playback stopped");
		    
	    	
		    //mousehover the position seek dot, and drag&drop toward the given direction, which is set by int (+-)
		    TestBase.MouseHoverByJavaScript(By.id(positionsSeek));
		    action.dragAndDropBy(SeekDot, 0, 200).build().perform();
		    
		    //start
		    TestBase.playPause();
	    	Log.info("playback is running");
		    
		    //while cycle for elapsed time check
        	int second = 60;
       	
        	while(second > 0) {
        	   
        		try {
        		   
        	    	JavascriptExecutor js = (JavascriptExecutor) driver;
        	    	
				    //runs javascript to get elapsed time
				    WebElement elapsedTime;
				    elapsedTime = driver.findElement(By.id(playbackElapsedTime));				    				    
				    
				    String text = TestBase.getText(driver, elapsedTime);
				    
				    //prints out elapsed time
				    System.out.println("Elapsed time first: "+text);
	        	    Thread.sleep(333L);
	        	    
	        	    //find and replace elapsed time string value       	    
	        	    Pattern replace = Pattern.compile("\\:+");
	        	    Matcher matcher = replace.matcher(text);
	        	    System.out.println(matcher.replaceAll(""));
	        	    
				    //runs javascript to get elapsed time
				    WebElement elapsedTime2;
				    elapsedTime2 = driver.findElement(By.id(playbackElapsedTime));				    				    
				    
				    
				    //Thread goes to sleep for 5 seconds 
				    //so that the second elapsed time check will be after the first one
	        	    Thread.sleep(5000);
				    
	        	    //prints out elapsed time
				    String text2 = TestBase.getText(driver, elapsedTime);
				    System.out.println("Elapsed time second: "+text2);

	        	    //find and replace elapsed time string value       	    
	        	    Pattern replace2 = Pattern.compile("\\:+");
	        	    Matcher matcher2 = replace.matcher(text2);
	        	    System.out.println(matcher2.replaceAll(""));
	        	    
	        	    //convert replaced string value to integer
				    int elapsedtime = Integer.parseInt(matcher.replaceAll(""));
				    int elapsedtime2 = Integer.parseInt(matcher2.replaceAll(""));

				    TestBase.verifyNotSame(elapsedTime, elapsedTime2);
				    TestBase.verifyTrue(elapsedtime2>elapsedtime, "Playback stopped after 5 seconds due to unkown error!");
				    
        	   }
        	   catch(Exception e) {
        		   Log.info("Playback failed for content: "+driver.findElement(By.id(playbackTitle)).getText());
        	   }
        	   second--;
        	}
		    Reporter.log("Playback works fine");
		    Log.info("Playback works fine");
		    		    

	return new PlayMainContent(driver); 
	}
}
