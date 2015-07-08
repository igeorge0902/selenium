package test.java;

import main.java.qa.framework.main.TestBase;
import main.java.qa.framework.pageObjects.CopyOfHboSignIn;
import main.java.qa.framework.testng.LoggingListener;
import main.java.qa.framework.testng.TestListeners;
import main.java.qa.framework.testng.TestMethodListener;

import org.testng.annotations.*;

@Listeners({ TestListeners.class, main.java.qa.framework.main.CaptureScreenshotOnFailureListener.class, TestMethodListener.class, LoggingListener.class })
public class TestCopyOf extends TestBase {

	@Parameters ({"operator"})
	@Test( description = "HBO login")
	public void testSignInCopyOf(String operator) throws Exception {
		CopyOfHboSignIn CopyOfHboSignIn = new CopyOfHboSignIn();
		
		CopyOfHboSignIn.selectOperator(operator);

	}

	@Test(dependsOnMethods = { "testSignInCopyOf" }, description = "HBO logout")
	public void logOut() throws Exception {
		CopyOfHboSignIn logOut = new CopyOfHboSignIn();
		
		logOut.logOut();

	}
}