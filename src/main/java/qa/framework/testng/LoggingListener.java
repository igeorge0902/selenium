package main.java.qa.framework.testng;

/*
 * For custom logging messages after tests have finished.
 * 
 * 				under consideration yet
 */


import main.java.qa.framework.main.TestBase;
import main.java.qa.framework.utils.SQLAccess;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;

public class LoggingListener extends TestListenerAdapter {
	
	  @Override
	  public void onConfigurationFailure(ITestResult tr) {
	    
			// call the superclass
	        super.onConfigurationFailure(tr);
	        super.getConfigurationFailures();
	        super.getConfigurationSkips();
	       
			try {
				if (SQLAccess.insertReport()) {

					try{
						SQLAccess.sessionId();
						SQLAccess.testSummaryReport(CustomReportListener.getsuiteName(),CustomReportListener.gettestName(), CustomReportListener.getconfigFailes(), CustomReportListener.gettestFailes(), CustomReportListener.gettestSkipped(), CustomReportListener.gettestPassed());		

						} catch (Exception e) {
							TestBase.Log.info("SQL insert failed!");
					}
				}
			} catch (Exception e) {
				TestBase.Log.info("SQL insert failed!");
			}
				
	        Reporter.log("Fuck<br>");
	        TestBase.Log.info("Fuck");
	  	}
		  	 
		  @Override
		  public void onTestFailure(ITestResult tr) {
		    
		    
				// call the superclass
		        super.onTestFailure(tr);
		        super.getFailedTests();
		        	        
	        Reporter.log("Fuck<br>");
	        TestBase.Log.info("Fuck");
		  }
		 
		  @Override
		  public void onTestSkipped(ITestResult tr) {
			  
				// call the superclass
		        super.onTestSkipped(tr);
		        super.getSkippedTests();
		     
		     Reporter.log("Shit happens");
		     TestBase.Log.info("Shit happens");
		  }
		 
		  @Override
		  public void onTestSuccess(ITestResult tr) {
			  
				// call the superclass
		        super.onTestSuccess(tr);
		        super.getPassedTests();
  
		    Reporter.log("Minden fasza");
		    TestBase.Log.info("Minden fasza");
		  }
		  
		  @Override
		  public void onFinish(ITestContext context) {
			  
				// call the superclass
		        super.onFinish(context); 
		        super.getAllTestMethods();
		        
		  }
		 
		} 


