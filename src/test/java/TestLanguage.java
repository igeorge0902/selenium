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
@Listeners({ TestListeners.class, CaptureScreenshotOnFailureListener.class, TestMethodListener.class, LoggingListener.class })
public class TestLanguage extends TestBase {

	@Parameters({ "czech", "slovak", "czeMeta", "sloMeta" })
	@Test(description = "ChangeLanguage")
	
	public void testLanguageChange(String czech, String slovak, String czeMeta, String sloMeta) throws Exception {
		LanguageChange LanguageChange = new LanguageChange(driver);

		LanguageChange.changeLanguage(czech, slovak, czeMeta, sloMeta);
	}
	

}
