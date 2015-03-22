package pageObjects;


import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

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
import utils.SampleDataProvider;
import utils.WaitTool;

import java.util.regex.Matcher;
import java.util.regex.Pattern;



@SuppressWarnings("unused")
@Listeners({ TestListeners.class, main.CaptureScreenshotOnFailureListener.class, TestMethodListener.class})

public class SelectMovieGroups extends TestBase implements WebElements{
	
	
	public SelectMovieGroups(WebDriver driver){
		super(driver); 
	}
	
	public SelectMovieGroups actionGroups() throws Exception {

		Actions actions = new Actions(driver);
		JavascriptExecutor js = (JavascriptExecutor) driver;
			
			driver.get(BaseUrls.PLAYER.get() + OffersScreen);
			
			WaitTool.waitForElement(driver, By.id(HeaderButton), 5);
			driver.findElement(By.id(HeaderButton)).click();

			TestBase.isElementPresent(By.id(Movies));
			WebElement element = driver.findElement(By.id(Movies));
			
			//get the category
			WebElement category;
			category = driver.findElement(By.linkText(actionCategory));
			
			//**scroll into view of the element (one of them should work)
			js.executeScript("arguments[0].scrollIntoView(true);", category);
			Thread.sleep(500);
						
	        js.executeScript("javascript:window.scrollBy(250,350)");
	        
	        actions.moveToElement(element);
	        actions.perform();
			//**
	        
	        //click on the category link found by linkText
			driver.findElement(By.linkText(actionCategory)).click();
	        
			//wait for page to load
	        WaitTool.waitForElement(driver, By.id("normalView"), 10);	        
	        String url = driver.getCurrentUrl();
	        Log.info(url);
	        
	        //get url list as Iterator<String> list from TestBase.contents_() method
	        Iterator<String> list = TestBase.contents_();	        
            StringBuilder builder = new StringBuilder();
            
	        
            //while (list.hasNext()) {
	        	
            	do {
            	
            	//try {	
            	//load the new url	
	        	driver.get(list.next());
	        	System.out.println("Hello");
	        	
	        	//find the actual playbutton --TODO
			   // String stringToSearch = "play-button-07572899-1f5a-4f4e-9204-2fcc63a82775";
			    try {
			    
				    for (int second = 0;; second++) {
				    	
				    	if (second >= 60) fail("timeout");
				    	
				    	try { if (isElementPresentAndDisplay(By.xpath("//img[starts-with(@id, 'play-button-')]")))
				    		
				    		break; 
				    	
				    	} catch (Exception e) {
				    	
				    		Log.info(e.getCause());
				    	}   
				    	
				    	Thread.sleep(1000);

				    }	
			    					    
			    //do a mouse hover on the actual playbutton  
		    	TestBase.MouseHoverByJavaScript(By.xpath("//img[starts-with(@id, 'play-button-')]"));
			    //}
		    	
		    	Log.info("MouseHover on the playbutton succeeded!");
		    	
			    try {
		    	//start playback
		    	TestBase.playContents();
		    	Log.info("Playback has started!");
		    	
			    } catch (Exception e) {
			    	Log.info("Content playback start has failed!");
			    }
			    
			    //player test
		    	
			    } catch (Exception e) {
			    	Log.info("No content on the url");
			    }

		    				    
		    	//TODO: test method for playback
	        	
	        	// iterate until the list has next url
            	} while (list.hasNext());
	        	
            //}	        
	        
	return new SelectMovieGroups(driver); 
	}	

}
