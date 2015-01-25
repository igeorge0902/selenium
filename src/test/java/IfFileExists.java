package test.java;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class IfFileExists {
  
  public void ifFileExists(int increase) {
	  
      String userHome = System.getProperty("user.home");
      String name = userHome + "/Documents/Tests/";
	  
	  String name2 = String.valueOf(increase);
	  File filename = new File(name + name2 + ".png");

	  while(filename.exists()){
	       increase++;
	       // reassign file this while will terminate when #.txt doesnt exist
	       name = String.valueOf(increase);
	       filename = new File(name + name2 + ".png");
	  } // the while should end here
	  // then we check again that #.txt doesnt exist and try to create it
	  if(!filename.exists()) {

	  try {

	      String content = filename.toString();
	      filename.createNewFile();

	      FileWriter fw = new FileWriter(filename.getAbsoluteFile());
	      BufferedWriter bw = new BufferedWriter(fw);
	      bw.write(content);
	      bw.close();

	      System.out.println("Done");

	  }catch (IOException e){
	      e.printStackTrace();
	  }
	  // you had a extra close bracket here causing the issue
	  }
	  // this program will now create a new text file each time its run in ascending order
  }
}
