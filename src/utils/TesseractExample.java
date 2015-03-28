package utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import net.sourceforge.tess4j.*;

public class TesseractExample {
    public static void main(String[] args) {
    	
    	
    	//System.setProperty("jna.library.path", "lib");
    	System.setProperty("jna.library.path", "32".equals(System.getProperty("sun.arch.data.model")) ? "lib/win32-x86" : "lib/win32-x86-64");
        Tesseract instance = Tesseract.getInstance();  // JNA Interface Mapping
        //Tesseract1 instance = new Tesseract1(); // JNA Direct Mapping
        //File tessDataFolder = LoadLibs.extractTessResources("tessdata"); // Maven build bundles English data
        
        String imageFile = "eurotext.png";
        BufferedImage image;
        
		try {
			
			image = ImageIO.read(new File(imageFile));
			String result = instance.doOCR(image);
            System.out.println(result);
            
		} catch (IOException | TesseractException e) {
			e.printStackTrace();
		}

    }
}


