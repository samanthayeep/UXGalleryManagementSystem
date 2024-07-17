package assignment;

import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;

public class GalleryApp 
{
	private static boolean login = false;// Declare and initialize a boolean variable named login as false and this variable will keep track of whether the user has logged in or not
	private static boolean cont = true;// Declare and initialize a boolean variable named 'cont' as true and this variable will keep the program running until the user decides to exit the program
	public static void main (String [] args) throws IOException
	{
		
		
		while(cont)// Start a while loop that will keep running as long as 'cont' is true
		{
			// Display menu option for the user
			System.out.println("Welcome to UX Gallery management system.");
			System.out.println("1. User log in/sign up.");
			System.out.println("2. Record customer information.");
			System.out.println("3. Record artist information.");
			System.out.println("4. Record artwork information.");
			System.out.println("5. Exit the system. ");
			
			System.out.println("Your choice: ");// Prompt the user to enter their choice
			int cho = 0;
			
			try {// Start a try block to catch any NumberFormatException that may occur
				Scanner input = new Scanner(System.in);
				String choice = input.nextLine();
				cho = Integer.parseInt(choice);
			}catch(NumberFormatException e)// Catch any NumberFormatException that occurs and print an error message/ Catch any NumberFormatException that occurs and print an error message/ Catch any NumberFormatException that occurs and print an error message
			{
				System.err.println("Input must be a valid integer.");
			}
			switch(cho)
			{
			case 1:
				User users = new User();
				login = users.userMenu();
				break;
			case 2:
				if (login)// Check if the user has logged in
				{
					Customer customer = new Customer();
					customer.customerMenu();// Call the customerMenu() method of the Customer object.
				}
				else// If the user has not logged in,display this message
					System.out.println("Please login to gain access to this funciton.");
				break;
			case 3:
				if (login)
				{
					Artist artists = new Artist();
					artists.artistMenu();// Call the artistMenu() method of the Artist object
				}
				else
					System.out.println("Please login to gain access to this funciton.");
				break;
			case 4:
				Artwork artworks = new Artwork();
				artworks.artworkMenu();// Call the artworkMenu() method of the Artwork object
				break;
			case 5:
				System.out.println("Thank you for using this system.");
				cont = false;
				break;
			default:
				System.err.println("Please enter 1-5.");
			}
			
		}
		
		
	}

}
 