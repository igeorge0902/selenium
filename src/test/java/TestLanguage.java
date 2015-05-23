package test.java;

import main.java.qa.framework.main.TestBase;
import main.java.qa.framework.pageObjects.HBOSignOut;
import main.java.qa.framework.pageObjects.HboSignIn;
import main.java.qa.framework.pageObjects.LanguageChange;
import main.java.qa.framework.testng.LoggingListener;
import main.java.qa.framework.testng.TestListeners;
import main.java.qa.framework.testng.TestMethodListener;
import main.java.qa.framework.main.CaptureScreenshotOnFailureListener;

import org.testng.Reporter;
import org.testng.annotations.*;

@SuppressWarnings("unused")
@Listeners({ TestListeners.class, CaptureScreenshotOnFailureListener.class,
		TestMethodListener.class, LoggingListener.class })
public class TestLanguage extends TestBase {

	@Parameters({ "english", "engMeta" })
	@Test(description = "ChangeLanguage")
	public void testLanguageChange(String english, String engMeta) throws Exception {
		LanguageChange LanguageChange = new LanguageChange();

		LanguageChange.changeLanguage(english, engMeta);
	}

}
