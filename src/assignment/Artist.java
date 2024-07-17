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
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.StringTokenizer;

//This Artist class represent artists and their details.
//This class extends the Person class.
public class Artist extends Person
{
	private String name;
	private String specialty;
	private String status;
	private double max_price;
	private double min_price;
	File artistFile= new File("artistList.txt");

	public Artist()
	{
		
	}

	public String getSpecialty()
	{
		return specialty;
	}
	
	public String getStatus()
	{
		return status;
	}
	
	public double getMaxPrice()
	{
		return max_price;
	}
	
	public double getMinPrice()
	{
		return min_price;
	}
	
	public void artistMenu()
	{
		String choice;
		int cho;
		do {
		System.out.println("Enter the operation.");
		System.out.println("1. add artist details.");
		System.out.println("2. modify artist details.");
		System.out.println("3. delete artist details.");
		System.out.println("4. list all the artists.");
		System.out.println("5. search an artist.");
		System.out.println("6. search the artwork created by an artist.");
		System.out.println("7. Exit.");
		while (true) {
		System.out.println("Your choice: ");
		Scanner input = new Scanner(System.in);
		choice = input.nextLine();
		try {
			cho = Integer.parseInt(choice);
			break;
		}catch (NumberFormatException e) {
			System.err.println("Input must be a valid integer.");
		}
		}
		switch(cho)
		{
		case 1: 
			addDetails();
			break;
		case 2: 
			modifyDetails();
			break;
		case 3:
			deleteDetails(artistFile);
			break;
		case 4:
			try {
				listFile(artistFile);
			}catch(IOException e) {
				System.err.println("Error reading file: " + e.getMessage());
			}
			break;
		case 5:
			try {
				searchFile(artistFile);
			}catch(IOException e) {
				System.err.println("Error reading file: " + e.getMessage());
			}
			break;
		case 6:
			Artwork artwork = new Artwork();
			try {
			artwork.searchArtwork();
			}
			catch (IOException e)
			{
				System.err.println("Error reading file: " + e.getMessage());
			}
			break;
		case 7:
			System.out.println("Exit to main menu.");
			break;
		
		default:
			System.out.println("Invalid input, please try again.");
	        
		}
		} while(cho != 7);
		
	}
	//add details of new artist to the artistList.txt file
	public void addDetails() 
	{
		boolean cont = true;
		Scanner input = new Scanner(System.in);
		try
		{
		System.out.println("Please enter the artist name: ");
		name = input.nextLine();
		System.out.println("Please enter the specialty of the artist: ");
		specialty = input.nextLine();
		String result;
		do{
			System.out.println("Is the artist alive? Y/N: ");
			result = input.next();
			switch (result)
			{
				case "Y" :
				case "y" :
					status = "Alive";
					break;
				case "N" :
				case "n" :
					status = "Deceased";
					break;
				default:
					System.out.println("Invalid input, please enter y/n: ");
					status = "not known";
					break;
			}
		}while(!result.equalsIgnoreCase("y") && !result.equalsIgnoreCase("n"));
		System.out.println("Enter the highest price of the artwork created: ");
		max_price = input.nextDouble();
		System.out.println("Enter the lowest price of the artwork created: ");
		min_price = input.nextDouble();
		while(cont)
		{
			
				FileWriter filewriter = new FileWriter(artistFile,true);
				BufferedWriter bufferwriter = new BufferedWriter(filewriter);
				if (!artistFile.exists())
					artistFile.createNewFile();
				bufferwriter.append("Artist name:");
				bufferwriter.append(String.format("%-20s", name));
				bufferwriter.append(",  Specialty:");
				bufferwriter.append(String.format("%-20s", specialty));
				bufferwriter.append(",  Status:");
				bufferwriter.append(String.format("%-10s", status));
				bufferwriter.append(",  Price range:");
				bufferwriter.append(String.format("%-10s", min_price));
				bufferwriter.append("  -");
				bufferwriter.append(String.format("%-10s", max_price));
				bufferwriter.append("\r\n");
				bufferwriter.close();
				filewriter.close();
				cont = false;
				System.out.println("Information recorded successfully.");
			
			
		}
		}
		catch (IOException e)
		{
		System.out.println("An error occured.");
		e.printStackTrace();
		}
		catch (Exception e)
		{
			System.out.println("Invalid input. Please try again.");
		}
	}
	
