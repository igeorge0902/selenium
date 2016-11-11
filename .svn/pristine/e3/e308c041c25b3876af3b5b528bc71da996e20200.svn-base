package main.java.qa.framework.temp;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.swing.JOptionPane;


public class GradeDemo {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		Map<String, String> studentGrades = new HashMap<String, String>();
		String userName;
		String userGrade;
		String exit = "done";
		
		String choice = new String();

		//This while loop just keeps going back to the main menu until the user
		//chooses option 6, which is to exit
		 while (!(choice.equals("4"))){

			//Printing the main menu
			System.out.println("1. Add new Student and Grades");
			System.out.println("2. Change a grade");
			System.out.println("3. remove a student");
			System.out.println("4. Quit Program");
		   
		   System.out.println("Select an option, enter its number, and " +
					"press enter:");

			//Take choice input from user
			choice = in.next();

			//Make some space after the input to reduce clutter
			System.out.println("\n");

			//User chooses 1 to deposit
			if (choice.equals("1")){
				
				
			userName = JOptionPane.showInputDialog("Please enter a name: Enter 'done to quit");
			
			userGrade = JOptionPane.showInputDialog("Please enter a grade:");
		   
			studentGrades.put(userName, userGrade);
			System.out.println(studentGrades);
		}
			
			else  if (choice.equals("2")){
				
				
			userName = JOptionPane.showInputDialog("Enter the name of the student you would like to edit: ");
			
			
			if (studentGrades.containsKey(userName)==false){
				System.out.println("That student does not exist");
				
				
			}
			else {
			userGrade = JOptionPane.showInputDialog("Please enter their new grade:");
			studentGrades.put(userName, userGrade);
			System.out.println(studentGrades);
			}
			
			
		   
			
		}
			
			
			
			
			
				else  if (choice.equals("3")){
				
				
			userName = JOptionPane.showInputDialog("Enter the name of the student you would like to remove: ");
			
			
			if (studentGrades.containsKey(userName)==false){
				System.out.println("That student does not exist");
				
				
			}
			else {
			
			studentGrades.remove(userName);
			System.out.println(studentGrades);
			}
			
			
		   
			
		}
			
			
			
			
			
			
			
			
				else  if (choice.equals("4")){
				
					
			 System.out.println("The final students and grades are");		
			System.out.println(studentGrades);
			}

			//If they enter anything else, it is an invalid choice, start over
			else {
				System.out.println("Invalid option, please try again:");
			}
				
			
			
			
			
			
			
		   
			
		}
	   
			}
		
		
		
		 }
   

