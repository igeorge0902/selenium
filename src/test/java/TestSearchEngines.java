package test.java;

import main.java.qa.framework.main.TestBase;
import main.java.qa.framework.pageObjects.Google;
import main.java.qa.framework.pageObjects.Yahoo;
import main.java.qa.framework.testng.CustomReportListener;
import main.java.qa.framework.testng.TestListeners;
import main.java.qa.framework.testng.TestMethodListener;

import org.testng.annotations.*;

@Listeners({ TestListeners.class,
		main.java.qa.framework.main.CaptureScreenshotOnFailureListener.class,
		TestMethodListener.class, CustomReportListener.class })
public class TestSearchEngines extends TestBase {

	@Test(dataProviderClass = main.java.qa.framework.utils.SampleDataProvider.class, dataProvider = "getColors")
	public void Yahoo(String input) throws Exception {
		Yahoo YahooSearch = new Yahoo();

		YahooSearch.test(input);

	}

	@Test(dataProviderClass = main.java.qa.framework.utils.SampleDataProvider.class, dataProvider = "getColors")
	public void Google(String input) throws Exception {
		Google GoogleSearch = new Google();

		GoogleSearch.test(input);
	}
}
