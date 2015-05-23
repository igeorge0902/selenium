package test.java;

import main.java.qa.framework.main.BaseUrls;
import main.java.qa.framework.main.TestBase;
import main.java.qa.framework.pageObjects.HboSignIn;
import main.java.qa.framework.pageObjects.MouseHover;
import main.java.qa.framework.pageObjects.SelectMovieGroups;

import org.testng.Reporter;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class SampleTest extends TestBase {

	@Parameters({ "operator" })
	@Test(groups = { "signin" }, description = "HBO login")
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

	}

	@Test
	public void testMouseHover() throws Exception {
		MouseHover MouseHover = new MouseHover();

		MouseHover.mouseHover();

	}

	@Test
	public void testSelectContentsPlay() throws Exception {
		SelectMovieGroups SelectMovieGroups = new SelectMovieGroups();

		// select contents
		SelectMovieGroups.groups();
		Log.info("selectMovieGroups test is done");
		Reporter.log("<p>selectMovieGroups test is done<br></p>");
		;

	}

}
