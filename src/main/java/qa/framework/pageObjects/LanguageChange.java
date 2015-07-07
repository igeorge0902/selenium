package main.java.qa.framework.pageObjects;

import main.java.qa.framework.main.BaseUrls;
import main.java.qa.framework.main.CaptureScreenshotOnFailureListener;
import main.java.qa.framework.main.TestBase;
import main.java.qa.framework.main.WebElements;
import main.java.qa.framework.utils.TesseractExample;
import main.java.qa.framework.utils.WaitTool;

import org.openqa.selenium.By;

//@Listeners({ TestListeners.class, CaptureScreenshotOnFailureListener.class, TestMethodListener.class, LoggingListener.class })
public class LanguageChange extends TestBase implements WebElements {

	private static boolean _languagecheck1_;

	public LanguageChange changeLanguage(String lang1, String meta1) throws Exception {

		if (!driver.getCurrentUrl().equals(
				driver.getCurrentUrl().equals(
						BaseUrls.PLAYER.get() + OffersScreen))) {

			driver.get(BaseUrls.PLAYER.get() + OffersScreen);
		}
		
		//TestBase.isElementPresent("meta[content='"+meta1+"']");
		_languagecheck1_ = TestBase.checkMetaContent(meta1, 5);		

		if (_languagecheck1_) {

			Log.info("Language is  " + meta1);

			return new LanguageChange();

		} else if (!_languagecheck1_) {

			driver.findElement(By.id(HeaderButton)).click();
			WaitTool.waitForElement(driver, By.id(Settings), 5);
			driver.findElement(By.id(Settings)).click();

			WaitTool.waitForElement(driver, By.id(CustomerMenu), 5);
			
			TestBase.clickLinkByHref(language);
			WaitTool.waitForElement(driver, By.id(lang1), 5);
			driver.findElement(By.id(lang1)).click();
			driver.findElement(By.cssSelector("div.form_buttons > button.button_submit")).click();

			WaitTool.waitForElement(driver,	By.cssSelector("div.form_buttons > button.button_submit") ,5);

			TestBase.checkMetaContent(meta1, 5);		
			String screenShot = CaptureScreenshotOnFailureListener.captureScreenShot(meta1);
			String[] arguments = new String[] {screenShot}; 
			TesseractExample.main(arguments);


		}

		return new LanguageChange();
	}

	public static String getLanguage() {
		return language;
	}
}
