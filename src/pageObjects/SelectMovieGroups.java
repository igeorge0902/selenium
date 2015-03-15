package pageObjects;


import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
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
				
			//scroll into view of the element
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
			Thread.sleep(500); 			
			
			driver.findElement(By.linkText("Akció, Kaland")).click();
	        
	        WaitTool.waitForElement(driver, By.id("normalView"), 10);
	        	      	        
	        TestBase.contents();
	        
	return new SelectMovieGroups(driver); 
	}	

}
