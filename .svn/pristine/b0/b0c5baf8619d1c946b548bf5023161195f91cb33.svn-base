package main.java.qa.framework.main;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import main.java.qa.framework.main.Click;

public class Clicks extends TestBase implements WebElements {
	Click click;

	public Clicks(WebDriver driver) {
		super(driver);
	}

	public Clicks(Click click) {
		this.click = click;
	}

	public void ContentTypes() {

		switch (click) {
		case CLICKEPISODE:			
			driver.findElement(By.linkText("Epizód megtekintése")).click();			
			break;

		case CLICKCONTENT:
			driver.findElement(By.linkText("Film megtekintése")).click();
			break;

		case CLICKFREE:
			driver.findElement(By.linkText("Előzetes megtekintése")).click();
			break;

		case CLICKFREEINTERACTIVE:
			break;

		case CLICKEXTRA:
			break;

		case CLICKINTERACTIVE:
			break;

		case CLICKLIVE:
			break;

		default:
			break;
		}
	}

	/**
	 * 
	 * Clicks on the Episode-like play button, and initiates the playback.
	 * 
	 */

	public static void clickEpisode() {
		Clicks clickepisode = new Clicks(Click.CLICKEPISODE);
		clickepisode.ContentTypes();

	}

	/**
	 * 
	 * Clicks on the Content-like play button, and initiates the playback.
	 * 
	 */

	public static void clickContent() {
		Clicks clickcontent = new Clicks(Click.CLICKCONTENT);
		clickcontent.ContentTypes();

	}
}
