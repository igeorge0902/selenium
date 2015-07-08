package test.java;

import main.java.qa.framework.main.TestBase;
import main.java.qa.framework.main.WebDriverManager;
import main.java.qa.framework.pageObjects.HBOSignOut;
import main.java.qa.framework.pageObjects.HboSignIn;
import main.java.qa.framework.testng.LoggingListener;
import main.java.qa.framework.testng.TestListeners;
import main.java.qa.framework.testng.TestMethodListener;

import org.testng.annotations.*;

@Listeners({ TestListeners.class, main.java.qa.framework.main.CaptureScreenshotOnFailureListener.class, TestMethodListener.class, LoggingListener.class })
public class TestHboSignIn extends TestBase {

	@Parameters({ "operator", "languageMeta" })
	@Test(groups = { "functional_test1" }, description = "HBO login")
	public void testSignInSuccess(String operator, @Optional ("defaultLanguageMeta") String languageMeta)
			throws Exception {
		HboSignIn SignInPage = new HboSignIn();

		
		 // select operator
		 SignInPage.selectOperator(operator);
		 
		 /* Log.info("selectOperator test is done");
		 * Reporter.log("<p>selectOperator test is done<br></p>");
		 * 
		 * // submit SignInPage.submit(); Log.info("submit test is done");
		 * Reporter.log("<p>submit test is done<br></p>");
		 */

		SignInPage.checkLanguage(languageMeta);
		
		if (WebDriverManager.serverStarted = true) {
			Log.info(server.getStreamManager().getRemainingDownstreamKB());
		}
	}

	@Test(dependsOnMethods = { "testSignInSuccess" }, description = "HBO logout")
	public void testSignOutSuccess() throws Exception {
		HBOSignOut SignOut = new HBOSignOut();

		SignOut.signOut();

		SignOut.submit();
	}

}
