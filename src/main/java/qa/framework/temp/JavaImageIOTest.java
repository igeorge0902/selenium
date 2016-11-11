package main.java.qa.framework.temp;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import javax.imageio.ImageIO;

import main.java.qa.framework.main.TestBase;
 
public class JavaImageIOTest extends TestBase
{
 
  public JavaImageIOTest() throws IOException
  {
    File f = new File("/Users/georgegaspar/Pictures/movies/interstellar.jpg");
	byte[] bytes = new byte[(int) f.length()];
	
    BufferedInputStream bis = new BufferedInputStream(new FileInputStream(f));
    BufferedImage images = ImageIO.read(bis);
    ByteArrayOutputStream baos = new ByteArrayOutputStream(bytes.length);
    ImageIO.write(images, "jpg", baos);
  }
 
  public static void main(String[] args) throws IOException
  {
	long start = System.currentTimeMillis();

    new JavaImageIOTest();
    
	TestBase.memory();
	long time = System.currentTimeMillis() - start;
	Log.info("Elapsed time:" +time);
  }
 
}