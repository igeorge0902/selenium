package test.java;

import static com.unitedinternet.portal.selenium.utils.logging.LoggingAssert.assertEquals;
import static com.unitedinternet.portal.selenium.utils.logging.LoggingAssert.assertTrue;

import static org.junit.Assert.*;

import com.thoughtworks.selenium.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;

import com.unitedinternet.portal.selenium.utils.logging.HtmlResultFormatter;
import com.unitedinternet.portal.selenium.utils.logging.LoggingCommandProcessor;
import com.unitedinternet.portal.selenium.utils.logging.LoggingDefaultSelenium;
import com.unitedinternet.portal.selenium.utils.logging.LoggingResultsFormatter;
import com.unitedinternet.portal.selenium.utils.logging.LoggingSelenium;
import com.unitedinternet.portal.selenium.utils.logging.LoggingUtils;

import br.eti.kinoshita.testlinkjavaapi.*;

import testlink.api.java.client.TestLinkAPIException;
import testlink.api.java.client.tc.autoexec.TestPlan;
import testlink.api.java.client.TestLinkAPIClient;
import testlink.api.java.client.TestLinkAPIException;
import testlink.api.java.client.tc.autoexec.TestCase;
import testlink.api.java.client.tc.autoexec.TestCaseExecutor;
import testlink.api.java.client.tc.autoexec.TestPlan;
import testlink.api.java.client.tc.autoexec.TestPlanPrepare;


import org.dbfacade.testlink.eclipse.plugin.*;



@SuppressWarnings("unused")
public class TestRedmineLogin {
	
	 protected LoggingSelenium selenium;
	 	 
     private BufferedWriter loggingWriter;

     private static final String RESULT_FILE_ENCODING = "UTF-8";

     private static final String DEFAULT_TIMEOUT = "500000";

     private static final String TEBA_URL = "http://redmine.local";

     private static final String SCREENSHOT_PATH = "Screenshots";
  
     private final String RESULTS_BASE_PATH = "/Users/georgegaspar/Documents" + File.separator + "loggingResults";

     private String resultsPath = new File(RESULTS_BASE_PATH).getAbsolutePath();

     private String screenshotsResultsPath = new File(RESULTS_BASE_PATH + File.separator + SCREENSHOT_PATH).getAbsolutePath();
	
     @Before
	public void setUp() { //throws Exception {
		//selenium = (LoggingSelenium) new DefaultSelenium("localhost", 4444, "*firefox", "https://tsso.teba.gov.hu/");
		//selenium.start();
		
		if (!new File(screenshotsResultsPath).exists()) {
             new File(screenshotsResultsPath).mkdirs();
          }
          // to keep every result use a timestamped filename like this
          final String resultHtmlFileName = resultsPath
           + File.separator
           + "autorunResult"
           + LoggingUtils.timeStampForFileName()
           + ".html";
		
         // final String resultHtmlFileName = resultsPath + File.separator + "sampleResultJunitAssertionFailed.html";
         //System.err.println("resultHtmlFileName=" + resultHtmlFileName);
  
          loggingWriter = LoggingUtils.createWriter(resultHtmlFileName,
                  TestRedmineLogin.RESULT_FILE_ENCODING, true);
  
          LoggingResultsFormatter htmlFormatter = new HtmlResultFormatter(loggingWriter,
        		  TestRedmineLogin.RESULT_FILE_ENCODING);
          htmlFormatter.setScreenShotBaseUri(TestRedmineLogin.SCREENSHOT_PATH + "/"); 
          
          // has to be "/" as this is a URI
          
          htmlFormatter.setAutomaticScreenshotPath(screenshotsResultsPath);
          LoggingCommandProcessor myProcessor = new LoggingCommandProcessor(new HttpCommandProcessor("localhost", 4444, "*firefox",
                  TEBA_URL), htmlFormatter);
          myProcessor.setExcludedCommands(new String[] {});
          selenium = new LoggingDefaultSelenium(myProcessor);
          
          selenium.start();
	}
    
     public void CreateNewInsatnce() throws TestLinkAPIException{
    	 
     String url = "https://localhost/testlink/lib/api/xmlrpc.php";
     String devKey = "962b935af54b4516f6124bd311c143dc";
     TestLinkAPI api = null;
     
     URL testlinkURL = null;
     
     try
     {
             testlinkURL = new URL(url);
     }
     catch ( MalformedURLException mue )
     {
             mue.printStackTrace( System.err );
             System.exit(-1);
     }
     
     api = new TestLinkAPI(testlinkURL, devKey);
     
     
     System.out.println(api.ping());
     
     }
     
