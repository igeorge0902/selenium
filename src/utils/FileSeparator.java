package utils;

import java.io.File;

public class FileSeparator {

    public static void main(String[] args) {
    	
        String userHome = System.getProperty("user.home");
        String screenShotsFolder = userHome +File.separator+"Documents"+File.separator+"Tests"+File.separator;
        
        	int second = 60;
        	 
        	while(second > 0) {
        	   try {
        	     Thread.sleep(5000);
        	     System.out.println(screenShotsFolder);

        	   }
        	   catch(Exception e) {}
        	   second--;
        	}
    	
       // System.out.println(screenShotsFolder);

    }

}