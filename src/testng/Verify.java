package testng;

import org.testng.IInvokedMethod;
import org.testng.ITestContext;
import org.testng.ITestResult;

/**
 * Verification tool class. Wrapper class around TestNG Assert.
 * @author <a href='mailto:gadigeppa.code@gmail.com'>Gadigeppa Jattennavar</a>
 */
public class Verify {

	protected Verify() {
		// hide constructor
	}

	public static void addToErrorBuffer(Throwable e){	  

		try{				

			VerificationError verificationError = new VerificationError(e.getMessage());

			verificationError.setStackTrace(e.getStackTrace());

			TestMethodErrorBuffer.get().add(verificationError);

		}catch(NullPointerException ex){

			throw new RuntimeException("Please let TestNG know about " + TestMethodListener.class.getName() + " listener for verify statements to work. For more information go to http://testng.org/doc/documentation-main.html#testng-listeners");
		}

	}

	public void beforeInvocation(IInvokedMethod method, ITestResult testResult,
			ITestContext context) {
		// TODO Auto-generated method stub
		
	}
}
