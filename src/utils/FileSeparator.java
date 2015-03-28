package utils;

import java.io.File;

import main.TestBase;

public class FileSeparator extends TestBase {

    public static void main(String[] args) {
    	
        String userHome = System.getProperty("user.home");
        String screenShotsFolder = userHome +File.separator+"Documents"+File.separator+"Tests"+File.separator;
        
        System.out.println(TestBase.getCurrentTime());
        
        	int second = 2;
        	 
        	while(second > 0) {
        	   try {
        	     Thread.sleep(500);
        	     System.out.println(screenShotsFolder);

        	   }
        	   catch(Exception e) {}
        	   second--;
        	}

        	System.getProperty("sun.arch.data.model");
        	System.out.println(System.getProperty("sun.arch.data.model"));
        	
    }

}