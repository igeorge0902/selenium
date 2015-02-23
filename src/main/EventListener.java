package main;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

public class EventListener extends TestBase implements AbstractWebDriverEventListener {

	public EventListener(WebDriver driver){
		super(driver); 
	}
	
	WebDriverWait wait = new WebDriverWait(driver, 30);
	private static Logger Log = Logger.getLogger(Logger.class.getName());

	   
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
	wait.until(ExpectedConditions.presenceOfElementLocated(by));

}

@Override
public void afterFindBy(By by, WebElement element, WebDriver driver) {
	driver.switchTo().activeElement();
	Reporter.log("Found element:"+by.toString()+"<br>");
	Log.info("Element found:"+by);
}

////////////////////CLICKON RELATED METHODS ///////////////
@Override
public void beforeClickOn(WebElement element, WebDriver driver) {
// TODO Auto-generated method stub

}

@Override
public void afterClickOn(WebElement element, WebDriver driver) {
	driver.switchTo().activeElement();
}

@Override
public void afterClickOn(By by, WebDriver driver) {
	Reporter.log("Clicked on:"+by.toString()+"<br>");
	Log.info("Element clicked on:"+by);
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
// TODO Auto-generated method stub

}



}