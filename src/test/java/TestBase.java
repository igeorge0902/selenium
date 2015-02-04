package test.java;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;

public class TestBase {
	
	private String pageTitle;
	private static Logger Log = Logger.getLogger(Logger.class.getName());

	
	private static Map<ITestResult, List<Throwable>> verificationFailuresMap = new HashMap<ITestResult, List<Throwable>>();



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
    }
    
    
    protected static boolean isElementPresent(By by) {
        try {
          WebDriverManager.driver.findElement(by);
          Log.info((by));
          Reporter.log("Element found", 10);
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
	          Reporter.log("Element is displayed", 10);
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
	          Reporter.log("CSS found", 10);
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
	          Reporter.log("CSS found", 10);
	}

	public boolean isTextPresent(String text){ 
		try {
			  WebDriverManager.driver.getPageSource().contains(text);
	          Log.info((text));
	          Reporter.log("Text found", 10);
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
	
	public boolean isPageLoad(){
		return (WebDriverManager.driver.getTitle().contains(pageTitle));
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