	//This method allows the user to modify the details of an artist in the artistList.txt file.
	public void modifyDetails()
	{
		System.out.println("Please enter artist name to edit artist details:");
		Scanner input = new Scanner(System.in);
		String searchName = input.next();
		boolean found = false;
		String line;
		File tempFile = new File ("temp.txt");
		try {
			BufferedReader bufferedreader = new BufferedReader(new FileReader(artistFile));
			PrintWriter writer = new PrintWriter(new FileWriter(tempFile));
			line = bufferedreader.readLine();
	        while (line != null) 
	        {
	        	String findName = line.split(", ")[0].split(":")[1];
	        	String name = findName.trim();
	        	if (name.toLowerCase().contains(searchName.toLowerCase()))
	        	{
	        		found = true;
	        		System.out.println("The artist is found.");
	        		Scanner sc = new Scanner(System.in);
	            	System.out.println("Edit Artist details:");
	            	System.out.println("1. Artist name.");
	    	        System.out.println("2. Artist specialty.");
	    	        System.out.println("3. Artist alive or deceased.");
	    	        System.out.println("4. Price ranges of artwork.");
	    	        System.out.println("Your choice:");
	    	        String choice = sc.nextLine();
	    	        int cho = Integer.parseInt(choice);
	    	        String[] tokens = line.split(", ");
	    	        String newLine = null;
 
	    	        switch (cho)
	    	        {
	    	        case 1:
	    	        	System.out.println("Please enter new artist name: ");
			        	String newName = sc.nextLine();
			        	newLine = "Artist name:" + String.format("%-20s", newName);
			        	newLine += ", " + tokens[1];
			        	newLine += ", " + tokens[2];
			        	newLine += ", " + tokens[3];
			        	break;

	    	        case 2:
	    	        	newLine =  tokens[0];
	    	        	System.out.println("Please enter new specialty: ");
	    	        	String newSpecialty = sc.nextLine();
                        newLine += ", Specialty:" + String.format("%-20s", newSpecialty);
                        newLine += ", " + tokens[2];
                        newLine += ", " + tokens[3];
                        break;
                        
	    	        case 3:
	    	        	newLine = tokens[0];
	    	        	newLine += ", " + tokens[1];
	    	        	System.out.println("Is the artist alive? Y/N: ");
	    	        	String newStatus = sc.nextLine();
	    	        	switch (newStatus)
	    	    		{
	    	    		case "Y" :
	    	    		case "y" :
	    	    			status = "Alive";
	    	    			break;
	    	    		case "N" :
	    	    		case "n" :
	    	    			status = "Deceased";
	    	    			break;
	    	    		default:
	    	    			System.out.println("Invalid input, please enter y/n: ");
	    	    			status = "not known";
	    	    			break;
	    	    		}while(!newStatus.equalsIgnoreCase("y") && !newStatus.equalsIgnoreCase("n"));
                        newLine += ", Status:" + String.format("%-10s", newStatus);
                        newLine += ", " + tokens[3];
                        break;
                        
	    	        case 4:
	    	        	newLine = tokens[0];
	    	        	newLine += ", " + tokens[1];
	    	        	newLine += ", " + tokens[2];
	    	        	System.out.print("Please enter the new price range: ");
                        String newPrice = sc.nextLine();
                        newLine += ", Price range:" + String.format("%-21s", newPrice);
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
	        if (!found)
	        {
	        	//If the name is not found, an appropriate message is displayed.
	        	System.out.println("The name is not found.");
	        }
	        
	        try {
	            if (!artistFile.delete()) {
	            	//If artist file is not deleted ,this message will be displayed.
	                throw new Exception("Could not delete input file");
	            }
	            if (!tempFile.renameTo(artistFile)) {
	            	//If temporary file is not renamed ,this message will be displayed.
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
	    
		catch(Exception e)
		{
			e.printStackTrace();
		}
		}
	
	
}






