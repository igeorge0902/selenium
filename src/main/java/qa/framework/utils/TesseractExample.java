package main.java.qa.framework.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

import main.java.qa.framework.main.TestBase;
import main.java.qa.framework.main.WebElements;
import main.java.qa.framework.main.CaptureScreenshotOnFailureListener;
import net.sourceforge.tess4j.*;

public class TesseractExample extends TestBase implements WebElements {
	public static void main(String[] args) throws FileNotFoundException, IOException {

		Path libPath32 = Paths.get("lib/win32-x86");
		Path libPath64 = Paths.get("lib/win32-x86-64");

    	Log.info(System.getProperty("sun.arch.data.model"));
    	
		if (libPath32.toFile().isDirectory() && libPath64.toFile().isDirectory()) {
    	
			System.setProperty("jna.library.path", "32".equals(System.getProperty("sun.arch.data.model")) ? libPath32.toString() : libPath64.toString());
			}
		
    	Log.info(System.getProperty("jna.library.path"));
        Tesseract instance = Tesseract.getInstance();  // JNA Interface Mapping

        //Tesseract1 instance = new Tesseract1(); // JNA Direct Mapping
        //File tessDataFolder = LoadLibs.extractTessResources("tessdata"); // Maven build bundles English data
        
        //String imageFile = "eurotext.png";
        BufferedImage image;
        
		try {
			
			image = ImageIO.read(new File(CaptureScreenshotOnFailureListener.filename));
			
			String result = instance.doOCR(image);
            System.out.println(result);
            
		} catch (IOException | TesseractException e) {
			e.printStackTrace();
		}

    }
}