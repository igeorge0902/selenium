package main.java.qa.framework.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import main.java.qa.framework.main.TestBase;
import main.java.qa.framework.main.WebElements;

public class FileSeparator extends TestBase implements WebElements {

	static int second = 0;
	static String userHome = "";
	static String screenShotsFolder = "";
	static List<File> files = new ArrayList<File>();
	static Path testOutput;

	  private volatile String myField;

	  public String getMyField() {
	    String tmp = myField;
	    if (tmp == null) {
	      synchronized (this) {
	        tmp = myField;
	        if (tmp == null) {
	          myField = tmp = new String();
	        }
	      }
	    }
	    return tmp; // Using tmp here instead of myField avoids an memory update
	  }
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		long start = System.currentTimeMillis();
				
		PropertyUtils.listProperties();

		userHome = System.getProperty("user.home");
		screenShotsFolder = userHome + File.separator + "Documents"
				+ File.separator + "Tests" + File.separator;

		System.out.println(TestBase.getCurrentTime());
		second = 4;

		while (second > 0) {
			try {
				Thread.sleep(500);
				System.out.println(screenShotsFolder);

			} catch (Exception e) {
			}
			second--;
		}

		System.getProperty("sun.arch.data.model");
		System.out.println(System.getProperty("sun.arch.data.model"));

		String book1 = "Book1";
		String book2 = "Book2";
		String book3 = "Book3";
		String book4 = "Book4";
		String book5 = "Book5";


		List<String> bookList = new ArrayList<String>();
		bookList.add(book1);
		bookList.add(book2);
		bookList.add(book3);
		bookList.add(book4);
		bookList.add(book5);


		TestBase.writeFile(urlsFile, bookList);
		System.out.println(urlsFile);

		//TestBase.readFile(Paths.get("scripts/create_db.sql"));

		Path testOutput = Paths.get("test-output/html");

		if (testOutput.toFile().exists()) {
			try {
				System.out.println("Real path: "
						+ testOutput.toRealPath(LinkOption.NOFOLLOW_LINKS));
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("The directory does not exist");
		}


		File[] children = testOutput.toFile().listFiles();

		for (int i = 0; i < children.length; i++) {
			System.out.println(children[i]);

			files.add(children[i]);
			System.out.println(files);

		}
		
		TestBase.memory();
		long time = System.currentTimeMillis() - start;
		Log.info("Elapsed time:" +time);
	}
}