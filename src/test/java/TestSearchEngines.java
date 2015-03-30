package test.java;


import main.TestBase;
import org.testng.annotations.*;

import pageObjects.Google;
import pageObjects.Yahoo;
import testng.CustomReportListener;
import testng.TestListeners;
import testng.TestMethodListener;


@Listeners({TestListeners.class, main.CaptureScreenshotOnFailureListener.class, TestMethodListener.class, CustomReportListener.class})

public class TestSearchEngines extends TestBase{
	
	
  @Test (dataProviderClass=utils.SampleDataProvider.class,dataProvider="getColors")
  public void Yahoo(String input) throws Exception {
	  Yahoo YahooSearch = new Yahoo(driver);
	  
	  YahooSearch.test(input);
	  
  }
  
  
  @Test (dataProviderClass=utils.SampleDataProvider.class,dataProvider="getColors")
  public void Google(String input) throws Exception {
	  Google GoogleSearch = new Google(driver);
	  
	  GoogleSearch.test(input);
  }
}
