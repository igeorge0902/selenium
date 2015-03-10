package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;

import testng.ExpectedExceptions;
import testng.Verify;
import utils.WaitTool;


public class TestBase extends Verify implements WebElements{
	
	public static String silverLightPlayerObjectId = "silverlightPlayer";
	public static Logger Log = Logger.getLogger(Logger.class.getName());
	 	
	protected static WebDriver driver = null;
	protected static WebElement element = null;
    
	public TestBase(WebDriver driver) {
		  TestBase.driver = WebDriverManager.driver; 
	  }
	  
	public TestBase() {
		  
	  }

	/*
	 * softAssert methods
	 */

	
	private static Map<ITestResult, List<Throwable>> verificationFailuresMap = new HashMap<ITestResult, List<Throwable>>();


	public static void currentPlatform() {
	    	Platform.getCurrent();
	    return;
	}

    public static boolean isSupportedPlatformMac(boolean condition) {
        Platform current = Platform.getCurrent();
        return Platform.MAC.is(current);
      }
     
    
    public static boolean isSupportedPlatformWindows(boolean condition) {
        Platform current = Platform.getCurrent();
        return Platform.WIN8.is(current) || Platform.WIN8_1.is(current);
      }
       
	
	/**
	* Confirm alert, prompt, or confirmation dialog that is present on the page.
	* Does nothing if confirmation not present on page and returns empty string.
	 * @return 
	*/
	
	public String getAlertConfirmation() throws InterruptedException{		
		Alert alert = driver.switchTo().alert();
		String alertText = alert.getText();
		alert.accept();
		Log.info("JavaScript text: " + alert.getText());
		Reporter.log("JavaScript text: " + alert.getText(), true);
		return alertText;		
	}

	public static void assertTrue(boolean condition) {
    	Assert.assertTrue(condition);
    }
    
    public static void assertTrue(boolean condition, String message) {
    	Assert.assertTrue(condition, message);
    }
    
    public static void assertFalse(boolean condition) {
    	Assert.assertFalse(condition);
    }
    
    public static void assertFalse(boolean condition, String message) {
    	Assert.assertFalse(condition, message);
    }
    
    public static void assertEquals(boolean actual, boolean expected) {
    	Assert.assertEquals(actual, expected);
    }
    
    public static void assertEquals(Object actual, Object expected) {
    	Assert.assertEquals(actual, expected);
    }
    
    public static void assertEquals(Object[] actual, Object[] expected) {
    	Assert.assertEquals(actual, expected);
    }
    
    public static void assertEquals(Object actual, Object expected, String message) {
    	Assert.assertEquals(actual, expected, message);
    }
    
    public static void verifyTrue(boolean condition) {
    	try {
    		assertTrue(condition);
    	} catch(Throwable e) {
    		addVerificationFailure(e);
    		Log.info(getVerificationFailures(), e);
    	}
    }
    
    public static boolean verifySuccess(boolean condition) {
    	try {
    		assertTrue(condition);
    	} catch(Throwable e) {
    		addVerificationFailure(e);
    		Log.info(getVerificationFailures(), e);
    	} if (condition == true)
	    {
    		Reporter.log("<br><p>Verification is successfull<br></p>");
    		Log.info("Verification is successfull");
	    } else
	    {
	    	Reporter.log("<br><p>Verification is not successfull<br></p>");
	    	Log.info("Verification is not successfull");
	    }
	    return condition;
    }
    
    
    public static void verifyTrue(boolean condition, String message) {
    	try {
    		assertTrue(condition, message);
    		Log.info(message);
    		Reporter.log(message);
    	} catch(Throwable e) {
    		addVerificationFailure(e);
    		Log.info(getVerificationFailures(), e);
    		Log.info(message);
    		Reporter.log(message);
    	}
    }
    
    
    public static void verifyFalse(boolean condition) {
    	try {
    		assertFalse(condition);
		} catch(Throwable e) {
    		addVerificationFailure(e);
    		Log.info(getVerificationFailures(), e);
		}
    }
    
