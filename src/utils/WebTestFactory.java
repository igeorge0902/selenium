package utils;

import org.testng.annotations.Factory;

import test.java.TestHboSignUp;
import test.java.TestTest2;

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
	public static Object[] createInstances1() {
	 Object[] result = new Object[2]; 
	 for (int i = 0; i < 2; i++) {
	    result[i] = new TestTest2(i * 2);
	  }
	  return result;
	}
	
	
}


