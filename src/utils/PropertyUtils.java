package utils;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import main.TestBase;

import org.testng.Assert;

public class PropertyUtils extends TestBase {
	
    private static Properties p = new Properties();

    static
    {
        String workingDir = System.getProperty("user.dir");		 
        
        try
        {
            loadPropertyFile(workingDir + File.separator + proprtyFile);
        	
        	} catch (FileNotFoundException realCause) {
        		
        		Assert.fail("Unable to load file!", realCause);
        
        	} catch (IOException realCause) {
        		
        		Assert.fail("Unable to load file!", realCause);
        }
    }

    public static void loadPropertyFile(String propertyFileName) throws FileNotFoundException, IOException
    {
    	  InputStream is;
	            
	        	is = new FileInputStream(new File(propertyFileName));
	            DataInputStream in = new DataInputStream(is);
	            BufferedReader br = new BufferedReader(new InputStreamReader(in));
	        	p.load(br);
	        	
	        	in.close();	    
	 
    }

    public static String getProperty(String propertyKey)
    {
        String propertyValue = p.getProperty(propertyKey.trim());

        if (propertyValue == null || propertyValue.trim().length() == 0)
        
        	{
            
        		Log.info(propertyKey + "is missing!");
        	
        	}

        return propertyValue;
    }

    public static void setProperty(String propertyKey, String value) throws FileNotFoundException, IOException
    {
        p.setProperty(propertyKey, value);
    }
}
