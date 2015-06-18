package main.java.qa.framework.pageObjects;

import main.java.qa.framework.main.BaseUrls;
import main.java.qa.framework.main.TestBase;
import main.java.qa.framework.main.WebElements;
import main.java.qa.framework.utils.PropertyUtils;
import main.java.qa.framework.utils.WaitTool;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

public class CopyOfHboSignIn extends TestBase implements WebElements {

	public CopyOfHboSignIn(WebDriver driver) {
		super(driver);
	}

	private static String email = PropertyUtils.getProperty("email");
	private static String password = PropertyUtils.getProperty("password");

	public CopyOfHboSignIn selectOperator() throws Exception {

		if (!driver.getCurrentUrl().equals(
				driver.getCurrentUrl().equals(
						BaseUrls.PLAYER.get() + OffersScreen))) {

			driver.get(BaseUrls.PLAYER.get() + OffersScreen);
		}
		    driver.findElement(By.id("headerButtonLogin")).click();
		    driver.findElement(By.id("OperatorId")).click();
		    driver.findElement(By.id("OperatorId_d4125db5-bc12-4ee4-b68f-0b39b5276659")).click();
		    driver.findElement(By.name("EmailAddress")).clear();
		    driver.findElement(By.name("EmailAddress")).sendKeys("gyorgy.gaspar@mediaux.biz");
		    driver.findElement(By.name("Password")).clear();
		    driver.findElement(By.name("Password")).sendKeys("common");
		    driver.findElement(By.cssSelector("#sign-in-buttons > button.button_submit")).click();
		  
		return new CopyOfHboSignIn(driver);
	}
}
