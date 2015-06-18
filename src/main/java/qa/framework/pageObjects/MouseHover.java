package main.java.qa.framework.pageObjects;

import main.java.qa.framework.main.Clicks;
import main.java.qa.framework.main.TestBase;
import main.java.qa.framework.testng.CustomException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class MouseHover extends TestBase {

	public MouseHover mouseHover() throws Exception, CustomException {

		Actions action = new Actions(driver);

		// driver.get("http://huvip.hbogo.eu/content/game-of-thrones-1-winter-is-coming-2012115219");

		for (int second = 0;; second++) {
			if (second >= 60)
				fail("timeout");
			try {
				if (isElementPresentAndDisplay(By.xpath(ContentDetail)))
					break;
			} catch (Exception e) {
				Log.info(e.getCause());
			}
			Thread.sleep(1000);
		}

		Thread.sleep(5000);

		WebElement selectedplaygomb = driver.findElement(By.xpath(ContentDetail));

		if (selectedplaygomb.isDisplayed()) {

			// mouse hover action
			action.moveToElement(selectedplaygomb).build().perform();
			Log.info("Mouse hove action succeeded!");

			WebElement playbuttonmenu;
			playbuttonmenu = driver.findElement(By.id("play_dropdown"));
			action.moveToElement(playbuttonmenu).perform();

			Clicks.clickContent();

		}

		else if (!selectedplaygomb.isDisplayed()) {
			throw new CustomException();
		}

		// assert if the playback got under way within 20 seconds
		TestBase.isPlayBackRunning(2, 20000, true);

		return new MouseHover();

	}
}
