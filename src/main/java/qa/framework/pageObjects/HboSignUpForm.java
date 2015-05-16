package main.java.qa.framework.pageObjects;

import main.java.qa.framework.main.BaseUrls;
import main.java.qa.framework.main.TestBase;
import main.java.qa.framework.testng.TestListeners;
import main.java.qa.framework.testng.TestMethodListener;
import main.java.qa.framework.utils.PropertyUtils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Listeners;

@Listeners({ TestListeners.class,
		main.java.qa.framework.main.CaptureScreenshotOnFailureListener.class,
		TestMethodListener.class })
public class HboSignUpForm extends TestBase {

	public HboSignUpForm(WebDriver driver) {
		super(driver);
	}

	private static String email = PropertyUtils.getProperty("email");
	private static String email2 = PropertyUtils.getProperty("email2");
	private static String password = PropertyUtils.getProperty("password");

	public HboSignUpForm fillCustomerData() throws Exception {

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
				if (isElementPresent(By.name("EmailAddressAgain")))
					break;
			} catch (Exception e) {
			}
			Thread.sleep(1000);
		}
		driver.findElement(By.name("EmailAddressAgain")).clear();
		driver.findElement(By.name("EmailAddressAgain")).sendKeys(email2);

		for (int second = 0;; second++) {
			if (second >= 60)
				fail("timeout");
			try {
				if (isElementPresent(By.name("Nick")))
					break;
			} catch (Exception e) {
			}
			Thread.sleep(1000);
		}
		driver.findElement(By.name("Nick")).clear();
		driver.findElement(By.name("Nick")).sendKeys("Gyuri07");

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

		for (int second = 0;; second++) {
			if (second >= 60)
				fail("timeout");
			try {
				if (isElementPresent(By.name("PasswordAgain")))
					break;
			} catch (Exception e) {
			}
			Thread.sleep(1000);
		}
		driver.findElement(By.name("PasswordAgain")).clear();
		driver.findElement(By.name("PasswordAgain")).sendKeys(password);

		for (int second = 0;; second++) {
			if (second >= 60)
				fail("timeout");
			try {
				if (isElementPresent(By.id("Newsletter_theme")))
					break;
			} catch (Exception e) {
			}
			Thread.sleep(1000);
		}
		driver.findElement(By.id("Newsletter_theme")).click();

		for (int second = 0;; second++) {
			if (second >= 60)
				fail("timeout");
			try {
				if (isElementPresent(By.id("Terms_theme")))
					break;
			} catch (Exception e) {
			}
			Thread.sleep(1000);
		}
		driver.findElement(By.id("Terms_theme")).click();

		return new HboSignUpForm(driver);
	}

	public HboSignUpForm submitForm() throws Exception {
		driver.findElement(By.cssSelector("button.button_submit.submit"))
				.click();
		TestBase.assertEquals(driver.getCurrentUrl(),
				(BaseUrls.PLAYER.get() + OffersScreen));
		return new HboSignUpForm(driver);
	}

	public HboSignUpForm newDevice() {
		driver.findElement(By.id("newDeviceInput")).clear();
		driver.findElement(By.id("newDeviceInput")).sendKeys("MacBook");
		driver.findElement(By.cssSelector("button.button_submit")).click();
		return new HboSignUpForm(driver);
	}

}
