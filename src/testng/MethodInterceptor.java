package testng;

import java.util.ArrayList;
import java.util.List;

import org.testng.IMethodInstance;
import org.testng.IMethodInterceptor;
import org.testng.ITestContext;

public class MethodInterceptor implements IMethodInterceptor {
	 
    @Override
    public List<IMethodInstance> intercept(List<IMethodInstance> methods, ITestContext context) {
    	
    	List<IMethodInstance> newList = new ArrayList<IMethodInstance>(methods.size());
    	
    	for(IMethodInstance m : methods)
    	{
    		String MethodName =m.getMethod().getMethodName();
    		if (MethodName.contains("Yahoo"))
    		{
    			newList.add(0, m);
    		}
    		else if (MethodName.contains("player"))
    		{
    			newList.add(m);
    		}
    		else if (MethodName.contains("Google"))
    		{
    			newList.add(m);
    		}
    		else
    		{
    			newList.add(m);
    		}
    	}
                  
        return newList;
    }
}
