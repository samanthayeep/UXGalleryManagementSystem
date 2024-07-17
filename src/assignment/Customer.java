package assignment;
import java.io.*;
import java.util.*;

//This customer class represent customers and their details.
//This class extends the Person class.
	public class Customer extends Person 
	{
	
		private String name;
		private String id;
		private String email;
		private String address;
		private String contactNum;
		File customerFile=new File("customerList.txt");
		
		public Customer()
		{
			
		}
		
		public String getAddress()
		{
			return address;
		}
		
		public void setAddress(String address)
		{
			this.address = address;
		}
		//This method is used to display a menu for customer management
		public void customerMenu()
		{
			String choice;
			int cho;
			do {
				System.out.println("Enter the operation.");
				System.out.println("1. Add customer details.");
				System.out.println("2. Modify customer details.");
				System.out.println("3. Delete customer details.");
				System.out.println("4. List all the customer.");
				System.out.println("5. Search a customer.");
				System.out.println("6. Exit.");
				while (true) {
				System.out.println("Your choice: ");
				Scanner sc = new Scanner(System.in);
				choice = sc.nextLine();
				try {
					cho = Integer.parseInt(choice);
					break;
				}catch (NumberFormatException e) 
				{
					System.err.println("Input must be a valid integer.");
				}
				}
				switch(cho)
				{
				case 1: 
					addDetails();/*add customer details*/
					break;
				case 2: 
					modifyDetails();
					break;
				case 3:
					deleteDetails(customerFile);
					break;
				case 4:
					try {
						listFile(customerFile);
					}catch(IOException e) {
						System.err.println("Error reading file: " + e.getMessage());
					}
					break;
				case 5:
					try {
						searchFile(customerFile);
					}catch(IOException e) {
						System.err.println("Error reading file: " + e.getMessage());
					}
					break;
				case 6:
					System.out.println("Exit to main menu.");
					break;
				default:
					System.out.println("Invalid input, please try again.");
				}
				} while(cho != 6);
		}
		
		
		
		

		
		public void addDetails() 
		{
		    boolean cont = true;
		    Scanner sc = new Scanner(System.in);
		    
		    System.out.println("Please enter customer name: ");
		    name = sc.nextLine();
		    System.out.println("Please enter customer email: ");
		    email=sc.nextLine();
		    email = validateEmail(email);
		    System.out.println("Please enter customer contact number: ");
		    contactNum=sc.nextLine();
		    contactNum = validateContactNum(contactNum);
		    System.out.println("Please enter customer address: ");
		    address=sc.nextLine();
		    id = RandomIdGenerator();
		    System.out.println("Details added successfully.");
		    System.out.println("The Id for customer " + name + " is " + id);

		    while (cont)
		    {
		        try {
		            FileWriter filewriter = new FileWriter(customerFile, true);//Create to write data in file
		            BufferedWriter bufferwriter= new BufferedWriter(filewriter);
		            if(!customerFile.exists())
		            	customerFile.createNewFile();
		            bufferwriter.append("Customer ID:" );
		            bufferwriter.append(String.format("%-10s", id));
		            bufferwriter.append(", Customer name:" );
		            bufferwriter.append(String.format("%-20s", name));
		            bufferwriter.append(", Customer email:" );
		            bufferwriter.append(String.format("%-20s", email));
		            bufferwriter.append(", Customer contact number:" );
		            bufferwriter.append(String.format("%-10s", contactNum));
		            bufferwriter.append(", Customer address:" );
		            bufferwriter.append(String.format("%-30s", address));
		            bufferwriter.append("\r\n");
		            bufferwriter.close();
		            filewriter.close();
		            cont = false;//to indicate data has been successfully written into file
		            
		        } 
		        catch (IOException e) 
		        {
		            System.out.println("An error occured.");
		            e.printStackTrace();
		        }
		    }
		}
		
		public void modifyDetails()
		{
			System.out.println("Please enter customer id to edit customer details:");
			Scanner input = new Scanner(System.in);
			String searchId = input.next();
			boolean found = false;
			String line;
			File tempFile = new File ("temp.txt");
			try {//try to run the following code, and catch any exceptions that might be thrown
				BufferedReader bufferedreader = new BufferedReader(new FileReader(customerFile));
				PrintWriter writer = new PrintWriter(new FileWriter(tempFile));
				line = bufferedreader.readLine();
		        while (line != null) 
		        {
		        	String findId = line.split(", ")[0].split(":")[1];
		        	String ID = findId.trim();
		        	if (ID.equals(searchId))
		        	{
		        		found = true;
		        		System.out.println("The customer id is found.");
		        		Scanner sc = new Scanner(System.in);
		            	System.out.println("Edit Customer details:");
		            	System.out.println("1. Customer name.");
		    	        System.out.println("2. Customer email.");
		    	        System.out.println("3. Customer contact number.");
		    	        System.out.println("4. Customer address.");
		    	        System.out.println("Your choice:");
		    	        String choice = sc.nextLine();
		    	        int cho =Integer.parseInt(choice);
		    	        String[] tokens = line.split(", ");
	                    String newLine = tokens[0];
		    	        switch (cho)
		    	        {
		    	        case 1:
		    	        	System.out.println("Please enter new customer name: ");
				        	String newName = sc.nextLine();
				        	newLine += ", Customer name:" + String.format("%-20s", newName);
				        	newLine += ", " + tokens[2];
				        	newLine += ", " + tokens[3];
				        	newLine += ", " + tokens[4];
				        	break;

		    	        case 2:
		    	        	newLine += ", " + tokens[1];
		    	        	System.out.println("Please enter new customer email: ");
		    	        	String newEmail = sc.nextLine();
		    	        	newEmail = validateEmail(newEmail);
                            newLine += ", Customer email:" + String.format("%-20s", newEmail);
                            newLine += ", " + tokens[3];
                            newLine += ", " + tokens[4];
                            break;
                            
		    	        case 3:
		    	        	newLine += ", " + tokens[1];
		    	        	newLine += ", " + tokens[2];
		    	        	System.out.println("Please enter new customer contact number: ");
		    	        	String newContactNumber = sc.nextLine();
		    	        	newContactNumber = validateContactNum(newContactNumber);
                            newLine += ", Customer contact number:" + String.format("%-10s", newContactNumber);
                            newLine += ", " + tokens[4];
                            break;
                            
		    	        case 4:
		    	        	newLine += ", " + tokens[1];
		    	        	newLine += ", " + tokens[2];
		    	        	newLine += ", " + tokens[3];
		    	        	System.out.print("Enter the new customer address: ");
                            String newAddress = sc.nextLine();
                            newLine += ", Customer address:" + String.format("%-30s", newAddress);
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
		        if (!found)//If id not found, an appropriate message is displayed.
		        {
		        	System.out.println("ID not found.");
		        }
		        try {
		            if (!customerFile.delete()) {//If the customer file is not deleted ,this message will be displayed.
		                throw new Exception("Could not delete input file");
		            }
		            if (!tempFile.renameTo(customerFile)) {//If temporary file not renamed ,this message will be displayed.
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
