package main.java.qa.framework.main;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Date;

/**
 * Author: Date: 13/01/2012
 *
 * Summary: Implementation of a listener to take screen shots when using web
 * driver for tests that fail. Screen shots are saved to a screen shots folder
 * on the users Desktop in the folder /Desktop/Test-Failure-Screenshots/, must
 * have admin rights so the folder is created and saved etc.
 *
 * Dependencies: Requires an instance of the browsers web driver as this class
 * calls the WebDriverManager to get the browsers web driver instance.
 *
 */

public class CaptureScreenshotOnFailureListener extends TestListenerAdapter
		implements WebElements {
	private static final String ESCAPE_PROPERTY = "org.uncommons.reportng.escape-output";
	private static Logger Log = Logger.getLogger(Logger.class.getName());
	public static String filename;

	public static String captureScreenShot(String filename_) {

		WebDriver driver = WebDriverManager.getDriverInstance();

		/*
		 * We can only take screen shots for those browsers that support screen
		 * shot capture, html unit does not support screenshots as part of its
		 * capabilities.
		 */
		if ((driver instanceof InternetExplorerDriver)
				|| (driver instanceof FirefoxDriver)
				|| (driver instanceof ChromeDriver)
				|| (driver instanceof SafariDriver)
				|| (driver instanceof EventFiringWebDriver)) {
			// Create a calendar object so we can create a date and time for the
			// screenshot
			//Calendar calendar = Calendar.getInstance();

			// Get the users home path and append the screen shots folder
			// destination
			String workingDir = System.getProperty("user.dir");

			String screenShotsFolder = workingDir + File.separator
					+ "test-output" + File.separatorChar + "html"
					+ File.separator + "ScreenShots" + File.separator;

			Path screenShotsFolderPath = Paths.get(screenShotsFolder);

			if (screenShotsFolderPath.toFile().isDirectory()) {
				if (!screenShotsFolderPath.toFile().exists()) {
					screenShotsFolderPath.toFile().mkdir();
				}

			}

			// Create the filename for the screen shots
			filename = screenShotsFolder + WebDriverManager.getBroswer()
					+ "-" + "-" + filename_ + ".png";

			// Take the screen shot and then copy the file to the screen shot
			// folder
			File scrFile = ((TakesScreenshot) driver)
					.getScreenshotAs(OutputType.FILE);

			try {
				FileUtils.copyFile(scrFile, new File(filename));
			} catch (IOException e) {
				e.printStackTrace();
			}

			System.out.println(" *** Capture files are created in "
					+ screenShotsFolder + File.separator);
			Log.info(" *** Capture files are created in " + screenShotsFolder
					+ File.separator);

			File file = new File(filename);
			System.setProperty(ESCAPE_PROPERTY, "false");

			// TODO: pictures to be copied into the user.dir to get them
			// relative path

			String absolute = file.getAbsolutePath();
			int beginIndex = absolute.indexOf(".");
			String relative = absolute.substring(beginIndex).replace(".\\", "");
			String screenShot = relative.replace('\\', '/');

			/*
			 * String absolute = file.getAbsolutePath(); int beginIndex =
			 * absolute.indexOf("."); String relative =
			 * absolute.substring(beginIndex).replace(".\\", ""); String
			 * screenShot = absolute.replace('\\', '/');
			 */

			Reporter.log("<a href=\"" + screenShot
					+ "\"><p align=\"left\">Error screenshot at " + new Date()
					+ "</p>");
			Reporter.log("<p><img width=\"1024\" src=\""
					+ file.getAbsoluteFile() + "\" alt=\"screenshot at "
					+ new Date() + "\"/></p></a><br />");

		}
		return filename;

	}

	@Override
	public void onTestFailure(ITestResult testResult) {

		// call the superclass
		super.onTestFailure(testResult);

		// Get a driver instance from the web driver manager object
		WebDriver driver = WebDriverManager.getDriverInstance();

		/*
		 * We can only take screen shots for those browsers that support screen
		 * shot capture, html unit does not support screenshots as part of its
		 * capabilities.
		 */
		if ((driver instanceof InternetExplorerDriver)
				|| (driver instanceof FirefoxDriver)
				|| (driver instanceof ChromeDriver)
				|| (driver instanceof SafariDriver)
				|| (driver instanceof EventFiringWebDriver)) {
			// Create a calendar object so we can create a date and time for the
			// screenshot
			Calendar calendar = Calendar.getInstance();

			// Get the users home path and append the screen shots folder
			// destination
			String workingDir = System.getProperty("user.dir");

			String screenShotsFolder = workingDir + File.separator
					+ "test-output" + File.separatorChar + "html"
					+ File.separator + "ScreenShots" + File.separator;

			Path screenShotsFolderPath = Paths.get(screenShotsFolder);

			if (screenShotsFolderPath.toFile().isDirectory()) {
				if (!screenShotsFolderPath.toFile().exists()) {
					screenShotsFolderPath.toFile().mkdir();
				}

			}

			// Create the filename for the screen shots
			String filename = screenShotsFolder + WebDriverManager.getBroswer()
					+ "-" + calendar.get(Calendar.YEAR) + "-"
					+ calendar.get(Calendar.MONTH) + "-"
					+ calendar.get(Calendar.DAY_OF_MONTH) + "-"
					+ calendar.get(Calendar.HOUR_OF_DAY) + "-"
					+ calendar.get(Calendar.MINUTE) + "-"
					+ calendar.get(Calendar.SECOND) + "-"
					+ calendar.get(Calendar.MILLISECOND) + ".png";

			// TODO: pictures to be copied into the user.dir to get them
			// relative path

			// Take the screen shot and then copy the file to the screen shot
			// folder
			File scrFile = ((TakesScreenshot) driver)
					.getScreenshotAs(OutputType.FILE);

			try {
				FileUtils.copyFile(scrFile, new File(filename));
			} catch (IOException e) {
				e.printStackTrace();
			}

			System.out.println(" *** Capture files are created in "
					+ screenShotsFolder + File.separator);
			Log.info(" *** Capture files are created in " + screenShotsFolder
					+ File.separator);

			File file = new File(filename);
			System.setProperty(ESCAPE_PROPERTY, "false");

			String absolute = file.getAbsolutePath();
			// int beginIndex = absolute.indexOf(".");
			// String relative = absolute.substring(beginIndex).replace(".\\",
			// File.separator);
			// String screenShot = relative.replace('\\', '/');

			Path pathAbsolute = Paths.get(absolute);
			Path pathBase = Paths.get(screenShotsFolder);
			Path pathRelative = pathBase.relativize(pathAbsolute);
			Log.info(pathRelative);

			Reporter.log("<a href=\"" + pathRelative
					+ "\"><p align=\"left\">Error screenshot at " + new Date()
					+ "</p>");

			Reporter.log("<p><img width=\"50%\" src=\""
					+ file.getAbsoluteFile() + "\" alt=\"screenshot at "
					+ new Date() + "\"/></p></a><br />");

		} // end of if

	} // end of onTestFailure

} // enf of class