     public interface TestPlanPrepare
     {
           /**
            * Optionally made available by callers to the interface
            *
            * @param directory
            */
           public void setExternalPath(String path);
           /**
            * Optionally made available by callers to the interface
            *
            * @param user
            */
           public void setTCUser(String igeorge1982);
           /**
            * Make changes to the contents of the test plan and test cases.
            *
            * @param plan
            * @return A test plan which has had the executors set for each test case.
            *
            */
           public TestPlan adjust(
                 TestLinkAPIClient apiClient,
                 TestPlan plan) throws TestLinkAPIException;
     }
     
     public interface TestCaseExecutor
     {
           // Results
           public static final short RESULT_UNKNOWN = -1;
           public static final short RESULT_PASSED = 0;
           public static final short RESULT_FAILED = 1;
           public static final short RESULT_BLOCKED = 2;
           // States
           public static final short STATE_READY = 100;
           public static final short STATE_RUNNING = 101;
           public static final short STATE_BOMBED = 102;
           public static final short STATE_COMPLETED = 103;
           public static final short STATE_RESET = 104;
           /**
            * Get the state of the execution
            *
            * @return
            */
           public short getExecutionState();
           /**
            * Set the new state of the executor
            *
            * @param newState
            */
           public void setExecutionState(
                 short newState);
           /**
            * Return the result state of the test case execution.
            *
            * @return The result of the test case. Implementers should set the initial
     status to UNKNOWN.
            */
           public short getExecutionResult();
           /**
            * Set the results of the test from an external source.
            *
            * @param result
            */
   
     }
     

	@Test /*(expected=AssertionError.class)*/
	public void testRedmineLogin() throws InterruptedException, ParseException {
        selenium.setContext("MyFirstTestcase()");
        
		selenium.open("/redmine");

		selenium.captureScreenshot(screenshotsResultsPath
                 + File.separator
                 + "redmine_test"
                 + LoggingUtils.timeStampForFileName()
                 + ".png");
		
		assertEquals("Expected page title not found", "Redmine", selenium.getTitle(), selenium);
		
		selenium.click("css=a.login");
		selenium.waitForPageToLoad(DEFAULT_TIMEOUT);
		selenium.type("id=username", "gygaspar");
		selenium.type("id=password", "Bugsbunny666");
		selenium.click("name=login");
		selenium.waitForPageToLoad(DEFAULT_TIMEOUT);
		
		/*for (int second = 0;; second++) {
			if (second >= 60) fail("timeout");
			try { if (selenium.isCookiePresent("_redmine_session=")) 
				
				break; } catch (Exception e) {}
			Thread.sleep(1000);
			
		}
	    */

		for (int second = 0;; second++) {
			if (second >= 120) fail("timeout");
			try { if (selenium.isVisible("css=a.logout")) 
				
			   break; } catch (Exception e) {}	
			Thread.sleep(10000);
			
		}
		

		
/*
		int second = 0;
		while(second > 120 && !selenium.isVisible("css=button.x-btn-text")){
			Thread.sleep(1000);
			second++;
		}
*/
		
		assertTrue("not found", selenium.isVisible("css=a.logout"), selenium);
	

		
        //If element is available on page then perform click operation.
		
		if(selenium.isVisible("css=a.logout")) {
		   selenium.click("css=a.logout");
	       } else {
		    System.out.printf( "Element is not available on page." );}
		 
		
	
		
	        
//		public void safeClick(String elementLocator) { 
//			if(selenium.isVisible("css=button.x-btn-text")) {
//				selenium.click("css=button.x-btn-text"); 
//			} else {
				// Using the TestNG API for logging
				//Reporter.log( "Element: " +elementLocator + selenium.getLocation());
//			}
		

		//selenium.click("css=button.x-btn-text");
	    
		
		selenium.captureScreenshot(screenshotsResultsPath
                 + File.separator
                 + "redmine"
                 + LoggingUtils.timeStampForFileName() 
                 + ".png");
	}

	@After
	public void tearDown() throws Exception {
		selenium.stop();
		try {
            if (null != loggingWriter) {
                loggingWriter.close();
            }
        } catch (IOException e) {
            // do nothing
        }
		
		
    }
}
