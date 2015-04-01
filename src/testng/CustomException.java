package testng;

import org.apache.log4j.Logger;
import org.testng.Reporter;

public class CustomException extends Exception {

	private static final long serialVersionUID = 6446869692704936034L;
	private static Logger Log = Logger.getLogger(Logger.class.getName());	 	

	 /**
	 * Custom exception messege.
	 *  
	 * @param e
	 * @return "Huston baj van!"
	 */
	
	public String toString (Exception e)  {
		Reporter.log(getLocalizedMessage()); 
		Log.info(e.getStackTrace());
		return "Huston baj van!";
		
	}

}
