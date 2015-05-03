package main.java.qa.framework.main;

import main.java.qa.framework.utils.WaitTool;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

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

    if (WebDriverManager.getBroswer().equals(safari)) {
    	try {
    		WaitTool.waitForElementPresent(driver, by, 10);
    		driver.switchTo().activeElement();
    		System.out.println("beforeFindBy event done for Safari");
    	} catch (Exception e) {
    		System.out.println("No beforeFindBy event done for Safari");
    	}
    }


}

@Override
public void afterFindBy(By by, WebElement element, WebDriver driver) {
    
    if (WebDriverManager.getBroswer().equals(safari)) {
    	try {
    		WaitTool.waitForElement(driver, by, 5);
    		driver.switchTo().activeElement().click();
    		System.out.println("afterFindBy event done for Safari");
    	} catch (Exception e) {
    		System.out.println("No afterFindBy event done for Safari");
    	}
    }
    
    if (WebDriverManager.getBroswer().equals(chrome)) {  
    	
    	try {
		
    		Thread.sleep(1000);
		
    	} catch (InterruptedException ex) {

			ex.printStackTrace();
		}
        try {
        	
        	if (driver instanceof JavascriptExecutor) {
        		
            ((JavascriptExecutor) driver).executeScript(
                    "arguments[0].scrollIntoView(true);", by);
            System.out.println("afterFindBy event done for CHROME");
        	}
        } catch (Exception e) {
            System.out.println("No afterFindBy event done for CHROME");
        }
    }
                   
    //((JavascriptExecutor) driver).executeScript("window.scrollTo(0,"+element.getLocation().y+")");
       
    Reporter.log("Found element:"+by.toString());
    Log.info("Element found:"+by);
}

////////////////////CLICKON RELATED METHODS ///////////////
@Override
public void beforeClickOn(WebElement element, WebDriver driver) {
/*
    if (WebDriverManager.getBroswer().equals(safari)) {
    	try {
    		driver.switchTo().activeElement();
    		System.out.println("beforeClickOn event done for Safari");
    	} catch (Exception e) {
    		System.out.println("No beforeClickOn event done for Safari");
    	}
    }*/
}

@Override
public void afterClickOn(WebElement element, WebDriver driver) {
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
	Reporter.log("Script run: "+ script);
	Log.info("Script run: "+ script);
}

/////////////// EXCEPTION RELATED METHODS ///////////////////////
@Override
public void onException(Throwable throwable, WebDriver driver) {
	}

}



