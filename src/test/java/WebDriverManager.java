package test.java;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Parameters;

import com.opera.core.systems.OperaDriver;

/**
 * Author: anthony.kearns
 * Date: 13/01/2012
 *
 * Summary:
 * Implementation of a web driver manager static class that is used to start and stop web driver for different
 * browser implementations.
 *
 * Dependencies:
 * To use the Chrome browser driver the user must download and setup the chrome browser driver locally.
 *
 */

public class WebDriverManager
{
    static WebDriver driver = null;
    private static String browser = null;
    private static Logger Log = Logger.getLogger(Logger.class.getName());


     // Default constructor, no need to extend this just use as a static
    public WebDriverManager()   {
    	
    	
    }

    /**
     * Static method for starting a webdriver, defaults  the wait time to 30 seconds and the browser to
     * the firefox driver.
     */
    public static WebDriver startDriver(String browser, int timeout)
    {

    	WebDriverManager.browser = browser;
        
        DOMConfigurator.configure("log4j.xml");
        Log.info("New driver instantiated");

        /*
        * Determine what browser were using and start the appropiate driver instance
        */
        if ( browser.equalsIgnoreCase("INTERNET_EXPLORER") )
        {
            System.out.println("browser : "+ browser);

            // start a internet explorer driver instance
            driver = new InternetExplorerDriver();

            driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);

            // open the url
            //driver.get(portalUrl);

            driver.manage().deleteAllCookies();
            new Actions(driver).keyDown(Keys.CONTROL).sendKeys(Keys.F5).keyUp(Keys.CONTROL).perform();
 
            // click the override link if it exists
            try
            {
                driver.navigate().to("javascript:document.getElementById('overridelink').click()");
            }
            catch (Exception e) {
                // do nothing as this exception is expected if no security ssl cert issue
            }

        }
        else if ( browser.equalsIgnoreCase("HTML_UNIT") )
        {
            System.out.println("browser : "+ browser);

            // start a html unit driver instance and enable javascript
            driver = new HtmlUnitDriver(true);

            driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);

            // open the url
            //driver.get(portalUrl);

            driver.manage().deleteAllCookies();

            new Actions(driver).keyDown(Keys.CONTROL).sendKeys(Keys.F5).keyUp(Keys.CONTROL).perform();

        }
        /**
         *  Installation and configuration steps for the Chrome driver.....
         *
         *  1. Go to http://code.google.com/p/chromium/downloads/list and download the latest chromedriver for windows
         *  2. Extract the chromedriver.exe to a location of your choice
         *  3. Create a webdriver.chrome.driver environment variable and set the path to the chromedriver.exe including
         *     the chromedriver.exe in the path.
         */
        else if ( browser.equalsIgnoreCase("CHROME") )
        {
            System.out.println("browser :"+ browser);

            /**
             * Chrome web driver requires a webdriver.chrome.driver property to be set to the path of the
             * chrome driver exe so create a webdriver.chrome.driver environment variable and determine
             * where the driver exe resides for each machine instance via this variable.
             * Use the environment variable to then set the property.
             */
            try
            {
                String chromeDriverPath = System.getenv( "webdriver.chrome.driver" );
                System.setProperty("webdriver.chrome.driver", chromeDriverPath);
            }
            catch (Exception ex)
            {
                System.out.println("\nException in getting and setting the webdriver chrome driver: "
                                                    + ex.getMessage() + ex.getClass() );
                ex.printStackTrace();
            }
            /*
             * Configure chrome to ignore the untrusted certificate error for secure ssl. More information can
             * be found here...
             *
             * http://code.google.com/p/selenium/wiki/ChromeDriver
             */
            ChromeOptions options = new ChromeOptions();
            options.addArguments(Arrays.asList(new String[]{"--ignore-certificate-errors", "--start-maximized"}));

            driver = new ChromeDriver(options);

            driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);

            // open the url
            //driver.get(portalUrl);

            driver.manage().deleteAllCookies();

            new Actions(driver).keyDown(Keys.CONTROL).sendKeys(Keys.F5).keyUp(Keys.CONTROL).perform();
        }
        else if ( browser.equalsIgnoreCase("OPERA") )
        {
            System.out.println("browser :"+ browser);

            DesiredCapabilities operaCapabilities = DesiredCapabilities.opera();
            operaCapabilities.setJavascriptEnabled(true);
            operaCapabilities.setCapability("-autotestmode", true);

            driver = new OperaDriver(operaCapabilities);

            driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);

            // open the url
            //driver.get(portalUrl);

            driver.manage().deleteAllCookies();

            new Actions(driver).keyDown(Keys.CONTROL).sendKeys(Keys.F5).keyUp(Keys.CONTROL).perform();

        }
        else
        {
            System.out.println("browser : Firefox (Default)\n");

            // start a firefox driver instance
            driver = new FirefoxDriver();

            driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);

            // open the url
            //driver.get(portalUrl);

            driver.manage().deleteAllCookies();

            new Actions(driver).keyDown(Keys.CONTROL).sendKeys(Keys.F5).keyUp(Keys.CONTROL).perform();

        }

        // return a reference to the static web driver instance started
        return driver;
    }


    /**
     * Stops the browser driver started
     *
     * @param - the instance of the driver to stop
     */
@Parameters
 public static void stopDriver()
    {
        driver.quit();
    }

    public static WebDriver getDriverInstance()
    {
        return driver;
    }

    /**
     * Getter method for the current browser being used for testing
     * @return the browser string being used for testing
     */
    public static String getBroswer()
    {
        return browser;
    }


   
   

} //end class