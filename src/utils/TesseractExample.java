package utils;

import java.io.File;

import net.sourceforge.tess4j.*;

public class TesseractExample {

	public static void main(String[] args) {
		String userHome = System.getProperty("user.home");
		String imageFilePath = userHome +File.separator+File.separator + "Tests" + File.separator+"ScreenShot1.png";
		File imageFile = new File(imageFilePath);
		Tesseract instance = Tesseract.getInstance();
		
        try {
            String result = instance.doOCR(imageFile);
            System.out.println(result);
        } catch (TesseractException e) {
            System.err.println(e.getMessage());
        }
	
	}
}


