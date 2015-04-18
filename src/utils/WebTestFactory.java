package utils;

import org.testng.annotations.Factory;

import test.java.TestHboSignUp;
import test.java.TestSingleContent;

public class WebTestFactory {


	  @Factory
	  public static Object[] createInstances() {
	   Object[] result = new Object[2]; 
	   for (int i = 0; i < 2; i++) {
	      result[i] = new TestHboSignUp(i * 2);
	    }
	    return result;
	  }
	  
	  @Factory
	  public static Object[] createInstances_() {
	   Object[] result = new Object[2]; 
	   for (int i = 0; i < 2; i++) {
	      result[i] = new TestSingleContent(i * 2);
	    }
	    return result;
	  }
	  
	  
	
	
}


