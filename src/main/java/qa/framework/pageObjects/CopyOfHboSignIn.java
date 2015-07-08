package main.java.qa.framework.pageObjects;

import main.java.qa.framework.main.BaseUrls;
import main.java.qa.framework.main.TestBase;
import main.java.qa.framework.main.WebElements;
import main.java.qa.framework.utils.PropertyUtils;
import main.java.qa.framework.utils.WaitTool;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class CopyOfHboSignIn extends TestBase implements WebElements {

	private static String email = PropertyUtils.getProperty("email");
	private static String password = PropertyUtils.getProperty("password");
	
	public CopyOfHboSignIn selectOperator(String operator) throws Exception {
		Actions action = new Actions(driver);

		if (!driver.getCurrentUrl().equals(driver.getCurrentUrl().equals(BaseUrls.PLAYER.get() + OffersScreen))) {

			driver.get(BaseUrls.PLAYER.get() + OffersScreen);
		}
		    driver.findElement(By.id("buttonSignin")).click();
		    
		    Thread.sleep(3000);
		    
		    WebElement frame = driver.findElement(By.id("loginFrame"));
		    driver.switchTo().frame(frame);	    
		    
		    WebElement ddlist = driver.findElement(By.xpath(".//*[@id='signInForm']/div[3]/div[2]/div"));
		    action.moveToElement(ddlist).click().perform();
		    
		    WaitTool.waitForElement(driver, By.id(operator), 5);
			driver.findElement(By.id(operator)).click();

		    WaitTool.waitForElement(driver, By.name("EmailAddress"), 5);

			driver.findElement(By.name("EmailAddress")).clear();
			driver.findElement(By.name("EmailAddress")).sendKeys(email);


			driver.findElement(By.name("Password")).clear();
			driver.findElement(By.name("Password")).sendKeys(password);
			
			driver.findElement(By.className("button_submit")).click();
			
			driver.switchTo().defaultContent();

			Log.info(driver.getCurrentUrl());
			Thread.sleep(5000);
			Log.info(driver.getCurrentUrl());
			
			//TestBase.assertTrue(driver.getCurrentUrl().equals(BaseUrls.PLAYER.get() + OffersScreen), driver.getCurrentUrl());
			Log.info("Login is successful!");		  
			
		return new CopyOfHboSignIn();
	}
	
	public CopyOfHboSignIn logOut() throws Exception {

		/*
		if (!driver.getCurrentUrl().equals(driver.getCurrentUrl().equals(BaseUrls.PLAYER.get() + OffersScreen))) {

			driver.get(BaseUrls.PLAYER.get() + OffersScreen);
		}*/

		driver.switchTo().activeElement();

		WebElement logOut = driver.findElement(By.xpath(".//*[@id='headerButtonsProfile']/ul/li"));

		//WebElement logOut = driver.findElement(By.cssSelector("button.button_searchbox"));
		
		Actions actions = new Actions(driver);
		actions.moveToElement(logOut, 100, 0).perform();
		Thread.sleep(3000);
		return new CopyOfHboSignIn();
	}
}
