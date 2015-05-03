package main.java.qa.framework.testng;

import java.util.ArrayList;
import java.util.List;

import main.java.qa.framework.main.TestBase;

import org.testng.IMethodInstance;
import org.testng.IMethodInterceptor;
import org.testng.ITestContext;

/** 
 * This class is used to alter the list of test methods that TestNG is about to run.
 **/

public class MethodInterceptor extends TestBase implements IMethodInterceptor {
	 
    @Override
    public List<IMethodInstance> intercept(List<IMethodInstance> methods, ITestContext context) {
    	
    	List<IMethodInstance> newList = new ArrayList<IMethodInstance>(methods.size());
    	
    	for(IMethodInstance m : methods)
    	{
    		String MethodName =m.getMethod().getMethodName();
    		if (MethodName.contains("testSignInSuccess"))
    		{
    			newList.add(0, m);
    			System.out.println("testSignInSuccess");
    		}
    		else if (MethodName.contains("testSignInFail"))
    		{
    			newList.add(m);
    			System.out.println("testSignInFail");
    		}
    		else if (MethodName.contains("Yahoo"))
    		{
    			newList.add(m);
    			System.out.println("Yahoo");
    		}
    		else
    		{
    			newList.add(m);
    		}
    	}
                  
        return newList;
    }
}
