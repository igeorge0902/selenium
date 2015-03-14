package main;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

@SuppressWarnings("unused")
public class ElementScreenshot extends TestBase{
private static final String ESCAPE_PROPERTY = "org.uncommons.reportng.escape-output"; 

 public static void captureScreenshot() throws Exception {
	 
	 
  By arg0 = null;
		//Locate Image element to capture screenshot.
        WebElement Image = WebDriverManager.driver.findElement(arg0);
        //Call captureElementScreenshot function to capture screenshot of element.
        captureElementScreenshot(Image);
 }
 
 public static void captureElementScreenshot(final WebElement element) throws Exception{
	 
	 
       	  
	  //Locate Image element to capture screenshot.
	  WebElement Image = null;

	  Calendar calendar = Calendar.getInstance();
      String userHome = System.getProperty("user.home");
      String screenShotsFolder = userHome +File.separator+"Documents" + File.separator + "Tests" + File.separator;
      
	String screen = screenShotsFolder  + WebDriverManager.getBroswer() + "-"
	          + calendar.get(Calendar.YEAR) + "-"
	          + calendar.get(Calendar.MONTH) + "-"
	          + calendar.get(Calendar.DAY_OF_MONTH) + "-"
	          + calendar.get(Calendar.HOUR_OF_DAY) + "-"
	          + calendar.get(Calendar.MINUTE) + "-"
	          + calendar.get(Calendar.SECOND) + "-"
	          + calendar.get(Calendar.MILLISECOND)
	          + ".png";
	 
	 //Capture entire page screenshot as buffer.
  //Used TakesScreenshot, OutputType Interface of selenium and File class of java to capture screenshot of entire page.
  File scrFile = ((TakesScreenshot) WebDriverManager.driver).getScreenshotAs(OutputType.FILE);
  
  //Reading full image screenshot.
  BufferedImage img = ImageIO.read(scrFile);
  
  //Used selenium getSize() method to get height and width of element.
  //Retrieve width of element.
  int ImageWidth = element.getSize().getWidth();
  //Retrieve height of element.
  int ImageHeight = element.getSize().getHeight();  
  
  
  //Used selenium Point class to get x y coordinates of Image element.
  //get location(x y coordinates) of the element.
  Point point = element.getLocation();
  int xcord = point.getX();
  int ycord = point.getY();
  
  //Reading full image screenshot.  
  System.out.printf("Original Image Dimension: " + element.getSize().getWidth(), element.getSize().getHeight());
  
  
  //cut Image using height, width and x y coordinates parameters.
  try {
  BufferedImage dest = img.getSubimage(xcord, ycord, ImageWidth, ImageHeight);
  ImageIO.write(dest, "png", scrFile);
  }
  catch (IOException e) {
	  e.printStackTrace();
  }
    
  //Used FileUtils class of apache.commons.io.
  String fileNameToCopy = screen;

  try  {
  FileUtils.copyFile(scrFile, new File(screen));
  }
  catch (IOException e)
  {
     e.printStackTrace();
  }
    
  File file = new File(screen);
  
  
  System.setProperty(ESCAPE_PROPERTY, "false");

  String absolute = file.getAbsolutePath();
  //int beginIndex = absolute.indexOf(".");
  //String relative = absolute.substring(beginIndex).replace(".\\","");
  String screenShot = absolute.replace('\\','/');


  Reporter.log("<a href=\"" + screenShot + "\"><p align=\"left\"Element screenshot at " + new Date()+ "</p>");
  Reporter.log("<p><img width=\"50%\" src=\"" + file.getAbsoluteFile()  + "\" alt=\"screenshot at " + new Date()+ "\"/></p></a><br />"); 

  
 	}


}