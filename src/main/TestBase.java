package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;

import testng.Verify;
import utils.WaitTool;


public class TestBase extends Verify {
	
	public static String silverLightPlayerObjectId = "silverlightPlayer";
	private static Logger Log = Logger.getLogger(Logger.class.getName());
	 
	
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
        return Platform.WINDOWS.is(current);
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
	 */
	public boolean verifyErrorMessageRequired_displayed() {  		
        	return isElementDisplayed(By.name("EmailAddressAgain"));       
    	}  
	

	/**
	 * Verify the error message of "I accept Terms of Service" is displayed.
	 */
	public static boolean isErrorMessageRequired_Check_TOS_displayed() { 
        	return isElementDisplayed(By.id("Terms"));           
    	} 
	
	/**
	 * Verify the new device dialog is not displayed.
	 */
	public static boolean isNewDeviceDialog() { 
        	return isElementDisplayed(By.id("newDeviceInput"));           
    	} 
	
	/**
	 * Check if the element is displayed.  
	 * It will wait for the element to be present on the DOM, and displayed. 
	 * 
	 * @param by
	 * @return
	 */
	protected static boolean isElementDisplayed(By by){
		WebElement element = null; 
		//wait for the Error Message Element to be present and display
		element = WaitTool.waitForElement(driver, by, 3); 
		if (element != null){
			return true;  
		}
		
		return false; 
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
