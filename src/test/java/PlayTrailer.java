package test.java;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Listeners;

import com.thoughtworks.selenium.Selenium;
import com.thoughtworks.selenium.Silvernium;

@SuppressWarnings("deprecation")
@Listeners({ TestListeners.class, test.java.CaptureScreenshotOnFailureListener.class, TestMethodListener.class})

public class PlayTrailer extends TestBase {
	
	private static Silvernium silvernium;	
	
	public PlayTrailer(WebDriver driver){
		super(driver); 
	}

	//silvernium = new Silvernium( (Selenium) driver, silverLightPlayerObjectId); 
	
	  public void PlayTrailerSilverLight(WebDriver driver) {
		   silvernium = new Silvernium( (Selenium) driver, silverLightPlayerObjectId); 
	  }

	
	public PlayTrailer playTrailer() throws Exception {

		   Actions action = new Actions(driver);	  

			//driver.get(BaseUrls.HBO.get() + "/group/offers");
		     
		    verifySuccess(driver.getTitle().equals("HBO GO. Bárhol. Bármikor."));
	   		    
		    for (int second = 0;; second++) {
		    	if (second >= 60) fail("timeout");
		    	try { if (isElementPresent(By.id("slide_categories"))) break; } catch (Exception e) {}
		    	Thread.sleep(1000);
		    }
		        
		    
		    for (int second = 0;; second++) {
		    	if (second >= 60) fail("timeout");
		    	try { if (isElementPresent(By.xpath("(//a[contains(text(),'Sorozatok')])[2]"))) break; } catch (Exception e) {}
		    	Thread.sleep(1000);
		    }
		    
		    for (int second = 0;; second++) {
		    	if (second >= 60) fail("timeout");
		    	try { if (isElementPresent(By.xpath(".//*[@id='slide_categories']/a[2]"))) break; } catch (Exception e) {}
		    	Thread.sleep(1000);
		    }
		        	
		    ElementScreenshot.captureElementScreenshot(driver.findElement(By.xpath("(//a[contains(text(),'Sorozatok')])[2]")));
		    driver.findElement(By.xpath("(//a[contains(text(),'Sorozatok')])[2]")).click();

		    
		    driver.findElement(By.id("selection_button")).click();
		    for (int second = 0;; second++) {
		    	if (second >= 60) fail("timeout");
		    	try { if (isElementPresent(By.xpath("(//a[@onclick='trackItem()'])[2]"))) break; } catch (Exception e) {}
		    	Thread.sleep(1000);
		    }


		    driver.findElement(By.xpath("(//a[@onclick='trackItem()'])[2]")).click();
		    for (int second = 0;; second++) {
		    	if (second >= 60) fail("timeout");
		    	try { if (isElementPresent(By.id("episode_chooser"))) break; } catch (Exception e) {}
		    	Thread.sleep(1000);
		    }
	  
		    driver.findElement(By.xpath("//html/body/div[9]/div[3]/div[1]/div[1]/div[7]/div[2]/div/div[1]/div/a[1]")).click();
		    
		    for (int second = 0;; second++) {
		    	if (second >= 60) fail("timeout");
		    	try { if (isElementPresent(By.xpath("//div[@id='content-inner']/div/div/div[2]"))) break; } catch (Exception e) {}
		    	Thread.sleep(1000);
		    }

		    for (int second = 0;; second++) {
		    	if (second >= 60) fail("timeout");
		    	try { if (isElementPresent(By.id("play-button-894d224d-00d6-43f7-92df-6ac52c4cdfc4"))) break; } catch (Exception e) {}
		    	Thread.sleep(1000);
		    }
		    	    
		    WebElement mousehover;
		    mousehover = driver.findElement(By.id("play-button-894d224d-00d6-43f7-92df-6ac52c4cdfc4"));	            
	        action.moveToElement(mousehover).build().perform();        
	                  
	        
	    	JavascriptExecutor js = (JavascriptExecutor) driver;
	    	
	    	List<WebElement> playbuttonmenu;
	        playbuttonmenu = driver.findElement(By.id("play_dropdown")).findElements(By.tagName("a")); 
	                
	        for(int i =0; i<playbuttonmenu.size();i++)    
	        {
	            String onClick = playbuttonmenu.get(i).getAttribute("onclick");
	            
	            for(int j=0; j<playTrailer.length;j++)
	            {
	                if(onClick.contains(playTrailer[j]))
	                {
	                    js.executeScript(onClick);
	                
	                }
	            }
	            
	        }
	        
	//verifyTrue(silvernium.isLoaded());


	return new PlayTrailer(driver); 
	}
}
