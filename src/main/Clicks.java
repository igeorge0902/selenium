package main;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import main.Click;


public class Clicks extends TestBase implements WebElements {
    Click click;
    
	public Clicks(WebDriver driver){
		super(driver); 
	}
    
    public Clicks(Click click) {
        this.click = click;
    }
    
    public void ContentTypes() {
    	
        switch (click) {
            case CLICKEPISODE:
            	driver.findElement(By.linkText(playEpisode)).click();
                break;
                    
            case CLICKCONTENT:
            	driver.findElement(By.linkText(playContent)).click();
                break;
                         
            case CLICKFREE:
            	driver.findElement(By.linkText(playTrailer)).click();
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
    
    public static void clickEpisode() {
    	Clicks clickepisode = new Clicks(Click.CLICKEPISODE);
    	clickepisode.ContentTypes();

    }
    
    public static void clickContent() {
    	Clicks clickcontent = new Clicks(Click.CLICKCONTENT);
    	clickcontent.ContentTypes();

    }
}
    


