package main.java.qa.framework.pageObjects;

import main.java.qa.framework.main.BaseUrls;
import main.java.qa.framework.main.CaptureScreenshotOnFailureListener;
import main.java.qa.framework.main.TestBase;
import main.java.qa.framework.main.WebElements;
import main.java.qa.framework.utils.WaitTool;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

//@Listeners({ TestListeners.class, CaptureScreenshotOnFailureListener.class, TestMethodListener.class, LoggingListener.class })
public class LanguageChange extends TestBase implements WebElements {

	public LanguageChange(WebDriver driver) {
		super(driver);
	}
	
	private static boolean _languagecheck1_;
	private static boolean _languagecheck2_;
	
	public LanguageChange changeLanguage(String string, String string2, String string3, String string4) throws Exception {
			
				if (!driver.getCurrentUrl().equals(
				driver.getCurrentUrl().equals(
						BaseUrls.PLAYER.get() + OffersScreen))) {

			driver.get(BaseUrls.PLAYER.get() + OffersScreen);
		}

			_languagecheck1_ = driver.getPageSource().contains(string3.toLowerCase()) 
							|| driver.getPageSource().contains(string3.toUpperCase());			

			if (_languagecheck1_) {
			
				Log.info("Language is  " + string3);
			
			
				return new LanguageChange(driver);

			} else if (!_languagecheck1_) {

				driver.findElement(By.id(HeaderButton)).click();
				//TestBase.isElementPresent(By.id(Settings));
				driver.findElement(By.id(Settings)).click();
				TestBase.assertEquals(driver.findElement(By.id(CustomerMenu))
						.isDisplayed(), driver.findElement(By.id(CustomerMenu))
						.isDisplayed());
				TestBase.clickLinkByHref(language);

				driver.findElement(By.id(string2)).click();
				driver.findElement(By.cssSelector("div.form_buttons > button.button_submit"))
						.click();
				
				WaitTool.waitForElement(driver, By.cssSelector("div.form_buttons > button.button_submit"), 10);				
				CaptureScreenshotOnFailureListener.captureScreenShot();
				
				driver.findElement(By.id(string)).click();
				driver.findElement(By.cssSelector("div.form_buttons > button.button_submit"))
						.click();

				WaitTool.waitForElement(driver,
						By.cssSelector("div.form_buttons > button.button_submit"), 10);		

				_languagecheck2_ = driver.getPageSource().contains(string3.toLowerCase()) 
								|| driver.getPageSource().contains(string3.toUpperCase());
				
				TestBase.assertTrue(_languagecheck2_);
				CaptureScreenshotOnFailureListener.captureScreenShot();

			}

		return new LanguageChange(driver);
	}
	
	public static String getLanguage() {
		return language;
	}
}
