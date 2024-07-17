package assignment;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Pattern;

//This is a superclass that contains the common methods and attributes used by the subclasses
public abstract class Person 
{
	private String name;
	private String id;
	private String email;
	private String contactNum;
	
	public String getEmail() 
	{
		return email;
	}
	public String getContactNum()
	{
		return contactNum;
	}
	public String getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	
	public void setEmail (String nEmail)
	{
		email = nEmail;
	}
	public void setContactNum(String contactNum)
	{
		this.contactNum = contactNum;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	
	public abstract void modifyDetails();
	
	//Method to delete details from file
	public void deleteDetails(File file)
	{
		String tempFileName = "temp.txt";

		File tempFile = new File(tempFileName);

		System.out.println("Enter the name to delete: ");
		Scanner input = new Scanner(System.in);
		String deleteName = input.nextLine();

		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			PrintWriter writer = new PrintWriter(new FileWriter(tempFile));

			String currentLine;
			while ((currentLine = reader.readLine()) != null) {
				//If the current line does not contain name to be deleted,write it to the temporary file
				if (!currentLine.trim().toLowerCase().contains(deleteName.toLowerCase())) {
					writer.println(currentLine);
				}
			}
			System.out.println("The information is successfully deleted.");
			writer.close();
			reader.close();

			if (!file.delete()) {
				System.out.println("Could not delete file");
				return;
			}

			if (!tempFile.renameTo(file)) {
				System.out.println("Could not rename file");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//Method to validate email format
	public String validateEmail(String email) 
	{
	    /*email entered allow upper and lower case a-z,0-9 number and may 
	    contain special character but two dots cannot next to each other 
	    and dots cannot at the 1st and last character in email*/
	    String emailRegex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+\\.[a-zA-Z]{2,}$";
	    Pattern pattern = Pattern.compile(emailRegex);
	    while (!pattern.matcher(email).matches()) {
	        System.out.println("Invalid email address. Please enter again: ");
	        Scanner sc = new Scanner(System.in);
	        email = sc.nextLine();
	        
	    }
	    return email;
	}

	//Method to validate contact number format
	public String validateContactNum(String contactNum) 
	{
	    if (contactNum.matches("[0-9]+")) 
	    {
	        this.contactNum = contactNum;
	    } 
	    else 
	    {
	        System.out.println("Invalid contact number. Please enter again and make sure it only contains numbers: ");
	        Scanner sc = new Scanner(System.in);
	        contactNum=sc.nextLine();
	    }
	    return contactNum;
	}
	// This method searches for a given name in a file
	public void searchFile(File file) throws IOException
	{
		boolean cont = true;
		Scanner input = new Scanner(System.in);
		while (cont)
		{
			String str;
			String nameSearch;
			System.out.println("Please enter the name to search: ");
			nameSearch = input.nextLine();
			FileReader filereader = new FileReader(file);
			BufferedReader bufferedreader = new BufferedReader(filereader);
			Boolean found = false;
			while ((str=bufferedreader.readLine()) != null && !found)
			{
				if (str.toLowerCase().contains(nameSearch.toLowerCase()))
				{
					found = true;
					System.out.println("The name is found.");
					System.out.println(str);
				}
			}
			if (!found) // If the name is not found, print a message to the console.
				System.out.println("Name not found.");
			String choice = null;
			do {			// Prompt the user to continue searching or exit.
			System.out.println("Do you want to continue? y/n:");
			choice = input.nextLine();
			switch (choice)
			{
			case "Y" :
			case "y" :
				break;
			case "N" :
			case "n" :
				cont = false;
				break;
			default:
				System.out.println("Invalid input, please enter y/n: ");
				break;
			}
			}while(!choice.equalsIgnoreCase("y") && !choice.equalsIgnoreCase("n"));
		}
		
			
		}
	// This method lists the contents of a file.
	// If the file is empty, a message is printed to the console.
	public void listFile(File file) throws IOException
	{
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
			String line;
			if ((line =bufferedReader.readLine()) == null){
				System.out.println("There is no data.");
			}
			else
				System.out.println(line);
				
			while ((line=bufferedReader.readLine()) != null)
			{
				System.out.println(line);
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		Scanner input = new Scanner(System.in);
		System.out.println("Enter anything to continue.");
		String conti = input.nextLine();
		
	}
	// This method generates a random ID.
	public String RandomIdGenerator() 
	{
	      Random rand = new Random();
	      int randomNum = rand.nextInt(900000) + 100000;
	      String randomId = Integer.toString(randomNum);
	      return randomId;
		   
	}
	
	
	
	
}