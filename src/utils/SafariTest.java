package utils;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;



public class SafariTest {
    @Test
    public void spawnSafari() {
    	WebDriver driver = null;
        SafariOptions options = new SafariOptions();
        
        // Add an extra extension
        //String userHome = System.getProperty("user.home");
        //String extensionFolder = userHome +File.separator+"Downloads"+File.separator+"SafariDriver.safariextz";
        //options.addExtensions(new File(extensionFolder));
        options.setUseCleanSession(true);
        //options.setSkipExtensionInstallation(true);
        options.getUseCleanSession();

        // For use with SafariDriver:
        driver = new SafariDriver(options);
    	        
        driver.get("http://www.google.com");
        driver.findElement(By.name("q")).sendKeys("webdriver");
        driver.findElement(By.name("btnG")).click();
        new WebDriverWait(driver, 3)
            .until(ExpectedConditions.titleIs("webdriver - Google keresés"));
        driver.quit();
    }
}