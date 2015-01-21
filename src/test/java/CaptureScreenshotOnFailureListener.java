package test.java;


import com.opera.core.systems.OperaDesktopDriver;
import com.thoughtworks.selenium.Selenium;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Author: 
 * Date: 13/01/2012
 *
 * Summary: 
 * Implementation of a listener to take screen shots when using web driver for tests that fail. Screen shots
 * are saved to a screen shots folder on the users Desktop in the folder /Desktop/Test-Failure-Screenshots/, must
 * have admin rights so the folder is created and saved etc.
 *
 * Dependencies:
 * Requires an instance of the browsers web driver as this class calls the WebDriverManager to get the 
 * browsers web driver instance.
 *
 */

public class CaptureScreenshotOnFailureListener extends TestListenerAdapter
{
	
	@Override
    public void onTestFailure (ITestResult testResult)
    {
        
		// call the superclass
        super.onTestFailure(testResult);

        // Get a driver instance from the web driver manager object
        WebDriver driver =  WebDriverManager.getDriverInstance();

        /*
         * We can only take screen shots for those browsers that support screen shot capture, html unit
         * does not support screenshots as part of its capabilities.
         */
        if ( (driver instanceof InternetExplorerDriver) || (driver instanceof FirefoxDriver)
                                                                       || (driver instanceof ChromeDriver)
                                                                       || (driver instanceof OperaDesktopDriver))
        {
            // Create a calendar object so we can create a date and time for the screenshot
            Calendar calendar = Calendar.getInstance();
            
            // Get the users home path and append the screen shots folder destination
            String userHome = System.getProperty("user.home");
            String screenShotsFolder = userHome + "/Documents/Tests/";

            // The file includes the the test method and the test class
            String testMethodAndTestClass = testResult.getMethod().getMethodName() + "(" + testResult.getTestClass().getName() + ")";

            System.out.println(" *** Capture files are created in Documents/Tests folder for the Test \n" + testMethodAndTestClass );

            // Create the filename for the screen shots
            String filename = screenShotsFolder + WebDriverManager.getBroswer() + "-"
                                                + testMethodAndTestClass + "-"
                                                + calendar.get(Calendar.YEAR) + "-"
                                                + calendar.get(Calendar.MONTH) + "-"
                                                + calendar.get(Calendar.DAY_OF_MONTH) + "-"
                                                + calendar.get(Calendar.HOUR_OF_DAY) + "-"
                                                + calendar.get(Calendar.MINUTE) + "-"
                                                + calendar.get(Calendar.SECOND) + "-"
                                                + calendar.get(Calendar.MILLISECOND)
                                                + ".png";

            // Take the screen shot and then copy the file to the screen shot folder
            File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);

            try  {
                FileUtils.copyFile(scrFile, new File(filename));
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }

        } // end of if

    } // end of onTestFailure
   




	protected void reportLogScreenshot(File file) {
        System.setProperty("org.uncommons.reportng.escape-output", "false");
    
        String absolute = file.getAbsolutePath();
        int beginIndex = absolute.indexOf(".");
        String relative = absolute.substring(beginIndex).replace(".\\","");
        String screenShot = relative.replace('\\','/');
    
    
  Reporter.log("<a href=\"" + screenShot + "\"><p align=\"left\">Error screenshot at " + new Date()+ "</p>");
  Reporter.log("<p><img width=\"1024\" src=\"" + file.getAbsoluteFile()  + "\" alt=\"screenshot at " + new Date()+ "\"/></p></a><br />"); 
  }


} // enf of class

