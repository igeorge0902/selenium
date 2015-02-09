package utils;

import java.io.File;

public class FileSeparator {

    public static void main(String[] args) {
    	
        String userHome = System.getProperty("user.home");
        String screenShotsFolder = userHome +File.separator+"Documents"+File.separator+"Tests"+File.separator;
    	
        System.out.println(screenShotsFolder);

    }

}