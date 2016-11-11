package main.java.qa.framework.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;
import java.nio.file.Path;

import main.java.qa.framework.main.WebElements;

 
@SuppressWarnings("unused")
public class deleteLines implements WebElements {
 
	public static void main(String[] args) throws Exception {
        
		// Creates file to write to
		Writer output = null;

		output = new BufferedWriter(new FileWriter("lib/output.txt"));
		
		String newline = System.getProperty("line.separator");
		         
		// Read in a file & process line by line
		FileInputStream in = new FileInputStream("lib/urls.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String strLine;
		                 
		while ((strLine = br.readLine()) != null) {
		             
		System.out.println(strLine);
		             
		// Open up standard input from command
		BufferedReader br2 = new BufferedReader(new InputStreamReader(System.in));
		
		
		String command = null;
		 
		System.out.println("Delete line?");
		 
		//TODO: it could be used for keeping only a given content(with regex, I think)
		try {
		command = br2.readLine();
		if (command.equals("delete")){
		
		System.out.println("Line Deleted.");
		System.out.println("");
		}else{
			
		// Write non deleted lines to file
		output.write(strLine);
		output.write(newline);
		}
		                  
		} catch (IOException ioe) {
		System.out.println("IO error reading command line input");
		System.exit(1);
		}
		             
		}
		output.close();
		br.close();
		System.out.println("End of file.");
		  }
	
		}
