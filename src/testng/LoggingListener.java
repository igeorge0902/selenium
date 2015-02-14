package testng;

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
		  public void onTestFailure(ITestResult tr) {
		    
		    
				// call the superclass
		        super.onTestFailure(tr);
		        	        
	        
	        log("Fuck\n");
		  }
		 
		  @Override
		  public void onTestSkipped(ITestResult tr) {
			  
				// call the superclass
		        super.onTestSkipped(tr);
			  
		     log("Shit happens\n");
		  }
		 
		  @Override
		  public void onTestSuccess(ITestResult tr) {
			  
				// call the superclass
		        super.onTestSuccess(tr); 
			  
		    log("Minden fasza\n");
		  }
		 
		  private void log(String string) {
		    System.out.print(string);
		    if (++m_count % 40 == 0) {
		      System.out.println("Hello");
		    }
		  }
		} 


