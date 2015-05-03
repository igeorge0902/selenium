package main.java.qa.framework.pageObjects;


import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

import main.java.qa.framework.main.BaseUrls;
import main.java.qa.framework.main.ElementScreenshot;
import main.java.qa.framework.main.TestBase;
import main.java.qa.framework.main.WebElements;
import main.java.qa.framework.testng.TestListeners;
import main.java.qa.framework.testng.TestMethodListener;
import main.java.qa.framework.utils.SampleDataProvider;
import main.java.qa.framework.utils.WaitTool;
import main.java.qa.framework.utils.deleteLines;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Reporter;
import org.testng.annotations.Listeners;

import java.util.regex.Matcher;
import java.util.regex.Pattern;



@SuppressWarnings("unused")
@Listeners({ TestListeners.class, main.java.qa.framework.main.CaptureScreenshotOnFailureListener.class, TestMethodListener.class})

public class SelectMovieGroups extends TestBase implements WebElements{
	
	
	public SelectMovieGroups(WebDriver driver){
		super(driver); 
	}
	
	public SelectMovieGroups actionGroups() throws Exception {

		Actions actions = new Actions(driver);
		PlayMainContent PlayMainContent = new PlayMainContent(driver);

		JavascriptExecutor js = (JavascriptExecutor) driver;
						
			WaitTool.waitForElement(driver, By.id(HeaderButton), 10);
			driver.findElement(By.id(HeaderButton)).click();

			TestBase.isElementPresent(By.id(Movies));
			
			//get the category
			WebElement category;
			category = driver.findElement(By.linkText(actionCategory));
			
			//**scroll into view of the element (one of them should work)
			js.executeScript("arguments[0].scrollIntoView(true);", category);
			Thread.sleep(500);
						
	        js.executeScript("javascript:window.scrollBy(250,350)");
			
	        WebElement element = driver.findElement(By.id(Movies));
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
	        //Iterator<String> list = TestBase.contents_(NormalView);	        
        	
        	List<String> urslList = TestBase.contentsList(NormalView);
        	TestBase.deleteFile(urlsFile);
        	TestBase.createFile(urlsFile);
        	TestBase.writeFile(urlsFile, urslList);
        	

    			/*
    		  //callind deleteLines java app to delete lines from urls.txt
    	      String[] arguments = new String[] {"123"};
    	      deleteLines.main(arguments);
    			 */
        	
	return new SelectMovieGroups(driver); 
	}	

}
