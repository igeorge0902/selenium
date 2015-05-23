package main.java.qa.framework.pageObjects;

import main.java.qa.framework.main.TestBase;
import main.java.qa.framework.main.WebElements;
import org.openqa.selenium.By;

public class HBOSignOut extends TestBase implements WebElements {

	public HBOSignOut signOut() throws Exception {
		
		driver.findElement(By.id("headerButtonMenu")).click();																//click on the Menu(hamburgerManu)
		
		driver.findElement(By.id("menuTabs_settings")).click();																//click on Settings tab
		
		driver.findElement(By.cssSelector("a[href*='signout']")).click();
		return new HBOSignOut();
	}

	public HBOSignOut submit() throws Exception {
		
		driver.findElement(By.cssSelector("div.form_buttons > button.button_submit")).click();	

		return new HBOSignOut();
	}
}
