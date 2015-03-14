package pageObjects;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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


@SuppressWarnings("unused")
@Listeners({ TestListeners.class, main.CaptureScreenshotOnFailureListener.class, TestMethodListener.class})

public class SelectMovieGroups extends TestBase implements WebElements{
	
	
	public SelectMovieGroups(WebDriver driver){
		super(driver); 
	}
	
	public SelectMovieGroups actionGroups() throws Exception {

		Actions action = new Actions(driver);
			
			driver.get(BaseUrls.PLAYER.get() + OffersScreen);
			
			WaitTool.waitForElement(driver, By.id("headerButtonMenu"), 5);
			driver.findElement(By.id("headerButtonMenu")).click();
			
			WaitTool.waitForElement(driver, By.id("categories_group_b84a7a5f-ff13-4854-956b-9bc1070457f1"), 5);			
			WebElement element = driver.findElement(By.id("categories_group_b84a7a5f-ff13-4854-956b-9bc1070457f1"));
			
			/*
			By.id("categories_group_b84a7a5f-ff13-4854-956b-9bc1070457f1");
			if (WaitTool.waitForElement(driver, By.className("menucatTitle closed"), 5) != null) {
				driver.findElement(By.id("categories_group_b84a7a5f-ff13-4854-956b-9bc1070457f1")).click();
			}*/
	
			//scroll into view of the element
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
			Thread.sleep(500); 
			
			
			driver.findElement(By.linkText("Akció, Kaland")).click();
	        
	        WaitTool.waitForElement(driver, By.id("normalView"), 10);
	        
	        //Lists the content urls in the category group, just so
	        List<WebElement> findElements;
	        findElements= driver.findElement(By.id("normalView")).findElements(By.tagName("a"));

	        for (int i=0; i<findElements.size();i++)
	        {
	        	String onClick = findElements.get(i).getAttribute("href");
	        		
	                System.out.println(findElements.get(i).getAttribute("href"));
	                Log.info(findElements.get(i).getAttribute("href"));
	                	                
	        }        
	        	        
	        //get a list of catogory_items by "href", then call the url/navigate to contents detail screen;
	        //start a playback on the contents detail screen, let it play, quit, navigate back to the category view,
	        //then start again from the next index till the last category item
	        
            List<Object[]> dataToBeReturned = new ArrayList<Object[]>();
	        	
	        	List<WebElement> contents;
	        	contents = driver.findElement(By.id("normalView")).findElements(By.tagName("a"));
	        		            
	                dataToBeReturned.add(new Object[] { contents } );
	                
	    	        if (dataToBeReturned.isEmpty()) {
	    	        	System.out.println("No contents in the Category!!");
	    	        	Log.info("No contents in the Category!!");
	    	        	Reporter.log("No contents in the Category!!");
	    	        }
	                
	    	        else if (!dataToBeReturned.isEmpty()) {
	    	        	
	                for (int j=0; j<contents.size();j++)
	                {
	    	        	String onClick = contents.get(j).getAttribute("href");
	    	        	
		                System.out.println(contents.get(j).getAttribute("href"));
		                Log.info(contents.get(j).getAttribute("href"));
	                }
	                
    	        }

	        
	        driver.get(BaseUrls.PLAYER.get() + "");
	
	        
	return new SelectMovieGroups(driver); 
	}
	

}
