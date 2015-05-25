package main.java.qa.framework.main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.Arrays;

import main.java.qa.framework.utils.PropertyUtils;
import main.java.qa.framework.utils.WaitTool;
import net.lightbody.bmp.proxy.ProxyServer;

import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.Platform;

/**
 * Author: anthony.kearns Date: 13/01/2012
 *
 * Summary: Implementation of a web driver manager static class that is used to
 * start and stop web driver for different browser implementations.
 *
 * Dependencies: To use the Chrome browser driver the user must download and
 * setup the chrome browser driver locally.
 *
 */

public class WebDriverManager extends TestBase implements WebElements {
	public static WebDriver driver = null;
	private static String browser = null;
	static Boolean setDownstreamMaxKB;
	static Boolean setDownStreamKbps;

	// Default constructor, no need to extend this just use as a static
	public WebDriverManager() {

	}

	/**
	 * Static method for starting a webdriver, defaults the wait time to 30
	 * seconds and the browser to the firefox driver.
	 * 
	 * @param url
	 * @throws Exception
	 */
	public static WebDriver startDriver(String browser, String portalUrl, int timeout) throws Exception {

		WebDriverManager.browser = browser;
		DOMConfigurator.configure(log4jxml);
		PropertyConfigurator.configure(log4jProperties);
		Log.info("New driver instantiated");
		Log.info(Platform.getCurrent());
		Log.info(getBroswer());

		/*
		 * Determine what browser were using and start the appropiate driver
		 * instance
		 */
		if (browser.equalsIgnoreCase("INTERNET_EXPLORER")) {

			System.out.println("browser : " + browser);

			TestBase.assertFalse(TestBase.isSupportedPlatformMac(false));
			
			String IEDriverPath = Paths.get("lib/IEDriverServer32.exe").toFile().toString();
			System.setProperty("webdriver.ie.driver", IEDriverPath);

			DesiredCapabilities Capabilities = DesiredCapabilities.internetExplorer();
			Capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,	true);
			Capabilities.isJavascriptEnabled();
			Capabilities.setVersion("11");
			Capabilities.getVersion();
			Capabilities.equals(browser);
			Capabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
			Capabilities.setCapability(InternetExplorerDriver.ENABLE_ELEMENT_CACHE_CLEANUP, true);
			Capabilities.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, false);

			// start a internet explorer driver instance
			driver = new InternetExplorerDriver(Capabilities);

			driver = driverEventListener(Capabilities);
			Log.info(browser + "driver initialized with eventListeners");
			Log.info(Capabilities.getVersion());
			WaitTool.setImplicitWait(driver, timeout);

			driver.get(portalUrl);
			driver.manage().deleteAllCookies();
			driver.manage().window().maximize();

			new Actions(driver).keyDown(Keys.CONTROL).sendKeys(Keys.F5).keyUp(Keys.CONTROL).perform();

