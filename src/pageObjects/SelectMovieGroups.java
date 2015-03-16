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
			
			WebElement category;
			category = driver.findElement(By.linkText(actionCategory));
			//scroll into view of the element
			js.executeScript("arguments[0].scrollIntoView(true);", category);
			Thread.sleep(500);
						
	        js.executeScript("javascript:window.scrollBy(250,350)");
	        
	        actions.moveToElement(element);
	        actions.perform();
			
			driver.findElement(By.linkText(actionCategory)).click();
	        
	        WaitTool.waitForElement(driver, By.id("normalView"), 10);
	        
	        TestBase.contents();
	        
	        
	return new SelectMovieGroups(driver); 
	}	

}
