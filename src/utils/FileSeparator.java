package utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import main.TestBase;
import main.WebElements;

public class FileSeparator extends TestBase implements WebElements {
	
	
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
        	
        	String book1 = "Book1";
            String book2 = "Book2";
            String book3 = "Book3";
            String book4 = "Book4";

            
            List<String> bookList = new ArrayList<String>();
            bookList.add(book1);
            bookList.add(book2);
            bookList.add(book3);
            bookList.add(book4);

            
        	TestBase.writeFile(urlsFile, bookList);
        	
    }

}