			/*
			 * // click the override link if it exists try {
			 * driver.navigate().to
			 * ("javascript:document.getElementById('overridelink').click()"); }
			 * catch (Exception e) { // do nothing as this exception is expected
			 * if no security ssl cert issue }
			 */
		}

		/**
		 * Installation and configuration steps for the Chrome driver.....
		 *
		 * 1. Go to http://code.google.com/p/chromium/downloads/list and
		 * download the latest chromedriver for windows 2. Extract the
		 * chromedriver.exe to a location of your choice 3. Create a
		 * webdriver.chrome.driver environment variable and set the path to the
		 * chromedriver.exe including the chromedriver.exe in the path.
		 */
		else if (browser.equalsIgnoreCase("CHROME")) {
			System.out.println("browser :" + browser);

			/**
			 * Chrome web driver requires a webdriver.chrome.driver property to
			 * be set to the path of the chrome driver exe so create a
			 * webdriver.chrome.driver environment variable and determine where
			 * the driver exe resides for each machine instance via this
			 * variable. Use the environment variable to then set the property.
			 */

			try {

				if (!TestBase.isSupportedPlatformMac(true)) {

					// String userHome = System.getProperty("user.home");
					String chromeDriverPath = Paths.get("lib/chromedriver.exe").toFile().toString();
					System.setProperty("webdriver.chrome.driver", chromeDriverPath);
				}

				else if (TestBase.isSupportedPlatformMac(true)) {

					String chromeDriverPath = Paths.get("lib/chromedriver").toFile().toString();
					System.setProperty("webdriver.chrome.driver", chromeDriverPath);
				}

			}

			catch (Exception ex) {
				System.out.println("\nException in getting and setting the webdriver chrome driver: "
								+ ex.getMessage() + ex.getClass());
				ex.printStackTrace();
			}
			/*
			 * Configure chrome to ignore the untrusted certificate error for
			 * secure ssl. More information can be found here...
			 * 
			 * http://code.google.com/p/selenium/wiki/ChromeDriver
			 */
			
			String chromeProfile = "";
			if (!TestBase.isSupportedPlatformMac(true)) {
				PropertyUtils.loadPropertyFile(proprtyFile);
				String chromeProfileWin = PropertyUtils.getProperty("chromeProfileWin");
				chromeProfile = chromeProfileWin;
			}

			else if (TestBase.isSupportedPlatformMac(true)) {
				PropertyUtils.loadPropertyFile(proprtyFile);
				String chromeProfileMac = PropertyUtils.getProperty("chromeProfileMac");
				chromeProfile = chromeProfileMac;
			}
			
			ChromeOptions options = new ChromeOptions();
			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			capabilities.setCapability(ChromeOptions.CAPABILITY, options);
			capabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, "ignore");
			capabilities.setCapability(CapabilityType.SUPPORTS_APPLICATION_CACHE, false);

			options.addArguments(Arrays.asList(new String[] {"--use-gpu-in-tests" ,"--clear-token-service" ,"user-data-dir=" + chromeProfile,"--clear-data-reduction-proxy-data-savings","--ignore-certificate-errors", "--start-maximized" }));	

			driver = new ChromeDriver(capabilities);

			driver = driverEventListener(capabilities);
			Log.info(browser + " driver initialized with eventListeners");

			WaitTool.setImplicitWait(driver, timeout);

			// open the url
			driver.get(portalUrl);
			driver.manage().window().maximize();

			driver.manage().deleteAllCookies();

			new Actions(driver).keyDown(Keys.CONTROL).sendKeys(Keys.F5)
					.keyUp(Keys.CONTROL).perform();

		} else if (browser.equalsIgnoreCase("Firefox")) {
			System.out.println("browser :" + browser);

			// Use specific Firefox profile
			ProfilesIni profilesIni = new ProfilesIni();
			FirefoxProfile profile = profilesIni.getProfile("Test");
			server.start();
			
			//captures the mouse movements and navigations
			server.setCaptureHeaders(true);
			server.setCaptureContent(true);
						
			server.getStreamManager().enable();
			
			if (setDownstreamMaxKB = Boolean.valueOf(PropertyUtils.getProperty("setDownstreamMaxKB"))) {
				Log.info("setDownstreamMaxKB: "+setDownstreamMaxKB);
			server.getStreamManager().setDownstreamMaxKB(Integer.parseInt(PropertyUtils.getProperty("downStreamMaxKB")));
			Log.info("Remaining DownStreamKB: "+server.getStreamManager().getRemainingDownstreamKB());		
			Log.info("Maximum DownStreamKB: "+server.getStreamManager().getMaxDownstreamKB());
			}
			
			if (setDownStreamKbps = Boolean.valueOf(PropertyUtils.getProperty("setDownStreamKbps"))) {
				Log.info("setDownStreamKbps: "+setDownStreamKbps);
			server.getStreamManager().setDownstreamKbps(Integer.parseInt(PropertyUtils.getProperty("downStreamKbps")));
			Log.info("DownStream speed is set to: " + Integer.parseInt(PropertyUtils.getProperty("downStreamKbps")));
			}
			
		    // get the Selenium proxy object
		    Proxy proxy = server.seleniumProxy();
			
		    String path = Paths.get("har.har").toString();
			TestBase.deleteFile(path);
		    
			// configure it as a desired capability
		    DesiredCapabilities Capabilities = new DesiredCapabilities();
		    Capabilities.setCapability(CapabilityType.PROXY, proxy);
		    Capabilities.setCapability(FirefoxDriver.PROFILE, profile);

			driver = new FirefoxDriver(Capabilities);
			
			driver = driverEventListener(Capabilities);
			Log.info(browser + " driver initialized with eventListeners");

			WaitTool.setImplicitWait(driver, timeout);
			server.newHar(BaseUrls.PLAYER.get());

			driver.get(portalUrl);
			
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();			
			
			new Actions(driver).keyDown(Keys.CONTROL).sendKeys(Keys.F5).keyUp(Keys.CONTROL).perform();
			

		} else {
			System.out.println("browser : Safari (Default)\n");

			TestBase.assertTrue(TestBase.isSupportedPlatformMac(true));

			new DesiredCapabilities();
			DesiredCapabilities Capabilities = DesiredCapabilities.safari();
			SafariOptions options = new SafariOptions();

			Capabilities.setCapability("nativeEvents", false);
			Capabilities.setCapability("unexpectedAlertBehaviour", "accept");
			Capabilities.setCapability("acceptSslCerts", true);
			Capabilities.setCapability(SafariOptions.CAPABILITY, options);
			Capabilities.setCapability(CapabilityType.HAS_NATIVE_EVENTS, false);
			
			options.setUseCleanSession(true);
			
			// For use with SafariDriver:
			driver = new SafariDriver(Capabilities);

			driver = driverEventListener(Capabilities);
			Log.info(browser + "driver initialized with eventListeners");

			WaitTool.setImplicitWait(driver, timeout);

			driver.get(portalUrl);
			driver.manage().deleteAllCookies();

		}

		// return a reference to the static web driver instance started
		return driver;
	}

	private static WebDriver driverEventListener(Object object) {

		EventFiringWebDriver eventFiringDriver = new EventFiringWebDriver(
				driver);
		EventListener eventListener = new EventListener(driver);
		eventFiringDriver.register(eventListener);

		return driver = new EventFiringWebDriver(eventFiringDriver);
	}

	public static WebDriver unregister(Object object) {

		EventFiringWebDriver eventFiringDriver = new EventFiringWebDriver(
				driver);
		EventListener eventListener = new EventListener(driver);

		return driver = eventFiringDriver.unregister(eventListener);

	}
	
	
	public static ProxyServer serverStart() {
		
		ProxyServer server = new ProxyServer(9090);
		return server;
	}
	
	protected static PrintWriter createWriter(String string) throws IOException {
		new File(string);
		return new PrintWriter(new BufferedWriter(new FileWriter(new File(string))));
	}

	/**
	 * Stops the browser driver started
	 *
	 * @param - the instance of the driver to stop
	 * @throws Exception 
	 */
	public static void stopDriver() {
		driver.close();
		unregister(driver);
		Log.info("Driver closed afterInvocation");
		System.out.println("eventListeners unregistered");

	}

	public static WebDriver getDriverInstance() {
		return driver;
	}

	/**
	 * Getter method for the current browser being used for testing
	 * 
	 * @return the browser string being used for testing
	 */
	public static String getBroswer() {
		return browser;
	}


} // end class