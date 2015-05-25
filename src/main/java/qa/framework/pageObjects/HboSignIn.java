package main.java.qa.framework.pageObjects;

import main.java.qa.framework.main.BaseUrls;
import main.java.qa.framework.main.CaptureScreenshotOnFailureListener;
import main.java.qa.framework.main.TestBase;
import main.java.qa.framework.main.WebElements;
import main.java.qa.framework.utils.PropertyUtils;
import main.java.qa.framework.utils.WaitTool;

import org.openqa.selenium.By;
import org.testng.Reporter;

public class HboSignIn extends TestBase implements WebElements {

	private static String email = PropertyUtils.getProperty("email");
	private static String password = PropertyUtils.getProperty("password");
	protected boolean _languagecheck_;

	public HboSignIn selectOperator(String operator) throws Exception {

		driver.get(BaseUrls.PLAYER.get() + OffersScreen);
		Log.info(BaseUrls.PLAYER.get() + OffersScreen);

		for (int second = 0;; second++) {
			if (second >= 60)
				fail("timeout");
			try {
				if (isElementPresent(By.id("headerButtonLogin")))
					break;
			} catch (Exception e) {
			}
			Thread.sleep(1000);
		}
		driver.findElement(By.id("headerButtonLogin")).click();

		for (int second = 0;; second++) {
			if (second >= 60)
				fail("timeout");
			try {
				if (isElementPresent(By.id("OperatorId")))
					break;
			} catch (Exception e) {
			}
			Thread.sleep(1000);
		}
		driver.findElement(By.id("OperatorId")).click();

		for (int second = 0;; second++) {
			if (second >= 60)
				fail("timeout");
			try {
				if (isElementPresent(By.id(operator)))
					break;
			} catch (Exception e) {
			}
			Thread.sleep(1000);
		}

		driver.findElement(By.id(operator)).click();

		for (int second = 0;; second++) {
			if (second >= 60)
				fail("timeout");
			try {
				if (isElementPresent(By.name("EmailAddress")))
					break;
			} catch (Exception e) {
			}
			Thread.sleep(1000);
		}
		driver.findElement(By.name("EmailAddress")).clear();
		driver.findElement(By.name("EmailAddress")).sendKeys(email);

		for (int second = 0;; second++) {
			if (second >= 60)
				fail("timeout");
			try {
				if (isElementPresent(By.name("Password")))
					break;
			} catch (Exception e) {
			}
			Thread.sleep(1000);
		}
		driver.findElement(By.name("Password")).clear();
		driver.findElement(By.name("Password")).sendKeys(password);

		Log.info("selectOperator is successful");
		return new HboSignIn();
	}

	public HboSignIn submit() throws Exception {

		for (int second = 0;; second++) {
			if (second >= 60)
				fail("timeout");
			try {
				if (isElementPresent(By
						.cssSelector("#sign-in-buttons > button.button_submit")))
					break;
			} catch (Exception e) {
			}
			Thread.sleep(1000);
		}
		driver.findElement(
				By.cssSelector("#sign-in-buttons > button.button_submit"))
				.click();
		Log.info("submit is successful");

		Log.info(driver.getCurrentUrl());
		//TestBase.assertTrue(driver.getCurrentUrl().equals(BaseUrls.PLAYER.get() + OffersScreen), driver.getCurrentUrl());
		Log.info("Login is successful!");

		return new HboSignIn();
	}

	// TODO: change it to parental
	public HboSignIn enterNewDevice() {
		try {
			if (isDialogDisplayed("newDeviceInput") == false) {
				Log.info("hello");
				return new HboSignIn();

			} else if (isDialogDisplayed("newDeviceInput") == true) {

				driver.findElement(By.id("newDeviceInput")).clear();
				driver.findElement(By.id("newDeviceInput")).sendKeys("MacBook");
				driver.findElement(By.cssSelector("button.button_submit"))
						.click();
				Log.info("New Device is named MacBook");
				Reporter.log("New Device is named MacBook");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new HboSignIn();

	}

	public HboSignIn checkLanguage(String languageMeta) {

		String baseLanguageMeta_ = PropertyUtils.getProperty(languageMeta);
		Log.info(baseLanguageMeta_);

		if (!driver.getCurrentUrl().equals(driver.getCurrentUrl().equals(
						BaseUrls.PLAYER.get() + OffersScreen))) {

			driver.get(BaseUrls.PLAYER.get() + OffersScreen);
		}

		String pageSource = driver.getTitle();
		Log.info(pageSource);

		TestBase.checkMetaContent(baseLanguageMeta_, 5);
		CaptureScreenshotOnFailureListener.captureScreenShot();

		return new HboSignIn();
	}
}
