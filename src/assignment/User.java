package assignment;
import java.util.*;
import java.io.*;

//This user class represent users and their details.
//This class extends the Person class.
public class User extends Person
{
	private String name;
	private String id;
	private String password;
	private String email;
	boolean login = false;
	File userFile = new File("userList.txt");
	
	public String getEmail()
	{
		return email;
	}
	
	public String getId() {
		return id;
	}
	public String getPassword() {
		return password;
	}
	
	public void setId(String nId) {
		id = nId;
	}
	public void setPassword(String nPassword) {
		password = nPassword;
	}
	
	//constructor
	public User(String aId, String aPassword) {
		id = aId;
		password = aPassword;
	}
	public User(String id) {
		this.id = id;
	}
	
	public User() {
		
	}
	public boolean userMenu()
	{
		String choice;
		int cho;
		
		do {// starts a do-while loop that will execute at least once and repeat as long as cho is not equal to 6
			System.out.println("Enter the operation.");
			System.out.println("1. User Sign up.");
			System.out.println("2. User Log in.");
			System.out.println("3. Modify user details.");
			System.out.println("4. List all the user.");
			System.out.println("5. Search a user.");
			System.out.println("6. Exit.");
			while (true) {// starts a nested while loop that will continue running until a valid integer input is entered
			System.out.println("Your choice: ");
			Scanner sc = new Scanner(System.in);
			choice = sc.nextLine();
			try {// starts a try-catch block to handle any exceptions that may occur when parsing the user's input as an integer// starts a try-catch block to handle any exceptions that may occur when parsing the user's input as an integer// starts a try-catch block to handle any exceptions that may occur when parsing the user's input as an integer
				cho = Integer.parseInt(choice);
				break;// breaks out of the while loop if a valid integer input has been entered
			}catch (NumberFormatException e) 
			{
				System.err.println("Input must be a valid integer.");
			}
			}
			switch(cho)
			{
			case 1: 
				signUp();// call a function named signUp() that adds user details
				break;
			case 2: 
				login = logIn();// call a function named logIn() that logs in a user and sets the login variable to true if successful
				break;
			case 3:
				if(login)
				{
					modifyDetails();// call a function named modifyDetails() if the user is logged in
				}
				else
					System.out.println("Please log in to access this function.");
				break;
			case 4:
				try {
					if(login)
					{
						listFile(userFile);// call a function named listFile() that lists all the user data stored in a file named userFile
					}
					else
						System.out.println("Please log in to access this function.");
				}catch(IOException e) {
					System.err.println("Error reading file: " + e.getMessage());
				}
				break;
			case 5:
				try {
					if(login)
					{
						searchFile(userFile);// call a function named searchFile() that searches for user data in a file named userFile
					}
					else
						System.out.println("Please log in to access this function.");
				}catch(IOException e) {
					System.err.println("Error reading file: " + e.getMessage());
				}
				break;
			case 6:
				System.out.println("Exit to main menu.");
				break;
			default:
				System.out.println("Invalid input, please try again.");
				break;
			}
			} while(cho != 6);// repeat the do-while loop as long as cho is not equal to 6
		return login;
	}
	
	
	public void signUp()
	 {
	  boolean cont = true;//initialize boolean 'cont' with the value true
	  Scanner input = new Scanner(System.in);
	 
	  System.out.println("Please enter your name to sign up: ");
	  name = input.nextLine();
	  System.out.println("Please enter your email:");
	  email = input.nextLine();
	  email = validateEmail(email);
	  do {
		  System.out.println("Please enter the password:");
		  password = input.nextLine();
		  if (password.length() < 6)//start a loop to prompt user enter password until the password contains atlease 6 characters 
		  {
			  System.out.println("The password is too short. Please try again.");
		  }
	  }while(password.length() < 6);
	  id = RandomIdGenerator();
	  System.out.println("The user Id given is: " + id);
	
	  
	  while(cont)
	  {
	   try {
	    FileWriter filewriter = new FileWriter(userFile, true);
	    BufferedWriter bufferwriter = new BufferedWriter(filewriter);
	    if(!userFile.exists())
	     userFile.createNewFile();
	  
	    bufferwriter.append("User id: ");
	    bufferwriter.append(String.format("%-10s", id));
	    bufferwriter.append(", User name: ");
	    bufferwriter.append(String.format("%-20s", name));
	    bufferwriter.append(", Email: ");
	    bufferwriter.append(String.format("%-20s", email));
	    bufferwriter.append(", Password: ");
	    bufferwriter.append(String.format("%-10s", password));
	    bufferwriter.append("\r\n");
	    bufferwriter.close();
	    filewriter.close();
	    cont = false;
	   }
	   catch(IOException e)
	   {
	    System.out.println("An error occured. ");
	    e.printStackTrace();
	   }
	    
	  }
	 }
	public boolean logIn()
	{
		System.out.println("Please enter your user name to log in:");
		Scanner input = new Scanner(System.in);
		String searchName = input.nextLine();
		boolean found = false;
		boolean login = false;
		try {
		BufferedReader bufferedreader = new BufferedReader(new FileReader(userFile));
		String line = bufferedreader.readLine();
		while (line != null)
		{
			String findName = line.split(", ")[1].split(":")[1];
        	String name = findName.trim();
        	if (name.toLowerCase().trim().equals(searchName.toLowerCase()))
        	{
        		found = true;
        		System.out.println("The user name is found.");
        		while (!login)
        		{
        			System.out.println("Enter your password:");
        			String pw = input.nextLine();
        			if (pw.trim().equals(line.split(",")[3].split(":")[1].trim()))
        			{
        				login = true;
        				System.out.println("Login successful.");

        			}
        			else
        			{
        				System.out.println("The password is incorrect. Please try again.");
        			}
        			
        		}
        	
	        	
        	}
        	line = bufferedreader.readLine();
 
		}
		if(!found)
		{
			System.out.println("The name is not found. Please try again.");
		}
		}catch (Exception e )
		{
			System.out.println("An error occurs.");
		}
       	return login;
	}
	
