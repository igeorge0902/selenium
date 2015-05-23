package main.java.qa.framework.testng;

/*
 * For custom logging messages after tests have finished.
 * 
 * 				under consideration yet
 */


import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;

public class LoggingListener extends TestListenerAdapter {
		  private int m_count = 0;
		  	 
		  @Override
		  public void onTestFailure(ITestResult tr) {
		    
		    
				// call the superclass
		        super.onTestFailure(tr);
		        	        
	        Reporter.log("Fuck<br>");
	        log("Fuck\n");
		  }
		 
		  @Override
		  public void onTestSkipped(ITestResult tr) {
			  
				// call the superclass
		        super.onTestSkipped(tr);
		     
		     Reporter.log("Shit happens");
		     log("Shit happens\n");
		  }
		 
		  @Override
		  public void onTestSuccess(ITestResult tr) {
			  
				// call the superclass
		        super.onTestSuccess(tr); 
  
		    Reporter.log("Minden fasza");
		    log("Minden fasza\n");
		  }
		  
		  @Override
		  public void onFinish(ITestContext context) {
			  
				// call the superclass
		        super.onFinish(context); 
		        
		  }
		 
		  private void log(String string) {
		    System.out.print(string);
		    if (++m_count % 40 == 0) {
		      System.out.println("Hello");
		    }
		  }
		} 


