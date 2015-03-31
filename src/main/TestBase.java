package main;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import testng.Verify;
import utils.WaitTool;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class TestBase extends Verify implements WebElements{
	
	public static Logger Log = Logger.getLogger(Logger.class.getName());	 	
	protected static WebElement element = null;

    
	/**
	 *  The constructor driver for all classes, that extend TestBase. The driver is returned in {@link WebDriverManager.class}, 
	 *  where it will be instantiated with {@value browser} and {@value url} params.
	 */
	protected static WebDriver driver;
	public TestBase(WebDriver driver) {
		  TestBase.driver = WebDriverManager.driver; 
	  }
	  
	public TestBase() {

	  }
	
	public static JavascriptExecutor js = (JavascriptExecutor) driver;
		  

	@BeforeClass
	  public void setUp(ITestContext context) throws Exception {
		
		  try {			  
		  // get the web driver parameters from the testng xml file
	      String browser = context.getCurrentXmlTest().getParameter("browser");
	      String url = context.getCurrentXmlTest().getParameter("url");
	      
	      driver = WebDriverManager.startDriver(browser, url, 40); 
	      TestBase.verifyNotNull(driver, "Driver setUp failed!");

		  } catch (Exception e) {
			
			  Log.info(e);
			  Log.info("Safari is reconnecting!");
			  // get the web driver parameters from the testng xml file
		      String browser = context.getCurrentXmlTest().getParameter("browser");
		      String url = context.getCurrentXmlTest().getParameter("url");
		      
		      driver = WebDriverManager.startDriver(browser, url, 40); 
		      TestBase.verifyNotNull(driver, "Driver setUp failed!");
		  }
		  
	}

	@AfterClass
	public void closeBrowser(ITestContext context) {
		WebDriverManager.stopDriver();
	}

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
    
	public static boolean isElementPresentAndDisplay(By by) {
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
		
		WebElement element; 
		
		element = WaitTool.waitForElement(driver, by, 3);
		
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
		element = WaitTool.waitForElementPresent(driver, by, 5);
 
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
	
	public static void clearJavaScript(By by) throws Exception
    {                     
		
	WebElement element = null; 
	//wait for the Error Message Element to be present and display
	element = WaitTool.waitForElementPresent(driver, by, 5);

        String javaScript = "var scripts =  document.getElementsByTagName('script');"+
        					"var torefreshs = ['HBO.min.js'];"+ // list of js to be refresh
        					"var key = 1;"+ // change this key every time you want force a refresh
        					"for(var i=0;i<scripts.length;i++){"+ 
        						"for(var j=0;j<torefreshs;j++){"+ 
        							"if(scripts[i].src && (scripts[i].src.indexOf(torefreshs[j]) > -1)){"+
        								"new_src = scripts[i].src.replace(torefreshs[j],torefreshs[j] + 'k=' + key );"+
        								"scripts[i].src = new_src;"+ // change src in order to refresh js
        							"}"+ 
        						"}"+
        					"}";
        
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
	 * Creates a new file with Path param. 
	 * If the given file on the path exists, it will be deleted first.
	 * 
	 * @param newfile
	 * 
	 */
	
	public static void createFile(String newfile) {
		
		Path newFile = Paths.get(newfile);
		try {
			Files.deleteIfExists(newFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			Files.createFile(newFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Write a text file with the given list elements retrieved from the list.
	 * 
	 * @param newfile
	 * @param list
	 * @throws IOException 
	 */
	
	public static void writeFile(String newfile, List<String> list) {
		
		Path textFile = Paths.get(newfile);
		String path = Paths.get(newfile).toString();
		Charset charset = Charset.defaultCharset();
		List<String> urlList = list;
		
		try {
		
            BufferedWriter bufferedWriter = new BufferedWriter( new FileWriter(path));
            PrintWriter printWriter = new PrintWriter (bufferedWriter);
            
                  for (String item : urlList) {
                	                   
                	  printWriter.write(item);
                	  bufferedWriter.newLine();
			
                  }
        
                  printWriter.flush();
                  printWriter.close();
			
		} catch (IOException e) {
			e.printStackTrace();
			
			try {
				Files.write(textFile, urlList, charset);
			
				} catch (IOException e1) {
					e1.printStackTrace();
			}
		}
	}
	
	/**
	 * 
	 * Reads up a file, and checks if it has any lines.
	 * 
	 * @param textFile
	 * @param condition
	 * @return true if the file is not empty
	 */
	
	public static boolean readFile(Path textFile, boolean condition) {
		
		List<String> linesRead = null;
		try {
			linesRead = Files.readAllLines(textFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if (linesRead !=null) {
			for (String line : linesRead) {
				Log.info(line);
			}
		}
		return true;
	}
	
	public static void deleteFile(String textfile) {
		
		try{
			 
    		File file = new File(textfile);
 
    		if(file.delete()){
    			System.out.println(file.getName() + " is deleted!");
    		}else{
    			System.out.println("Delete operation is failed.");
    		}
 
    	}catch(Exception e){
 
    		e.printStackTrace();
 
    	}
		
	}
	
	/**
	 * Retrieve text of any elements in the DOM, with javaScript.
	 * 
	 * 
	 * @param driver
	 * @param element
	 * @return
	 */
	
	public static String getText(WebDriver driver, WebElement element){
		
	    return (String) ((JavascriptExecutor) driver).executeScript(
	    		
	        "return jQuery(arguments[0]).text();", element);
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
	
	public static void playContents(String playcontent){
		
    	JavascriptExecutor js = (JavascriptExecutor) driver;
		
    	WebElement playbuttonmenu;
        playbuttonmenu = driver.findElement(By.id("play_dropdown")).findElement(By.tagName("a")); 
                
        String onClick = playbuttonmenu.getAttribute("onclick");
        
        if(onClick.contains(playcontent))
        {
            js.executeScript(onClick);
        }
  }
	
	
	/**
	 * Checks if the playback has started, where the nr. of attempts equals the cycle to go, 
	 * and the nr. of seconds equals the interval that is used to check the elapsed time.
	 * 
	 * @param attempt
	 * @param second
	 * @param condition
	 * @return true
	 */
	
	public static boolean isPlayBackRunning(int attempt, int second, boolean condition) {
		
    	//int second;

		while(attempt > 0) {
		
		try {
 		   	    	
		    //runs javascript to get elapsed time
		    WebElement elapsedTime;
		    elapsedTime = driver.findElement(By.id(playbackElapsedTime));				    				    
		    
		    String text = TestBase.getText(driver, elapsedTime);
		    
		    //prints out elapsed time
		    System.out.println("Elapsed time first: "+text);
    	    Thread.sleep(333L);
    	    
    	    //find and replace elapsed time string value       	    
    	    Pattern replace = Pattern.compile("\\:+");
    	    Matcher matcher = replace.matcher(text);
    	    System.out.println(matcher.replaceAll(""));
    	    
		    //runs javascript to get elapsed time
		    WebElement elapsedTime2;
		    elapsedTime2 = driver.findElement(By.id(playbackElapsedTime));				    				    
		    
		    
		    //Thread goes to sleep for 5 seconds 
		    //so that the second elapsed time check will be after the first one
    	    Thread.sleep(second);
		    
    	    //prints out elapsed time
		    String text2 = TestBase.getText(driver, elapsedTime2);
		    System.out.println("Elapsed time second: "+text2);

    	    //find and replace elapsed time string value       	    
    	    Pattern replace2 = Pattern.compile("\\:+");
    	    Matcher matcher2 = replace2.matcher(text2);
    	    System.out.println(matcher.replaceAll(""));
    	    
    	    //convert replaced string value to integer
		    int elapsedtime = Integer.parseInt(matcher.replaceAll(""));
		    int elapsedtime2 = Integer.parseInt(matcher2.replaceAll(""));

		    TestBase.verifyNotSame(elapsedTime, elapsedTime2);
		    
		    //verify should work the way that if playback fails, next content will be loaded to avoid loop for playback fails
		    TestBase.assertTrue(elapsedtime2>elapsedtime, "Playback stopped after "+second+" seconds!");
		    
	   }
	   catch(Exception e) {       		   
		   CaptureScreenshotOnFailureListener.captureScreenShot();
			   Reporter.log("Playback failed for content: "+driver.findElement(By.id(playbackTitle)).getText());
		   Log.info("Playback failed for content: "+driver.findElement(By.id(playbackTitle)).getText());
    	   attempt--;
	   		
	   		}
		}
		return true;			
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
	    * 
	    * <Object[]> iterator to retrieve elements by "href" from a page.
	    * 
	    */
	   
	    public static Iterator<Object []> contents() {
	    	
        	List<Object[]> dataToBeReturned = new ArrayList<Object[]>();	
            
        	List<WebElement> findElements;
        	findElements = driver.findElement(By.id("normalView")).findElements(By.tagName("a"));
        		   
	            for (WebElement webElement : findElements)
	            	
	            {	
	            	String contents = webElement.getAttribute("href");
	                
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
	    * 					 
	    * <String> iterator to retrieve elements by "href" from a page. 
	    * The returned String List is filtered by the actual regex patter.
	    * 
	    */
    
	    public static Iterator<String> contents_() {
	    	
	    	List<String> dataToBeReturned = new ArrayList<String>();	
            List<String> links = new ArrayList<String> ();
            StringBuilder builder = new StringBuilder();

        	List<WebElement> findElements;
        	findElements = driver.findElement(By.id("normalView")).findElements(By.tagName("a"));
        		   
	            for (WebElement webElement : findElements)
	            	
	            {	
	            	String contents = webElement.getAttribute("href");
	                
	                dataToBeReturned.add(contents);
	                builder.append(contents);
	                
	            }
	                            
    	        if (dataToBeReturned.isEmpty()) {
    	        	System.out.println("No contents in the Category!!");
    	        	Log.info("No contents in the Category!!");
    	        	Reporter.log("No contents in the Category!!");
    	        }
    	        
    	        else if (!dataToBeReturned.isEmpty()) {
    	        		        		                
                	for(String contentlist : dataToBeReturned) {	 

                        Log.info(contentlist);
                        
                        try {	
              			   
                        	String stringToSearch = contentlist;
            			    
            			    //*** the pattern we want to search for
            			    Pattern p = Pattern.compile(BaseUrls.PLAYER.toString());
            			    
            			    Matcher m = p.matcher(stringToSearch);
            			 
            			    // if we find a match, get the group
            			    if (m.find())
            			    {
            			      // we're only looking for one group, so get it
            			      String theGroup = m.group(1);
            			       
            			      // print and log the group out for verification
            			      System.out.format("%s", theGroup);
            			      
            			      Log.info(String.format("%s", theGroup));
            			      
            			      //add the matching urls to the links List
                	          links.add(String.format("%s", theGroup));
          	                  builder.append(String.format("%s", theGroup));
          	                  
          	                  if (links.isEmpty()) {
          	                	  
              			    	Log.info("Url matcher failed!");
              			    	Log.info("Urls will be returned unfiltered from dataToBeReturned List!");
              			    	
              					return dataToBeReturned.iterator();
          	                	  
          	                  }
            			    }
        			    
                        } catch (Exception e) {
                        		                      		
            			    	Log.info("Url matcher failed!");
            			    	Log.info("Urls will be returned unfiltered from dataToBeReturned List!");
            			    	Log.info(e.getMessage());
            			    	
            					return dataToBeReturned.iterator();

                        	}                      
                  }
                
    	        }	return links.iterator();

	    }
	    
 public static List<String> contentsList() {
	    	
	    	List<String> dataToBeReturned = new ArrayList<String>();	
            List<String> links = new ArrayList<String> ();
            StringBuilder builder = new StringBuilder();

        	List<WebElement> findElements;
        	findElements = driver.findElement(By.id("normalView")).findElements(By.tagName("a"));
        		   
	            for (WebElement webElement : findElements)
	            	
	            {	
	            	String contents = webElement.getAttribute("href");
	                
	                dataToBeReturned.add(contents);
	                builder.append(contents);
	                
	            }
	                            
    	        if (dataToBeReturned.isEmpty()) {
    	        	System.out.println("No contents in the Category!!");
    	        	Log.info("No contents in the Category!!");
    	        	Reporter.log("No contents in the Category!!");
    	        }
    	        
    	        else if (!dataToBeReturned.isEmpty()) {
    	        		        		                
                	for(String contentlist : dataToBeReturned) {	 

                        Log.info(contentlist);
                        
                        try {	
              			   
                        	String stringToSearch = contentlist;
            			    
            			    //*** the pattern we want to search for
            			    Pattern p = Pattern.compile(BaseUrls.PLAYER.toString());
            			    
            			    Matcher m = p.matcher(stringToSearch);
            			 
            			    // if we find a match, get the group
            			    if (m.find())
            			    {
            			      // we're only looking for one group, so get it
            			      String theGroup = m.group(1);
            			       
            			      // print and log the group out for verification
            			      System.out.format("%s", theGroup);
            			      
            			      Log.info(String.format("%s", theGroup));
            			      
            			      //add the matching urls to the links List
                	          links.add(String.format("%s", theGroup));
          	                  builder.append(String.format("%s", theGroup));
          	                  
          	                  if (links.isEmpty()) {
          	                	  
              			    	Log.info("Url matcher failed!");
              			    	Log.info("Urls will be returned unfiltered from dataToBeReturned List!");
              			    	
              					return dataToBeReturned;
          	                	  
          	                  }
            			    }
        			    
                        } catch (Exception e) {
                        		                      		
            			    	Log.info("Url matcher failed!");
            			    	Log.info("Urls will be returned unfiltered from dataToBeReturned List!");
            			    	Log.info(e.getMessage());
            			    	
            					return dataToBeReturned;

                        	}                      
                  }
                
    	        }	return links;

	    }
	    
	    
	 /**
	  * Get the current time in SimpleDateFormat.
	  * 
	  * @return "MMM dd,yyyy HH:mm:ss"
	  */
	    
	public static String getCurrentTime(){
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd,yyyy HH:mm:ss");
        return sdf.format(new Date().getTime()); 
	}	
	
	  /**
	   * Url String validator. It returns false if regex matches "javascript:.*|mailto:.*".
	   *   
	   * @param s
	   * @return
	   */
	    
    public static boolean valid(String s) {
        if (s.matches("javascript:.*|mailto:.*")) {
          return false;
        }
        return true;
      }
    
    /**
     * 
     * It makes absolute url from an url and link parameter.
     * 
     * @param url
     * @param link
     * @return
     */
    
    
    public static String makeAbsolute(String url, String link) {
        if (link.matches("http://.*")) {
          return link;
        }
        if (link.matches("/.*") && url.matches(".*$[^/]")) {
          return url + "/" + link;
        }
        if (link.matches("[^/].*") && url.matches(".*[^/]")) {
          return url + "/" + link;
        }
        if (link.matches("/.*") && url.matches(".*[/]")) {
          return url + link;
        }
        if (link.matches("/.*") && url.matches(".*[^/]")) {
          return url + link;
        }
        throw new RuntimeException("Cannot make the link absolute. Url: " + url
            + " Link " + link);
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
