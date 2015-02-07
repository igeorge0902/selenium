package test.java;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;


public class TestBase {
	
	protected static String pageTitle;
	public static String silverlightPlayer = "silverlightPlayer";
	private static Logger Log = Logger.getLogger(Logger.class.getName());
	
    static String[] playTrailer = new String[]{"PlayTrailer"};
    static String[] playContent = new String[]{"PlayContent"};
    static String[] playFree = new String[]{"PlayFree"};
    static String[] playLive = new String[]{"PlayLive"};
    static String[] playExtra = new String[]{"PlayExtra"};
    static String[] playInteractive = new String[]{"PlayInteractive"};
    static String[] playFreeInteractive = new String[]{"PlayFreeInteractive"};
    
    protected WebDriver driver;
    
	  public TestBase(WebDriver driver) {
		  this.driver = driver; 
	  }
	  
	  public TestBase() {
		  
	  }
    
	private static Map<ITestResult, List<Throwable>> verificationFailuresMap = new HashMap<ITestResult, List<Throwable>>();


	public static void currentPlatform() {
	    	Platform.getCurrent();
	    return;
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
    
    
    protected static boolean isElementPresent(By by) {
        try {
          WebDriverManager.driver.findElement(by);
          Log.info((by));
          Reporter.log("Element is present<br>");
          		return true;
        	} 	catch (Throwable e) {
    			addVerificationFailure(e);
    			Log.info(getVerificationFailures(), e);{
    			return false;
    		}
    			
        }
     } 
    
	public boolean isElementPresentAndDisplay(By by) {
		try {
			  WebDriverManager.driver.findElement(by).isDisplayed();
	          Log.info((by));
	          Reporter.log("Element is displayed<br>");
			  	return true;
			  } catch (Throwable e) {
				addVerificationFailure(e);
				Log.info(getVerificationFailures(), e);{
				return false;
			}
			
		}
	}
	
	public boolean isElementPresent(String _cssSelector){
		try {
			  WebDriverManager.driver.findElement(By.cssSelector(_cssSelector));
	          Log.info((_cssSelector));
	          Reporter.log(_cssSelector);
			  	return true;
		      } catch (Throwable e) {
			    addVerificationFailure(e);
			    Log.info(getVerificationFailures(), e);{			
			    return false;
		    }
	    }
	}
	
	protected void sendText(String cssSelector, String text) {
			  WebDriverManager.driver.findElement(By.cssSelector(cssSelector)).sendKeys(text);
	          Log.info((cssSelector));
	          Reporter.log(cssSelector);
	}

	public boolean isTextPresent(String text){ 
		try {
			  WebDriverManager.driver.getPageSource().contains(text);
	          Log.info(text);
	          Reporter.log(text);
	            return true;
		      } catch (Throwable e) {
			    addVerificationFailure(e);
			    Log.info(getVerificationFailures(), e);{			
			    return false;
			}
		}
	}
	
	public String getTitle() {
			return pageTitle;
	}
	
	public boolean isPageLoad() {
		return (WebDriverManager.driver.getTitle().contains(pageTitle));
	}
	
	/**
	 * Verify the error message is displayed. 
	 */
	public boolean verifyErrorMessageRequired_displayed() {  		
        	return isErrorMessageOfField_display(By.name("EmailAddressAgain"));       
    	}  
	

	/**
	 * Verify the error message of "I accept Terms of Service" is displayed.
	 */
	public boolean isErrorMessageRequired_Check_TOS_displayed() { 
        	return isErrorMessageOfField_display(By.id("Terms_theme"));           
    	} 
	
	/**
	 * Check the Error Message field displayed.  
	 * It will wait for the JavaScript error message to be present on the DOM, and displayed. 
	 * 
	 * @param by
	 * @return
	 */
	private boolean isErrorMessageOfField_display(By by){
		WebElement element = null; 
		//wait for the Error Message Element to be present and display
		element = WebDriverManager.driver.findElement(by); 
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
