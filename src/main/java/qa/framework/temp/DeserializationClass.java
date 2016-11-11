package main.java.qa.framework.temp;

import java.io.*;

public class DeserializationClass {

	public static void main(String[] args) {
		
		Names emp = null;
		
			try {
				
			FileInputStream fileIn = new FileInputStream("./employee.txt");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			
			emp = (Names) in.readObject();
			
			in.close();
			fileIn.close();
		
			} catch (IOException i) {
		
				i.printStackTrace();
		return;
		
			} catch (ClassNotFoundException c) {
		
				System.out.println("Employee class not found");
		
				c.printStackTrace();
		return;
	
			}
		
			System.out.println("Deserializing Employee...");
			System.out.println("First Name of Employee: " + emp.firstName);
			System.out.println("Last Name of Employee: " + emp.lastName);
	
	}
}
