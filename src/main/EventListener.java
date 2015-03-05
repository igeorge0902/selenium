package main;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import utils.WaitTool;

public class EventListener extends TestBase implements AbstractWebDriverEventListener {

	public EventListener(WebDriver driver){
		super(driver); 
	}
	
	private static Logger Log = Logger.getLogger(Logger.class.getName());
	private final String safari = "*safari";
	private final String chrome = "CHROME";


	   
//////////NAVIGATION RELATED METHODS ////////////////
@Override
public void beforeNavigateTo(String url, WebDriver driver) {
System.out.println("Before Navigate To "+url);

}

@Override
public void afterNavigateTo(String url, WebDriver driver) {
// TODO Auto-generated method stub

}

@Override
public void beforeNavigateBack(WebDriver driver) {
System.out.println("Before Navigate Back. Right now I'm at "+driver.getCurrentUrl());

}

@Override
public void afterNavigateBack(WebDriver driver) {
// TODO Auto-generated method stub

}

@Override
public void beforeNavigateForward(WebDriver driver) {
// TODO Auto-generated method stub

}

@Override
public void afterNavigateForward(WebDriver driver) {
// TODO Auto-generated method stub

}

/////////////////// FINDBY RELATED METHODS ///////////////
@Override
public void beforeFindBy(By by, WebElement element, WebDriver driver) {

    if (safari == WebDriverManager.getBroswer()) {
	WaitTool.waitForElementPresent(driver, by, 10);
    driver.switchTo().activeElement();
    }


}

@Override
public void afterFindBy(By by, WebElement element, WebDriver driver) {
    
    if (safari == WebDriverManager.getBroswer()) {
   	WaitTool.waitForElementPresent(driver, by, 10);
    //driver.switchTo().activeElement().click();
    }
    
    if (chrome == WebDriverManager.getBroswer()) {    
    ((JavascriptExecutor) driver).executeScript("window.scrollTo(0,"+element.getLocation().y+")");
    }
    
    Reporter.log("Found element:"+by.toString());
    Log.info("Element found:"+by);
}

////////////////////CLICKON RELATED METHODS ///////////////
@Override
public void beforeClickOn(WebElement element, WebDriver driver) {
// TODO Auto-generated method stub

}

@Override
public void afterClickOn(WebElement element, WebDriver driver) {
	//driver.switchTo().activeElement();
	Reporter.log("Clicked on:"+element.toString());
	Log.info("Element clicked on:"+element);
}

@Override
public void afterClickedOn(By by, WebElement element, WebDriver driver) {
	Reporter.log("Clicked on:"+by.toString());
	Log.info("Element clicked On:"+by);
}


///////////////// CHANGE OF VALUE RELATED METHODS //////////////
@Override
public void beforeChangeValueOf(WebElement element, WebDriver driver) {
// TODO Auto-generated method stub

}

@Override
public void afterChangeValueOf(WebElement element, WebDriver driver) {
// TODO Auto-generated method stub

}

/////////////// SCRIPT EXECUTION RELATED METHODS ///////////////
@Override
public void beforeScript(String script, WebDriver driver) {
// TODO Auto-generated method stub

}

@Override
public void afterScript(String script, WebDriver driver) {
// TODO Auto-generated method stub

}

/////////////// EXCEPTION RELATED METHODS ///////////////////////
@Override
public void onException(Throwable throwable, WebDriver driver) {
	}

}



