package test.java;

/*
 * For custom logging messages after tests have finished.
 * 
 * 				under consideration yet
 */


import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class LoggingListener extends TestListenerAdapter {
		  private int m_count = 0;
		  	 
		  @Override
		  public void onTestFailure(ITestResult testResult) {
		    
		    
				// call the superclass
		        super.onTestFailure(testResult);
	        
	        
	        log("F");
		  }
		 
		  @Override
		  public void onTestSkipped(ITestResult testResult) {
			  
				// call the superclass
		        super.onTestSkipped(testResult);
			  
		     log("S");
		  }
		 
		  @Override
		  public void onTestSuccess(ITestResult testResult) {
			  
				// call the superclass
		        super.onTestSkipped(testResult); 
			  
		    log(".");
		  }
		 
		  private void log(String string) {
		    System.out.print(string);
		    if (++m_count % 40 == 0) {
		      System.out.println("");
		    }
		  }
		} 