	public void modifyDetails()
	{
		System.out.println("Please enter user id to edit user details:");// prompt the user to enter the user ID to edit// prompt the user to enter the user ID to edit// prompt the user to enter the user ID to edit// prompt the user to enter the user ID to edit// prompt the user to enter the user ID to edit
		Scanner input = new Scanner(System.in);
		String searchId = input.next();
		boolean found = false;// initialize a boolean variable to keep track of whether the ID has been found
		String line;
		File tempFile = new File ("temp.txt");
		try {
			BufferedReader bufferedreader = new BufferedReader(new FileReader(userFile));
			PrintWriter writer = new PrintWriter(new FileWriter(tempFile));//create a new temporary file to write modified user details to//create a new temporary file to write modified user details to//create a new temporary file to write modified user details to
			line = bufferedreader.readLine();
	        while (line != null) 
	        {
	        	String findId = line.split(", ")[0].split(":")[1];
	        	String ID = findId.trim();
	        	if (ID.equals(searchId))//check if the current line matches the user ID being searched for
	        	{
	        		found = true;// set the found flag to true and prompt the user to select a field to edit
	        		System.out.println("The user id is found.");
	        		Scanner sc = new Scanner(System.in);
	            	System.out.println("Edit User details:");
	            	System.out.println("1. User name.");
	    	        System.out.println("2. User email.");
	    	        System.out.println("3. User password.");
	    	        System.out.println("Your choice:");
	    	        String choice = sc.nextLine();
	    	        int cho =Integer.parseInt(choice);
	    	        String[] tokens = line.split(", ");
                    String newLine = tokens[0];
	    	        switch (cho)
	    	        {
	    	        case 1:
	    	        	System.out.println("Please enter new user name: ");
			        	String newName = sc.nextLine();
			        	newLine += ", " + tokens[3];
			        	newLine += ", User name:" + String.format("%-20s", newName);
			        	newLine += ", " + tokens[2];
			      
			        	break;

	    	        case 2:
	    	        	newLine += ", " + tokens[1];
	    	        	System.out.println("Please enter new user email: ");
	    	        	String newEmail = sc.nextLine();
	    	        	newEmail = validateEmail(newEmail);
                        newLine += ", Email:" + String.format("%-20s", newEmail);
                        newLine += ", " + tokens[3];
                       
                        break;
                        
	    	        case 3:
	    	        	newLine += ", " + tokens[1];
	    	        	newLine += ", " + tokens[2];
	    	        	System.out.println("Please enter new user password: ");
	    	        	String newPassword = sc.nextLine();
                        newLine += ", Password:" + String.format("%-10s", newPassword);
                      
                        break;
	    	  
	    	        default:
	    	        	System.out.println("Invalid input. Please try again.");
	    	        	break;
	        	}
	    	        System.out.println("The new information is:");
	    	        System.out.println(newLine);
	    	        writer.println(newLine);
	        	}
	    	    else
	    	    {
	    	        writer.println(line);
	            }
	    	        
	        	line = bufferedreader.readLine();
	        	
	        }
	        
	        writer.close();
	        bufferedreader.close();
	        if (!found)// if the search ID was not found, print a message
	        {
	        	System.out.println("ID not found.");
	        }
	        
	        try {
	            if (!userFile.delete()) {// if the older user file not deleted, print a message
	                throw new Exception("Could not delete input file");
	            }
	            if (!tempFile.renameTo(userFile)) {// if the temporary file not renamed to user file, print a message
	                throw new Exception("Could not rename temporary file");
	            }
	        } catch (Exception e) {
	            System.out.println(e.getMessage());
	            return;
	        }
	    } catch (IOException e) {
	        System.out.println("An error occurred while reading or writing the file.");
	        e.printStackTrace();
	    } catch (NumberFormatException nfe)
		{
			System.err.println("Input must be a valid integer.");
		}
	    
		catch(Exception e)//if an exception occurs
		{
			e.printStackTrace();//print the stack trace of exception
		}
		}
	
	
}
