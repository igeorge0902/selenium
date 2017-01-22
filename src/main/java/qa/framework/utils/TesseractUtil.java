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
import net.sourceforge.tess4j.*;
import net.sourceforge.tess4j.util.LoadLibs;

public class TesseractUtil extends TestBase implements WebElements {
	
	public static void main(String[] args) throws FileNotFoundException, IOException {

		Path libPath32 = Paths.get("lib/win32-x86");
		Path libPath64 = Paths.get("lib/win32-x86-64");

    	Log.info(System.getProperty("sun.arch.data.model"));
    	
		if (libPath32.toFile().isDirectory() && libPath64.toFile().isDirectory()) {
    	
			System.setProperty("jna.library.path", "32".equals(System.getProperty("sun.arch.data.model")) ? libPath32.toString() : libPath64.toString());
			}
		
    	Log.info(System.getProperty("jna.library.path"));
        Tesseract instance = new Tesseract(); 
		
        String workingDir = System.getProperty("user.dir");
		String tessdataFolder = workingDir + File.separator + "tessdata";

        Path tessdata = Paths.get(tessdataFolder);
        	
        	System.setProperty("tessdata", tessdata.toFile().toString());
        	
             tessdataFolder = LoadLibs.getTesseractLibName(); // Maven build bundles English data
             instance.setDatapath(tessdata.toString());
        
        instance.setLanguage("hun");
        
        String imageFile = "image1.jpg";
        BufferedImage image;
        
		try {
			
			image = ImageIO.read(new File(imageFile));
			Log.info("OCR processing is starting...");

			String result = instance.doOCR(image);
            Log.info(result);
            Log.info("OCR processing is done!");
		} catch (IOException | TesseractException e) {
			e.printStackTrace();
		}

	}
}