package test.java;

import main.java.qa.framework.main.BaseUrls;
import main.java.qa.framework.main.TestBase;
import main.java.qa.framework.pageObjects.HboSignIn;
import main.java.qa.framework.pageObjects.PlayMainContent;
import main.java.qa.framework.pageObjects.SelectSeries;
import main.java.qa.framework.testng.LoggingListener;
import main.java.qa.framework.testng.TestListeners;
import main.java.qa.framework.testng.TestMethodListener;

import org.testng.Reporter;
import org.testng.annotations.*;

@Listeners({ TestListeners.class,
		main.java.qa.framework.main.CaptureScreenshotOnFailureListener.class,
		TestMethodListener.class, LoggingListener.class })
public class TestPlayerSeries extends TestBase {

	@Parameters({ "operator" })
	@Test(description = "HBO login")
	public void testSignInSuccess(String operator) throws Exception {
		HboSignIn SignInPage = new HboSignIn(driver);

		// select operator
		SignInPage.selectOperator(operator);
		Log.info("selectOperator test is done");
		Reporter.log("<p>selectOperator test is done<br></p>");

		// submit
		SignInPage.submit();
		Thread.sleep(3000);
		Log.info("submit test is done");
		Reporter.log("<p>submit test is done<br></p>");
		TestBase.assertTrue(driver.getCurrentUrl().equals(
				BaseUrls.PLAYER.get() + OffersScreen));

	}

	@Test(dependsOnMethods = { "testSignInSuccess" }, description = "Selecting contents for playback tests.")
	public void testSelectSeries() throws Exception {
		SelectSeries SelectSeries = new SelectSeries(driver);

		// select contents
		SelectSeries.selectSeries();
		Log.info("selectSeries test is done");
		Reporter.log("<p>selectSeries test is done<br></p>");

	}

	@Test(dataProviderClass = main.java.qa.framework.utils.SampleDataProvider.class, dataProvider = "fileDataProviderSeries", dependsOnMethods = { "testSelectSeries" }, description = "Playback test of main contents")
	public void testPlayerSuccess(String urls) throws Exception {
		PlayMainContent PlayMainContent = new PlayMainContent(driver);

		// play mainContent
		PlayMainContent.playMainContent(urls);
		Log.info("playMainContent test is done");
		Reporter.log("<p>playMainContent test is done<br></p>");

	}

}