    public static void verifyFalse(boolean condition, String message) {
    	try {
    		assertFalse(condition, message);
    	} catch(Throwable e) {
    		addVerificationFailure(e);
    		Log.info(getVerificationFailures(), e);
    		Log.info(message);
    		Reporter.log(message);
    	}
    }
    
    public static void verifyEquals(boolean actual, boolean expected) {
    	try {
    		assertEquals(actual, expected);
		} catch(Throwable e) {
    		addVerificationFailure(e);
    		Log.info(getVerificationFailures(), e);
		}
    }

    public static void verifyEquals(Object actual, Object expected) {
    	try {
    		assertEquals(actual, expected);
		} catch(Throwable e) {
    		addVerificationFailure(e);
    		Log.info(getVerificationFailures(), e);
		}
    }
    
    public static void verifyEquals(Object[] actual, Object[] expected) {
    	try {
    		assertEquals(actual, expected);
		} catch(Throwable e) {
    		addVerificationFailure(e);
    		Log.info(getVerificationFailures(), e);
		}
    }

    public static void fail(String message) {
    	Assert.fail(message);
		Log.info(message);
		Reporter.log(message);
    }
    
    
    protected boolean isElementPresent(By by)  {
       try {
        	 driver.findElement(by);
        	 Log.info((by));
        	 //Reporter.log("Element is present:"+by.toString()+"<br>");
          		return true;
        	} 	catch (Throwable e) {
    			addVerificationFailure(e);
    			Log.info(getVerificationFailures(), e);
    	        Reporter.log("Element is not present:"+by.toString()+"<br>");
    			return false;
    		}
    			
        }
      

    
	public boolean isElementPresentAndDisplay(By by) {
		try {
			  driver.findElement(by).isDisplayed();
	          Log.info((by));
	          //Reporter.log("Element is present:"+by.toString()+"<br>");
			    return true;
			  } catch (Throwable e) {
				addVerificationFailure(e);
				Log.info(getVerificationFailures(), e);
    	        Reporter.log("Element is not present:"+by.toString()+"<br>");
				return false;			
		}
	}
	
	  public boolean isElementPresent(String _cssSelector){
		try {
			  driver.findElement(By.cssSelector(_cssSelector));
	          Log.info((_cssSelector));
	          Reporter.log(_cssSelector);
			  	return true;
		      } catch (Throwable e) {
			    addVerificationFailure(e);
			    Log.info(getVerificationFailures(), e);
    	        Reporter.log(_cssSelector);			
			    return false;
	    }
	}


	public static boolean isTextPresent(String text){ 
		try {
			  driver.getPageSource().contains(text);
	          Log.info(text);
	          Reporter.log(text);
	            return true;
		      } catch (Throwable e) {
			    addVerificationFailure(e);
			    Log.info(getVerificationFailures(), e);		
			    return false;
		}
	}
	
	
	/**
	 * Verify the error message is displayed. 
	 * @throws ExpectedExceptions 
	 */
	public boolean verifyErrorMessageRequired_displayed() throws Exception {  		
        	return isElementDisplayed(By.name("EmailAddressAgain"));       
    	}  
	

	/**
	 * Verify the error message of "I accept Terms of Service" is displayed.
	 * @throws ExpectedExceptions 
	 */
	public static boolean isErrorMessageRequired_Check_TOS_displayed() throws Exception { 
        	return isElementDisplayed(By.id("Terms"));           
    	} 
	
	/**
	 * Verify the new device dialog is not displayed.
	 * @throws ExpectedExceptions 
	 */
	public static boolean isNewDeviceDialog() throws Exception { 
        	return isElementDisplayed(By.id("newDeviceInput"));           
    	} 
	
	  /** 
	   * Send text keys to the element that finds by cssSelector.  
	   * It shortens "driver.findElement(By.cssSelector()).sendKeys()". 
	   * @param cssSelector
	   * @param text
	   */
	  protected void sendText(String cssSelector, String text) {
			driver.findElement(By.cssSelector(cssSelector)).sendKeys(text);
	  }
	
