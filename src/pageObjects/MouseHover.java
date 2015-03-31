package pageObjects;

import main.TestBase;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import testng.CustomException;

public class MouseHover extends TestBase {
	
	public MouseHover(WebDriver driver){
		super(driver); 
	}

	public MouseHover mouseHover() throws Exception, CustomException{
		
		Actions action = new Actions(driver);

		//driver.get("http://huvip.hbogo.eu/content/game-of-thrones-1-winter-is-coming-2012115219");
		
    for (int second = 0;; second++) {
    	if (second >= 60) fail("timeout");
    	try { if (isElementPresentAndDisplay(By.xpath(PlayButton))) break; } catch (Exception e) {
    		Log.info(e.getCause());
    	} Thread.sleep(1000);
    }			
    	
		Thread.sleep(5000);
    
    	WebElement selectedplaygomb = driver.findElement(By.xpath(PlayButton));
        
    	if (selectedplaygomb.isDisplayed()){
    		action.moveToElement(selectedplaygomb).build().perform();
    		Log.info("Mouse hove action succeeded!");
            
        }
    	
    	else if (!selectedplaygomb.isDisplayed()) {
        	JavascriptExecutor js = (JavascriptExecutor) driver;

    		js.executeScript("return window.location.reload(true)");
    		TestBase.MouseHoverByJavaScript((By.xpath(PlayButton)));
    	}
    	
    	TestBase.playContents();
     
    	TestBase.isPlayBackRunning(2, 20000, true);
	
    return new MouseHover(driver); 	
	
	}
}


