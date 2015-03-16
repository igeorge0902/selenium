package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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

import testng.Verify;
import utils.WaitTool;


public class TestBase extends Verify implements WebElements{
	
	public static Logger Log = Logger.getLogger(Logger.class.getName());	 	
	protected static WebElement element = null;
    
	/**
	 *  The constructor driver for all classes, that extend TestBase. The driver is returned in {@link WebDriverManager.class}, 
	 *  where it will be instantiated with {@value browser} and {@value url} params.
	 */
	protected static WebDriver driver = null;
	public TestBase(WebDriver driver) {
		  TestBase.driver = WebDriverManager.driver; 
	  }
	  
	public TestBase() {
		  
	  }
	
	public static JavascriptExecutor js = (JavascriptExecutor) driver;


	/*
	 * softAssert methods
	 */

	
	private static Map<ITestResult, List<Throwable>> verificationFailuresMap = new HashMap<ITestResult, List<Throwable>>();
	
	//private static Map<String, List<WebElement>> contentsMap = new HashMap<String, List<WebElement>>();


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
	* 
	* @return alertText
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
    
	  /**
		* Checks if the elment is in the DOM. 
		* 
		* @param by - selector to find the element
		* @return true or false
		*/
    
    public static boolean isElementPresent(By by)  {
       try {
        	 driver.findElement(by);
        	 Log.info((by));
          		return true;
        	} 	catch (Throwable e) {
    			addVerificationFailure(e);
    			Log.info(getVerificationFailures(), e);
    	        Reporter.log("Element is not present:"+by.toString()+"<br>");
    			return false;
    		}
    			
        }
      

	  /**
		* Checks if the elment is in the DOM and displayed. 
		* 
		* @param by - selector to find the element
		* @return true or false
		*/
    
	public boolean isElementPresentAndDisplay(By by) {
		try {
			  driver.findElement(by).isDisplayed();
	          Log.info((by));
			    return true;
			  } catch (Throwable e) {
				addVerificationFailure(e);
				Log.info(getVerificationFailures(), e);
    	        Reporter.log("Element is not present:"+by.toString()+"<br>");
				return false;			
		}
	}
	
	/**
	 * Check if the element is displayed.  
	 * It will wait for 3 seconds for the element to be present on the DOM, and displayed. 
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
	   * Check if the Element present in the DOM. 
	   * 
	   * @param _cssSelector 		element locater
	   * @return					WebElement
	   */
	
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

	 /**
	 * Verify if the text is displayed in the page source. 
	 * 
	 * @throws Exceptions 
	 */

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
	 * Verify if the error message is displayed. 
	 * @throws Exceptions 
	 */
	public boolean verifyErrorMessageRequired_displayed() throws Exception {  		
        	return isElementDisplayed(By.name("EmailAddressAgain"));       
    	}  
	

	/**
	 * Verify the error message of "I accept Terms of Service" is displayed.
	 * @throws Exceptions 
	 */
	public static boolean isErrorMessageRequired_Check_TOS_displayed() throws Exception { 
        	return isElementDisplayed(By.id("Terms"));           
    	} 
	
	/**
	 * Verify if the new device dialog is displayed.
	 * @throws Exceptions 
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
	 * By (by) = element mouse over method. Uses actions class which is object of the driver.
	 * 
	 * @param by
	 * @return Returns true if the action was successful.
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
		element = WaitTool.waitForElementPresent(driver, by, 3);
 
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
     * Play any contents method. PlayButton types are selected from the PlayButton menu list. 
     * PlayButton types are iterated from a String [] array defined in the {@link main.WebElements.class}
     *     
     * @param String play
     */
	/*
	public static void playContents(String playContent){
    	JavascriptExecutor js = (JavascriptExecutor) driver;
		
    	List<WebElement> playbuttonmenu;
        playbuttonmenu = driver.findElement(By.id("play_dropdown")).findElements(By.tagName("a")); 
                
        for(int i =0; i<playbuttonmenu.size();i++)    
        {
            String onClick = playbuttonmenu.get(i).getAttribute("onclick");
            
           // for(int j=0; j<playContent.length;j++)
            //{
            
                if(onClick.contains(playContent))
                {
                    js.executeScript(onClick);
                
                }
            //}
            
        }
		
	}*/
	
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
	 * Use this method to pause / play the content in the player. 
	 * 
	 */
	
	public static void playPause(){
    	JavascriptExecutor js = (JavascriptExecutor) driver;
		
    	WebElement playpause;
        playpause = driver.findElement(By.id("playbackControls")).findElement(By.id("playPause")); 
                
            String onClick = playpause.getAttribute("onclick");
            
                if(onClick.equals(playPause))
                {
                    js.executeScript(onClick);
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
	   	
	    /**
	    * 
	    * <Object[]> iterator to retrieve elements by "href" from a page.
	    * 
	    */
	   
	    public static Iterator<Object[]> contents() {
	    	
      	  	//Set<Object[]> dataSetToBeReturned=new HashSet<Object[]>();
        	List<Object[]> dataToBeReturned = new ArrayList<Object[]>(/*dataSetToBeReturned*/);	
            //List<String> lines = new ArrayList<String> ();
            
        	List<WebElement> findElements;
        	findElements = driver.findElement(By.id("normalView")).findElements(By.tagName("a"));
        		   
	            for (WebElement webElement : findElements)
	            	
	            {	
	            	String contents = webElement.getAttribute("href");
	                
	            	//dataToBeReturned.add(new Object[] { contents } );
	                //TODO: collect only content urls
	                dataToBeReturned.add(new Object [] {contents});
	                
	            }
	                            
    	        if (dataToBeReturned.isEmpty()) {
    	        	System.out.println("No contents in the Category!!");
    	        	Log.info("No contents in the Category!!");
    	        	Reporter.log("No contents in the Category!!");
    	        }
    	        
    	        else if (!dataToBeReturned.isEmpty()) {
    	        		        		                
                	for(Object[] contentlist : dataToBeReturned) {	                
                		StringBuffer contentlistbuffer = new StringBuffer();
                		
                    for(int i = 0; i < contentlist.length; i++) {
                    	contentlistbuffer.append(contentlist[i]);

                    System.out.println(contentlistbuffer.toString());
                    Log.info(contentlistbuffer.toString());
                                    
                  }	                	                
               }
    	    }
				return dataToBeReturned.iterator();
	    }
	 
	 /**
	 * Retrieves verficationFailures' to from List<Throwable>, 
	 * that will be appended to the ITestReport by {@link TestMethodListener.class}.
	 * 
	 */    
	    
	public static List<Throwable> getVerificationFailures() {
		List<Throwable> verificationFailures = verificationFailuresMap.get(Reporter.getCurrentTestResult());
		return verificationFailures == null ? new ArrayList<Throwable>() : verificationFailures;
	}
	
	 /**
	 * Adds verficationFailures' to the List<Throwable>, 
	 * that will be appended to the ITestReport by {@link TestMethodListener.class}.
	 * @param e
	 */
	
	public static void addVerificationFailure(Throwable e) {
		List<Throwable> verificationFailures = getVerificationFailures();
		verificationFailuresMap.put(Reporter.getCurrentTestResult(), verificationFailures);
		verificationFailures.add(e);
	}

	
}