	/**
	 * Check if the element is displayed.  
	 * It will wait for the element to be present on the DOM, and displayed. 
	 * 
	 * @param by
	 * @return if element != null it returns true otherwise false.
	 * @throws Exception
	 */
	protected static boolean isElementDisplayed(By by) {

		//wait for the Error Message Element to be present and display
		element = WaitTool.waitForElement(driver, by, 3); 
		
			if (element != null){
				Log.info("Element found: "+by);
				return true;  
		}
		Log.info("Element not found: "+by);
		return false; 
	}

	/**
	 * By (by) = element mouse over method. Uses actions class which is object of the driver.
	 * 
	 * @param by
	 * @return
	 * @throws Exception 
	 */
	
	public static boolean ElementMouseOver(By by) throws Exception
        {
		
		WebElement element = null; 
		
		//wait for the Error Message Element to be present and display
		element = WaitTool.waitForElement(driver, by, 3);
		
            driver.manage().window().maximize();
            Actions actions = new Actions(driver);
            try
            {
            	actions.moveToElement(element).build().perform();
                Thread.sleep(2000);
  
             }
            catch (Exception e)
            {
                Log.info(e.getMessage());
                return false;
            }
            return true;
        }


	/**
	 * Element mouse hover method with JavaScript. 
	 * 
	 * @param by
	 * @throws Exception 
	 */
	
	public static void MouseHoverByJavaScript(By by) throws Exception
        {
		
		WebElement element = null; 
		//wait for the Error Message Element to be present and display
		element = WaitTool.waitForElement(driver, by, 3);
 
            String javaScript = "var evObj = document.createEvent('MouseEvents');"+
                                "evObj.initMouseEvent(\"mouseover\",true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);"+
                                "arguments[0].dispatchEvent(evObj);";
            
            JavascriptExecutor js = (JavascriptExecutor) driver;
            try {
            js.executeScript(javaScript, element);
            Thread.sleep(2000);
          } catch (Exception e) {
        	  Log.info(e);
          }
        }
	
    /**
     * 
     * PlayTrailer method. PlayButtons are selected from the PlayButton menu list.
     *     
     */
	
	public static void playTrailers(){
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
		
	}
	
    /**
     * 
     * Play main content method. PlayButtons are selected from the PlayButton menu list.
     *     
     */
	
	public static void playContents(){
    	JavascriptExecutor js = (JavascriptExecutor) driver;
		
    	List<WebElement> playbuttonmenu;
        playbuttonmenu = driver.findElement(By.id("play_dropdown")).findElements(By.tagName("a")); 
                
        for(int i =0; i<playbuttonmenu.size();i++)    
        {
            String onClick = playbuttonmenu.get(i).getAttribute("onclick");
            
            for(int j=0; j<playContent.length;j++)
            {
                if(onClick.contains(playContent[j]))
                {
                    js.executeScript(onClick);
                
                }
            }
            
        }
		
	}
	
    /**
     * 
     * Play free content method. PlayButtons are selected from the PlayButton menu list.
     *     
     */
	
	public static void playFrees(){
    	JavascriptExecutor js = (JavascriptExecutor) driver;
		
    	List<WebElement> playbuttonmenu;
        playbuttonmenu = driver.findElement(By.id("play_dropdown")).findElements(By.tagName("a")); 
                
        for(int i =0; i<playbuttonmenu.size();i++)    
        {
            String onClick = playbuttonmenu.get(i).getAttribute("onclick");
            
            for(int j=0; j<playFree.length;j++)
            {
                if(onClick.contains(playFree[j]))
                {
                    js.executeScript(onClick);
                
                }
            }
            
        }
		
	}
	
	 /**
     * 
     * Play live content method. PlayButtons are selected from the PlayButton menu list.
     *     
     */
	
	public static void playLives(){
    	JavascriptExecutor js = (JavascriptExecutor) driver;
		
    	List<WebElement> playbuttonmenu;
        playbuttonmenu = driver.findElement(By.id("play_dropdown")).findElements(By.tagName("a")); 
                
        for(int i =0; i<playbuttonmenu.size();i++)    
        {
            String onClick = playbuttonmenu.get(i).getAttribute("onclick");
            
            for(int j=0; j<playLive.length;j++)
            {
                if(onClick.contains(playLive[j]))
                {
                    js.executeScript(onClick);
                
                }
            }
            
        }
		
	}
	
