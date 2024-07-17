UX Gallery Management System

Overview

The UX Gallery Management System is a console-based application designed to manage a gallery's customers, artists, and artwork inventory. The system allows users to add, delete, modify, search, and list information about customers, artists, and artworks. It also manages inventory, tracking purchase and selling prices. The application employs advanced object-oriented programming techniques, file handling, and exception handling to ensure robust and efficient management.

Features

Customer Management: Add, delete, modify, search, and list customers and their art purchases.
Artist Management: Add, delete, modify, search, and list artists and their works.
Artwork Management: Add, delete, modify, search, and list artworks with details such as purchase price and selling price.
File Handling: Save and load all information to and from text files to maintain data persistence.
Exception Handling: Ensure smooth operation and error handling throughout the application.
Class Diagram

The project includes a complete class diagram using UML notations, which illustrates all classes, their attributes, operations, relationships among classes, along with navigability and multiplicities.

Project Structure

src/: Contains the source code of the application.
Customer.java: Class representing customers.
Artist.java: Class representing artists.
Artwork.java: Class representing artworks.
Inventory.java: Class managing the gallery inventory.
UXGalleryManagementSystem.java: Main class containing the application logic.
data/: Contains sample input data files.
customers.txt: Sample customer data.
artists.txt: Sample artist data.
artworks.txt: Sample artwork data.
docs/: Contains documentation and diagrams.
ClassDiagram.png: UML class diagram of the project.
screenshots/: Contains screenshots of the application output.
sample_output_1.png
sample_output_2.png
Getting Started

Prerequisites
Java Development Kit (JDK) 8 or higher.
A text editor or an Integrated Development Environment (IDE) like IntelliJ IDEA or Eclipse.
Installation
Clone the repository:

sh
Copy code
git clone https://github.com/samanthayeep/UXGalleryManagementSystem.git
cd UXGalleryManagementSystem
Open the project in your IDE:

Import the project as a Maven project if your IDE supports it.
Compile and run the application:

Navigate to the src directory and compile the Java files.
Run the UXGalleryManagementSystem class to start the application.
Usage
Start the application:

Run the UXGalleryManagementSystem class.
The application will load initial data from the text files located in the data directory.
Interact with the application:

Follow the on-screen prompts to manage customers, artists, and artworks.
You can add, delete, modify, search, and list information as required.
Exit the application:

Choose the exit option to save all changes back to the text files.
Contributions

Contributions are welcome! Please fork the repository and create a pull request with your changes. Ensure that your code adheres to the existing style and includes appropriate comments and documentation.

License

This project is licensed under the MIT License - see the LICENSE file for details.

Acknowledgements

The project was developed as part of the UECS1044 Object-Oriented Application Development course.
Special thanks to all group members for their contributions and collaboration.
