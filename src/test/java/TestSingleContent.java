package test.java;

import main.java.qa.framework.main.BaseUrls;
import main.java.qa.framework.main.TestBase;
import main.java.qa.framework.pageObjects.HboSignIn;
import main.java.qa.framework.pageObjects.PlaySingleMainContent;

import org.testng.Reporter;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestSingleContent extends TestBase {

	@Parameters({ "operator" })
	@Test(description = "HBO login")
	public void testSignInSuccess(String operator) throws Exception {
		HboSignIn SignInPage = new HboSignIn();

		// select operator
		SignInPage.selectOperator(operator);
		Log.info("selectOperator test is done");
		Reporter.log("<p>selectOperator test is done<br></p>");

		// submit
		SignInPage.submit();
		Thread.sleep(3000);
		TestBase.assertTrue(driver.getCurrentUrl().equals(
				BaseUrls.PLAYER.get() + OffersScreen));
		Log.info("submit test is done");
		Reporter.log("<p>submit test is done<br></p>");

		/*
		 * //language check SignInPage.checkLanguage();
		 * Log.info("language check test is done");
		 * Reporter.log("<p>language check test is done<br></p>");
		 */
	}

	@Parameters({ "contents" })
	@Test(dependsOnMethods = { "testSignInSuccess" })
	public void testPlaySingleContent(String contents) throws Exception {

		// for (int i = 0; i < m_numberOfTimes; i++) {

		PlaySingleMainContent PlayMainContent = new PlaySingleMainContent();

		PlayMainContent.playMainContent(contents);

	}

}
// }
