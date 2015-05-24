package main.java.qa.framework.pageObjects;

import main.java.qa.framework.main.BaseUrls;
import main.java.qa.framework.main.CaptureScreenshotOnFailureListener;
import main.java.qa.framework.main.TestBase;
import main.java.qa.framework.main.WebElements;
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
			driver.findElement(By.id(Settings)).click();

			TestBase.assertEquals(driver.findElement(By.id(CustomerMenu))
					.isDisplayed(), driver.findElement(By.id(CustomerMenu))
					.isDisplayed());
			
			TestBase.clickLinkByHref(language);

			driver.findElement(By.id(lang1)).click();
			driver.findElement(
					By.cssSelector("div.form_buttons > button.button_submit"))
					.click();

			WaitTool.waitForElement(driver,
					By.cssSelector("div.form_buttons > button.button_submit"),
					10);

			TestBase.checkMetaContent(meta1, 5);		
			CaptureScreenshotOnFailureListener.captureScreenShot();

		}

		return new LanguageChange();
	}

	public static String getLanguage() {
		return language;
	}
}
