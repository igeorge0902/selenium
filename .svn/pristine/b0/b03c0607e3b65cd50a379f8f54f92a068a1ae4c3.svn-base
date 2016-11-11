package main.java.qa.framework.pageObjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import main.java.qa.framework.main.TestBase;
import main.java.qa.framework.main.WebElements;

public class iMDB extends TestBase implements WebElements {

	public iMDB selectMovies() throws Exception {
				  
		driver.get("http://www.imdb.com");
		
		driver.findElement(By.xpath(".//*[@id='nblogin']")).click();
		driver.findElement(By.xpath(".//div[@id='signin-options']/div/div/a[1]")).click();
		
		driver.findElement(By.xpath(".//*[@id='ap_email']")).sendKeys("igeorge1982@gmail.com");
		driver.findElement(By.xpath(".//*[@id='ap_password']")).sendKeys("Bugsbunny666");
		
		driver.findElement(By.xpath(".//*[@id='signInSubmit']")).click();
		
		driver.findElement(By.xpath(".//*[@id='navWatchlistMenu']/p/a")).click();

		List<String> titles = new ArrayList<String>();

		for (int i = 1; i < 101; i++) {
		
		StringBuilder builder = new StringBuilder();

		WebElement findElements = driver.findElement(By.xpath(".//div[@id='main']/div[1]/div[3]/div[3]/div["+i+"]/div[2]/h3/a"));
		WebElement findElements_ = driver.findElement(By.xpath(".//*[@id='main']/div[1]/div[3]/div[3]/div["+i+"]/div[2]/p[2]"));	
		
		String title = findElements.getText();
		String link = findElements.getAttribute("href");

		String title_text = findElements_.getText();	
		
		builder.append(i+1005).append(",\"").append(title_text).append("\",\"").append(title).append("\",").append("NULL,").append("NULL,").append(link);
		
		titles.add(builder.toString());
		
		}

		TestBase.deleteFile(urlsFile);
		TestBase.writeFile(urlsFile, titles);

		
		return new iMDB();
	}

}
