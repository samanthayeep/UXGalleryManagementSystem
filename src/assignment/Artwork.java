package assignment;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
public class Artwork 
{
 private String id;
 private String title;
 private String datePurchased;
 private String dateSold;
 private String artist;
 private double price;

 
 File artworkFile = new File("artworkList.txt");
 
 public Artwork()
 {
  
 }
 
 public String getTitle()
 {
  return title;
 }
 
 public String getDatePurchased()
 {
  return datePurchased;
 }
 
 public String getDateSold()
 {
  return dateSold;
 }
 
 public String getArtist()
 {
  return artist;
 }
 
 public double getPrice()
 {
  return price;
 }
 
 public void setTitle(String title)
 {
  this.title = title;
 }
 
 public void setDatePurchased(String datePurchased)
 {
  this.datePurchased = datePurchased;
 }
 
 public void setDateSold(String dateSold)
 {
  this.dateSold = dateSold;
 }
 
 public void setArtist(String artist)
 {
  this.artist = artist;
 }
 
 public void getPrice(double price)
 {
  this.price = price;
 }
 
 public void artworkMenu()
 {
  String choice;
  int cho;
  do {
	  System.out.println("Enter the operation.");
	  System.out.println("1. add artwork details.");
	  System.out.println("2. modify artwork details.");
	  System.out.println("3. delete artwork details.");
	  System.out.println("4. show artwork inventory.");
	  System.out.println("5. search an artwork.");
	  System.out.println("6. Exit.");
	  while (true)
	  {
		  System.out.println("Your choice: ");
		  Scanner input = new Scanner(System.in);
		  choice = input.nextLine();
		  try
		  {
			  cho = Integer.parseInt(choice);
			  break;
		  }
		  catch(NumberFormatException e)
		  {
			  System.err.println("Input must be valid integer.");
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
		  deleteDetails();
		  break;
	  case 4:
		  try
		  {
			  listArtworkFile();
		  }
		  catch(IOException e)
		  {
			  System.err.println("Error reading file: " + e.getMessage());
		  }
		  break;
	  case 5:
		  try
		  {
			  searchArtworkFile();
		  }
		  catch(IOException e)
		  {
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
 }while(cho != 6);
 }
 
 public void addDetails()
 {
  boolean cont = true;
  Scanner input = new Scanner(System.in);
 
  System.out.println("Please enter the artwork title: ");
  title = input.nextLine();
  
  boolean validSoldDate = false;
  while(!validSoldDate)
  {
  System.out.println("Date of sold:(dd/mm/yyyy) ");
  dateSold = input.nextLine();
  validSoldDate = isValidDate(dateSold, "dd/MM/yyyy");
  if (!validSoldDate)
	  System.out.println("Invalid date format, please try again.");
  }

  boolean validPurchasedDate = false;
  while(!validPurchasedDate)
  {
  System.out.println("Date of Purchased:(dd/mm/yyyy) ");
  datePurchased = input.nextLine();
  validPurchasedDate = isValidDate(datePurchased, "dd/MM/yyyy");
  if (!validPurchasedDate)
	  System.out.println("Invalid date format, please try again.");
  }
	  
  System.out.println("Please enter the artwork's artist: ");
  artist = input.nextLine();
  System.out.println("Please enter the price of artwork: ");
  price = input.nextDouble();
  
  while(cont)
  {
   try {
    FileWriter filewriter = new FileWriter(artworkFile, true);
    BufferedWriter bufferwriter = new BufferedWriter(filewriter);
    if(!artworkFile.exists())
     artworkFile.createNewFile();
  
    bufferwriter.append("Artwork title: ");
    bufferwriter.append(String.format("%-20s", title));
    bufferwriter.append(", Date Purchased: ");
    bufferwriter.append(String.format("%-10s", datePurchased));
    bufferwriter.append(", Date sold: ");
    bufferwriter.append(String.format("%-10s", dateSold));
    bufferwriter.append(", Artist: ");
    bufferwriter.append(String.format("%-20s", artist));
    bufferwriter.append(", Price: ");
    bufferwriter.append(String.format("%-10s", price));
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
 
 public void modifyDetails()
	{
	 System.out.println("Please enter the title of the artwork to be modified:");
		Scanner input = new Scanner(System.in);
		String searchTitle = input.next();
		boolean found = false;
		String line;
		File tempFile = new File ("temp.txt");
		try {
			BufferedReader bufferedreader = new BufferedReader(new FileReader(artworkFile));
			PrintWriter writer = new PrintWriter(new FileWriter(tempFile));
			line = bufferedreader.readLine();
	        while (line != null) 
	        {
	        	String findTitle = line.split(", ")[0].split(":")[1];
	        	String title = findTitle.trim();
	        	if (title.toLowerCase().contains(searchTitle.toLowerCase()))
	        	{
	        		found = true;
	        		System.out.println("The artwork is found.");
	        		Scanner sc = new Scanner(System.in);
	            	System.out.println("Edit Artwork details:");
	            	System.out.println("1. Artwork title.");
	    	        System.out.println("2. Date Purchased.");
	    	        System.out.println("3. Date Sold.");
	    	        System.out.println("4. Artwork artist.");
	    	        System.out.println("5. Artwork price.");
	    	        System.out.println("Your choice:");
	    	        String choice = sc.nextLine();
	    	        int cho = Integer.parseInt(choice);
	    	        String[] tokens = line.split(", ");
                 String newLine = null;
	    	        switch (cho)
	    	        {
	    	        case 1:
	    	        	System.out.println("Please enter new artwork title: ");
			        	String newTitle = sc.nextLine();
			        	newLine = "Artwork title:" + String.format("%-20s", newTitle);
			        	newLine += ", " + tokens[1];
			        	newLine += ", " + tokens[2];
			        	newLine += ", " + tokens[3];
			        	newLine += ", " + tokens[4];
			        	break;

	    	        case 2:
	    	        	newLine = tokens[0];
	    	        	System.out.println("Please enter new date purchased: ");
	    	        	String newPurchasedDate = sc.nextLine();
	    	        	newLine += ", Date purchased:" + String.format("%-10s", newPurchasedDate);
	    	        	newLine += ", " + tokens[2];
	    	        	newLine += ", " + tokens[3];
	    	        	newLine += ", " + tokens[4];
	    	        	
	    	        	break;
                     
	    	        case 3:
	    	        	newLine = tokens[0];
	    	        	newLine += ", " + tokens[1];
	    	        	System.out.println("Please enter new date sold: ");
	    	        	String newSoldDate = sc.nextLine();
	    	        	newLine += ", Date sold:" + String.format("%-10s", newSoldDate);
	    	        	newLine += ", " + tokens[3];
	    	        	newLine += ", " + tokens[4];
	    	        	break;
                     
	    	        case 4:
	    	        	newLine = tokens[0];
	    	        	newLine += ", " + tokens[1];
	    	        	newLine += ", " + tokens[2];
	    	        	System.out.print("Enter the new artwork artist: ");
	    	        	String newArtist = sc.nextLine();
	    	        	newLine += ", Artist:" + String.format("%-20s", newArtist);
	    	        	newLine += ", " + tokens[4];
	    	        	break;
                     
	    	        case 5:
	    	        	newLine = tokens[0];
	    	        	newLine += ", " + tokens[1];
	    	        	newLine += ", " + tokens[2];
	    	        	newLine += ", " + tokens[3];
	    	        	System.out.print("Enter the new price: ");
	    	        	String newPrice = sc.nextLine();
	    	        	newLine += ", Price:" + String.format("%-10s", newPrice);
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
	        	System.out.println("ID not found.");
	        }
	        
	        try {
	            if (!artworkFile.delete()) {
	                throw new Exception("Could not delete input file");
	            }
	            if (!tempFile.renameTo(artworkFile)) {
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
	

	public void searchArtwork() throws IOException
	{
		boolean cont = true;
		Scanner input = new Scanner(System.in);
		while (cont)
		{
			String str;
			String nameSearch;
			System.out.println("Please enter the name of the artist to search for the artwork created: ");
			nameSearch = input.nextLine();
			FileReader filereader = new FileReader(artworkFile);
			BufferedReader bufferedreader = new BufferedReader(filereader);
			Boolean found = false;
			while ((str=bufferedreader.readLine()) != null && !found)
			{
				if (str.toLowerCase().contains(nameSearch.toLowerCase()))
				{
					found = true;
					System.out.println("The artist is found. The artwork created is listed below:");
					System.out.println(str);
				}
			}
			if (!found)
				System.out.println("The artist is not found.");
			System.out.println("Do you want to continue? y/n:");
			String choice = input.nextLine();
			if (choice.toLowerCase().equals("n"))
			{
				cont = false;
			}
		}
			
		}

	public static boolean isValidDate(String dateStr, String format) 
	{
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        sdf.setLenient(false);
        try {
            Date date = sdf.parse(dateStr);
            if (!dateStr.equals(sdf.format(date))) {
                return false;
            }
        } catch (ParseException e) {
            return false;
        }
        return true;
	}
	
	
	public void deleteDetails()
	{
		String tempFileName = "temp.txt";

		File tempFile = new File(tempFileName);

		System.out.println("Enter the name to delete: ");
		Scanner input = new Scanner(System.in);
		String deleteName = input.nextLine();

		try {
			BufferedReader reader = new BufferedReader(new FileReader(artworkFile));
			PrintWriter writer = new PrintWriter(new FileWriter(tempFile));

			String currentLine;
			while ((currentLine = reader.readLine()) != null) {
				if (!currentLine.trim().toLowerCase().contains(deleteName.toLowerCase())) {
					writer.println(currentLine);
				}
			}
			System.out.println("The information is successfully deleted.");
			writer.close();
			reader.close();

			if (!artworkFile.delete()) {
				System.out.println("Could not delete file");
				return;
			}

			if (!tempFile.renameTo(artworkFile)) {
				System.out.println("Could not rename file");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void searchArtworkFile() throws IOException
	{
		boolean cont = true;
		Scanner input = new Scanner(System.in);
		while (cont)
		{
			String str;
			String titleSearch;
			System.out.println("Please enter the title of the artwork to search: ");
			titleSearch = input.nextLine();
			FileReader filereader = new FileReader(artworkFile);
			BufferedReader bufferedreader = new BufferedReader(filereader);
			Boolean found = false;
			while ((str=bufferedreader.readLine()) != null && !found)
			{
				if (str.toLowerCase().contains(titleSearch.toLowerCase()))
				{
					found = true;
					System.out.println("The artwork is found.");
					System.out.println(str);
				}
			}
			if (!found)
				System.out.println("Artwork not found.");
			String choice = null;
			do {
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

	public void listArtworkFile() throws IOException
	{
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(artworkFile));
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
}
 







