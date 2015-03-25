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
import main.CaptureScreenshotOnFailureListener;


@SuppressWarnings("unused")
@Listeners({ TestListeners.class, main.CaptureScreenshotOnFailureListener.class, TestMethodListener.class})

public class PlayMainContent extends TestBase implements WebElements{
	
	
	public PlayMainContent(WebDriver driver){
		super(driver); 
	}
	
	public PlayMainContent playMainContent() throws Exception {

		Actions action = new Actions(driver);
			
			//driver.get(BaseUrls.PLAYER.get() + ContentUrl1);
		    
		    for (int second = 0;; second++) {
		    	if (second >= 60) fail("timeout");
		    	try { if (isElementPresentAndDisplay(By.xpath(PlayButton))) break; } catch (Exception e) {
		    		Log.info(e.getCause());
		    	} Thread.sleep(1000);
		    }			

		    TestBase.verifyNotNull(playPuttony);
	    	 
	    	TestBase.MouseHoverByJavaScript(By.xpath(PlayButton));

	    	
	    	//start playback
	    	TestBase.playContents();
	    		
	    	//TODO: parental
	    	
	    	//TODO: if any error happens, go to verification error case
	    	try {
	    	
    	    String titleName = driver.findElement(By.id(playbackTitle)).getText();
    	   
    	    //wait for title presence with regex
	    	Pattern title = Pattern.compile("(.)");
    	    Matcher matchertitle = title.matcher(titleName);
    	    if (matchertitle.find()) {
    	    	
    	    	String theGroup = matchertitle.group(1);
    	    	
    	    	String titletext = String.format("%s", theGroup);
    	    	WaitTool.waitForTextPresent(driver, By.id(playbackTitle), titletext, 20);	

    	    }
	    		    	
	    	//wait for element, where second = nr. of attempts
		    for (int second = 0;; second++) {
		    	if (second >= 60) fail("timeout");
		    	try { if (isElementPresentAndDisplay(By.id(play_pause))) break; } catch (Exception e) {
		    		Log.info(e.getCause());
		    	}
		    } 	Thread.sleep(1000);
 
		    
		    //mousehover the play/pause button
	    	TestBase.MouseHoverByJavaScript(By.id(play_pause));
		        	
	    	
	    	//pause
		    TestBase.playPause();
	    	Log.info("playback stopped");
	    	
	    	//TODO: player controller tests (volume, audio, subs track, fullscreen, exit fullscreen, GUI goes away after 3 seconds)
		    
	    	//TODO: if there is next or previous episode, it could be tested here
	    	
		    //mousehover the position seek dot, and drag&drop toward the given direction, which is set by int (+-)
	    	//TODO: seek to the beginning of the content
		    TestBase.MouseHoverByJavaScript(By.id(positionsSeek));
		    action.dragAndDropBy(SeekDot, 0, -1100).build().perform();
		    
		    //start
		    TestBase.playPause();
	    	Log.info("playback is running");
		    
		    //while cycle for elapsed time check
        	int second = 30;
       	
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
				    String text2 = TestBase.getText(driver, elapsedTime2);
				    System.out.println("Elapsed time second: "+text2);

	        	    //find and replace elapsed time string value       	    
	        	    Pattern replace2 = Pattern.compile("\\:+");
	        	    Matcher matcher2 = replace.matcher(text2);
	        	    System.out.println(matcher2.replaceAll(""));
	        	    
	        	    //convert replaced string value to integer
				    int elapsedtime = Integer.parseInt(matcher.replaceAll(""));
				    int elapsedtime2 = Integer.parseInt(matcher2.replaceAll(""));

				    TestBase.verifyNotSame(elapsedTime, elapsedTime2);
				    
				    //verify should work the way that if playback fails, next content will be loaded to avoid loop for playback fails
				    TestBase.verifyTrue(elapsedtime2>elapsedtime, "Playback is working fine!");
				    
        	   }
        	   catch(Exception e) {
        		   CaptureScreenshotOnFailureListener.captureScreenShot();
        		   Log.info("Playback failed for content: "+driver.findElement(By.id(playbackTitle)).getText());
        	   }
        		//??
        		try {
        			TestBase.MouseHoverByJavaScript(By.id(playbackClose));
        		
        				} catch (Exception e) {
        		
        			Log.info("Playback quit not succeeded...");
        			}
        		
        		//go to the next item in the list
        	   second--;

        	}
		    Reporter.log("Playback works fine");
		    Log.info("Playback works fine");
		    
	    		} catch (Exception e) {
	    		Log.info("Playback start failed!");
	    			
	    	}
		    
		    


	return new PlayMainContent(driver); 
	}
}
