package main.java.qa.framework.pageObjects;

import java.io.FileOutputStream;

import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.client.ClientUtil;
import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.proxy.CaptureType;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import main.java.qa.framework.main.TestBase;
import main.java.qa.framework.main.WebElements;

public class PerformanceTest extends TestBase implements WebElements {

public PerformanceTest performanceTest() throws Exception {

    // start the proxy
    BrowserMobProxy proxy = new BrowserMobProxyServer();
    proxy.start(0);

    // get the Selenium proxy object
    Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxy);

    // configure it as a desired capability
    DesiredCapabilities capabilities = new DesiredCapabilities();
    capabilities.setCapability(CapabilityType.PROXY, seleniumProxy);

    // enable more detailed HAR capture, if desired (see CaptureType for the complete list)
    proxy.enableHarCaptureTypes(CaptureType.REQUEST_CONTENT, CaptureType.RESPONSE_CONTENT);

    // create a new HAR with the label "yahoo.com"
    proxy.newHar("assertselenium.com");

	// open yahoo.com
	driver.get("http://assertselenium.com");


	driver.get("http://assertselenium.com/2012/10/30/transformation-from-manual-tester-to-a-selenium-webdriver-automation-specialist/");


	// get the HAR data

	Har har = proxy.getHar();

	FileOutputStream fos = new FileOutputStream("har1.har");

	har.writeTo(fos);

	proxy.stop();

	driver.quit();
	
	return new PerformanceTest();

	}

}