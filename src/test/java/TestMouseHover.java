package test.java;

import main.TestBase;
import org.testng.annotations.Test;
import pageObjects.MouseHover;

public class TestMouseHover extends TestBase{
	
    @Test 
    public void testMouseHover() throws Exception{
		MouseHover MouseHover = new MouseHover(driver);
		
	    //playTrailer
		MouseHover.mouseHover();

		
	}

}
