package utils;


import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import main.TestBase;
import main.WebElements;

import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
 
public class SampleDataProvider implements WebElements{
	
	
    @DataProvider
    public static Iterator<Object[]> fileDataProvider (ITestContext context) {
    	
    
    	String inputFile = "";
    	if (!TestBase.isSupportedPlatformMac(true)) {
    		
    		//Get the input file path from the ITestContext
    		Path input = Paths.get(urlsFile);

    		if (input.toFile().exists()) {
    		
    		inputFile = context.getCurrentXmlTest().getParameter("filenamePathWin");
    		} 
    	}
    		
    	else if (TestBase.isSupportedPlatformMac(true)) {
            
    		//Get the input file path from the ITestContext
    		Path input = Paths.get(urlsFile);

    		if (input.toFile().exists()) {
    		
            inputFile = context.getCurrentXmlTest().getParameter("filenamePathMac");
        	} 
    	}
        
        //Get a list of String file content (line items) from the test file.
        
    	List<String> testData = getFileContentList(inputFile);

 
        //We will be returning an iterator of Object arrays so create that first.
        List<Object[]> dataToBeReturned = new ArrayList<Object[]>();
 
        //Populate our List of Object arrays with the file content.
        for (String userData : testData)
        {
            dataToBeReturned.add(new Object[] { userData } );
        }
        
        //return the iterator - testng will initialize the test class and calls the
        //test method with each of the content of this iterator.
        
        return dataToBeReturned.iterator();
 
    }

    @DataProvider
    public static Iterator<Object[]> fileDataProvider_ (ITestContext context) {
   		
    	String inputFile_series = "";
    	if (!TestBase.isSupportedPlatformMac(true)) {
    		
    		//Get the input file path from the ITestContext
    		Path input = Paths.get(episodeUrlsFile);

    		if (input.toFile().exists()) {
    		inputFile_series = context.getCurrentXmlTest().getParameter("filenamePathWin_series");
    		} 
    	}
    	
    	else if (TestBase.isSupportedPlatformMac(true)) {
            
    		//Get the input file path from the ITestContext
    		Path input = Paths.get(episodeUrlsFile);

    		if (input.toFile().exists()) {
            inputFile_series = context.getCurrentXmlTest().getParameter("filenamePathMac_series");
        	}
    	}
        
        //Get a list of String file content (line items) from the test file.
        
    	//if (!inputFile.isEmpty()) 
        List<String> testData_series = getFileContentList(inputFile_series);

 
        //We will be returning an iterator of Object arrays so create that first.
        List<Object[]> dataToBeReturned = new ArrayList<Object[]>();
 
        //Populate our List of Object arrays with the file content.        
        for (String userData : testData_series)
        {
            dataToBeReturned.add(new Object[] { userData } );
        }
        
        //return the iterator - testng will initialize the test class and calls the
        //test method with each of the content of this iterator.
        
        return dataToBeReturned.iterator();
 
    }
    
    @DataProvider
    public static Iterator<String> urlDataProvider (ITestContext context) throws FileNotFoundException {
    	
        //Get the input file path from the ITestContext
    	
        String inputFile = context.getCurrentXmlTest().getParameter("filenamePath");
                
        //Get a list of String file content (line items) from the test file.
        List<String> testData = getFileContentList(inputFile);
 
        //We will be returning an iterator of String arrays so create that first.
        List<String> dataToBeReturned = new ArrayList<String>();

 
        //Populate our List of Object arrays with the file content.
        if (!testData.isEmpty()) {
        for (String urlData : testData)
        {
            dataToBeReturned.add( urlData  );
        	}       
        }
        
        else {
        	//TODO: do something
        }
        
        //return the iterator - testng will initialize the test class and calls the
        //test method with each of the content of this iterator.
        
        return dataToBeReturned.iterator();
 
    }
    
    @DataProvider(name="colors") 
    public static Iterator<Object[]> getColors(){
    	  Set<Object[]> result=new HashSet<Object[]>();
    	  result.add(new Object[]{"black"});
    	  result.add(new Object[]{"silver"});
    	  result.add(new Object[]{"gray"});
    	  result.add(new Object[]{"white"});
    	  result.add(new Object[]{"maroon"});
    	  result.add(new Object[]{"red"});
    	  result.add(new Object[]{"purple"});
    	  result.add(new Object[]{"fuchsia"});
    	  result.add(new Object[]{"green"});
    	  result.add(new Object[]{"lime"});
    	  result.add(new Object[]{"olive"});
    	  result.add(new Object[]{"yellow"});
    	  result.add(new Object[]{"navy"});
    	  result.add(new Object[]{"blue"});
    	  result.add(new Object[]{"teal"});
    	  result.add(new Object[]{"aqua"});
    	  return result.iterator();
    	}
 
    private static List<String> getFileContentList(String filenamePath)
    {
        //Sample utility method to get the file content, any version of
        //this can be adapted, this is just one way of achieving the result.
        InputStream is;
        List<String> lines = new ArrayList<String> ();
        
        try {
            
        	is = new FileInputStream(new File(filenamePath));
            DataInputStream in = new DataInputStream(is);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;
            
            while ((strLine = br.readLine()) != null)   {
                lines.add(strLine);
            }
            in.close();
        	
        		} catch (FileNotFoundException e) {
        			e.printStackTrace();
        
        	} catch (IOException e) {
        		e.printStackTrace();
        }
        
        
        
        return lines;
    }
    
    
}