	 /**
     * 
     * Play extra content method. PlayButtons are selected from the PlayButton menu list.
     *     
     */
	
	public static void playExtras(){
    	JavascriptExecutor js = (JavascriptExecutor) driver;
		
    	List<WebElement> playbuttonmenu;
        playbuttonmenu = driver.findElement(By.id("play_dropdown")).findElements(By.tagName("a")); 
                
        for(int i =0; i<playbuttonmenu.size();i++)    
        {
            String onClick = playbuttonmenu.get(i).getAttribute("onclick");
            
            for(int j=0; j<playExtra.length;j++)
            {
                if(onClick.contains(playExtra[j]))
                {
                    js.executeScript(onClick);
                
                }
            }
            
        }
		
	}
	
	 /**
     * 
     * Play interactive content method. PlayButtons are selected from the PlayButton menu list.
     *     
     */
	
	public static void playInteractives(){
    	JavascriptExecutor js = (JavascriptExecutor) driver;
		
    	List<WebElement> playbuttonmenu;
        playbuttonmenu = driver.findElement(By.id("play_dropdown")).findElements(By.tagName("a")); 
                
        for(int i =0; i<playbuttonmenu.size();i++)    
        {
            String onClick = playbuttonmenu.get(i).getAttribute("onclick");
            
            for(int j=0; j<playInteractive.length;j++)
            {
                if(onClick.contains(playInteractive[j]))
                {
                    js.executeScript(onClick);
                
                }
            }
            
        }
		
	}
	
	 /**
     * 
     * Play free interactive content method. PlayButtons are selected from the PlayButton menu list.
     *     
     */
	
	public static void playFreeInteractives(){
    	JavascriptExecutor js = (JavascriptExecutor) driver;
		
    	List<WebElement> playbuttonmenu;
        playbuttonmenu = driver.findElement(By.id("play_dropdown")).findElements(By.tagName("a")); 
                
        for(int i =0; i<playbuttonmenu.size();i++)    
        {
            String onClick = playbuttonmenu.get(i).getAttribute("onclick");
            
            for(int j=0; j<playFreeInteractive.length;j++)
            {
                if(onClick.contains(playFreeInteractive[j]))
                {
                    js.executeScript(onClick);
                
                }
            }
            
        }
		
	}
	
	/**
	 * 
	 * Wrap the clicking action into a loop. 
	 * It clicks the element for multiple attempts if it is necessary. 
	 * The number of iterations is one of the method parameters with value 1 as default.
	 * 
	 * @param by
	 * @param iterations
	 * @throws Exception 
	 */
	
	   public static void SmartClick(WebElement element, int iterations) throws Exception
	   {
	      int i = iterations;
	   
	      if (element == null) 
	      { 
	         Log.info(element +" not found!");
	    	  //add 'Element is null' error message into your log file or throw NoSuchElement or NullReference exception 
	      }
	      if(!element.isDisplayed()) 
	      { 
		         Log.info(element+" is not displayed!");
	    	  //add 'Element is not displayed and cannot be clicked' error message into your log file 
	         //you may add a screenshot here
	      }
	      if(!element.isEnabled()) 
	      {
		         Log.info(element+" is not enabled!");
	    	  //add 'Element is not displayed and cannot be clicked' error message into your log file
	         //plus a screenshot
	      }
	      
	      while (i > 0)
	      {
	         i--;

	         element.click();
	         } 
	      }
	   
   
	public static List<Throwable> getVerificationFailures() {
		List<Throwable> verificationFailures = verificationFailuresMap.get(Reporter.getCurrentTestResult());
		return verificationFailures == null ? new ArrayList<Throwable>() : verificationFailures;
	}
	
	public static void addVerificationFailure(Throwable e) {
		List<Throwable> verificationFailures = getVerificationFailures();
		verificationFailuresMap.put(Reporter.getCurrentTestResult(), verificationFailures);
		verificationFailures.add(e);
	}

	
}
