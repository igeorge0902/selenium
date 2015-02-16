package utils;

import java.io.File;

import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.testng.annotations.Test;



public class SafariTest {
    @Test
    public void spawnSafari() {
    	
        SafariOptions options = new SafariOptions();
        
        // Add an extra extension
        String userHome = System.getProperty("user.home");
        String extensionFolder = userHome +File.separator+"Downloads"+File.separator+"SafariDriver.safariextz";
        options.addExtensions(new File(extensionFolder));

        // For use with SafariDriver:
        SafariDriver driver = new SafariDriver(options);
    	
        //SafariDriver driver = new SafariDriver();
        
        driver.get("http://www.google.com");
        driver.quit();
    }
}