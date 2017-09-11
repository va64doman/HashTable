/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hashtable;

import java.util.*;

/**
 *
 * @author Van Do
 */
public class Modular 
{
    // All methods must include scanner to allow user to input data
    private Scanner scan;
    // Allow to access HashTable methods
    private HashTable hash;
    // Initialise choice to enable to choose to keep running application
    private int choice;
    // This is for handling integer input error
    private int input;
    // Display option to edit linked list
    public void selectOption()
    {
        // Initialise new Scanner and HashTable object
        // Delimit new line character to set string as the whole line input
        scan = new Scanner(System.in).useDelimiter("[\r\n]");
        int size;
        // Continue this while size is less than 1
        do
        {
            System.out.println("Enter the number of buckets in the hash table.");
            size = handleInt();
            if(size < 1)
            {
                System.out.println("Table size cannot be 0 or less.");
            }
        }
        while(size < 1);
        hash = new HashTable(size);
        // Do this function while the user wants to keep using this app
        do
        {
            // Print out all options
            System.out.println(displayOption());
            // User choosing an option
            int option = handleInt();
            // Using switch case to decide multiple options
            switch(option)
            {
                // If 1, insert student in hash table
                case 1:
                    // Add new id
                    int id;
                    // Continue until key does not exist
                    do
                    {
                        System.out.print("Enter Student ID: ");
                        id = handleInt();
                        // Check if id is already exists in table
                        if(hash.checkExistKey(id))
                        {
                            System.out.println("Student's key has already exists.");
                        }
                    } 
                    while(hash.checkExistKey(id));
                    // Add name
                    String name;
                    // Continue this loop until name does not existed
                    do
                    {
                        System.out.print("Enter Student's Name: ");
                        name = scan.next();
                        if(hash.get(name) != null)
                        {
                            // Display where the name existed
                            System.out.println("This student's name is already exists.");
                            System.out.println("The value is " + hash.get(name).getID());
                        }
                    }
                    while(hash.get(name) != null);
                    System.out.println();
                    // Add age
                    int age;
                    // Continue while age is less than 4
                    do
                    {
                        System.out.print("Enter Student's Age: ");
                        age = handleInt();
                        if(age < 4)
                        {
                            System.out.println("Too young.");
                        }
                    }
                    while(age < 4);
                    // Add new subject
                    System.out.print("Enter Student's Subject: ");
                    String subject = scan.next();
                    // Insert student into hash table to the bucket
                    hash.insert(id, name, age, subject);
                    // Display table
                    printTable();
                    break;
                // If 2, delete the key-value pairs
                case 2:
                    // Enter the name of the student to delete
                    System.out.print("Enter the name you wanted to delete: ");
                    name = scan.next();
                    // Find the buckets and go through the linked list
                    hash.remove(name);
                    // Display table
                    printTable();
                    break;
                // If 3, display the key-value pair details
                case 3:
                    // Enter student's name you wanted to display
                    System.out.print("Enter the name you wanted to get and display its details: ");
                    name = scan.next();
                    // Get entry from hash table
                    StudentEntry entry = hash.get(name);
                    // Check if hash table is not empty
                    if(!hash.isEmpty())
                    {
                        // Check if entry is null, show that name does not existed
                        if(entry == null)
                        {
                            System.out.println(name + " is not found in table.");
                        }
                        // Check if entry is existed, display its details
                        else
                        {
                            System.out.println("The value is " + entry.getID());
                            entry.printDetails();
                        }
                    }
                    // If table is empty, display this message
                    else
                    {
                        System.out.println("The table is empty.");
                    }
                    break;
                // If 4, clear table and display empty table
                case 4:
                    if(!hash.isEmpty())
                    {
                        System.out.println("The table was filled.");
                        hash.makeEmpty();
                        System.out.println("And now it was empty.");
                    }
                    
                    else
                    {
                        System.out.println("The table is already empty.");
                    }
                    
                    printTable();
                    break;
                // If 5, check how many elements in a hash table
                case 5:
                    System.out.println("There are " + hash.getSize() + " students in a hash table.");
                    break;
                // If 6, display tables
                case 6:
                    printTable();
                    System.out.println();
                    printTableByName();
                    break;
                // If 7, change age and subject only because name is key.
                // Name should not be change that will affect the get method from hash table
                case 7:
                    // Enter student's name you wanted to display
                    System.out.print("Enter the name you wanted to get and display its details: ");
                    name = scan.next();
                    // Get entry from hash table
                    entry = hash.get(name);
                    // Check if hash table is not empty
                    if(!hash.isEmpty())
                    {
                        // Check if entry is null, show that name does not existed
                        if(entry == null)
                        {
                            System.out.println(name + " is not found in table.");
                        }
                        // Check if entry is existed, display its details
                        else
                        {
                            entry.printDetails();
                            do
                            {
                                System.out.print("Enter New Student's Age: ");
                                age = handleInt();
                                if(age < 4)
                                {
                                    System.out.println("Too young.");
                                }
                            }
                            while(age < 4);
                            // Add new subject
                            System.out.print("Enter New Student's Subject: ");
                            subject = scan.next();
                            // Change both age and subject and display new details
                            entry.setAge(age);
                            entry.setSubject(subject);
                            System.out.println("This student's new details.");
                            entry.printDetails();
                        }
                    }
                    // If table is empty, display this message
                    else
                    {
                        System.out.println("The table is empty.");
                    }
                    break;
                // If none of the above, display this message
                default:
                    System.out.println("Invalid option.");
                    break;
            }
            // Display this message if wanted to continue this app
            System.out.println("Do you want to continue? Yes (1) or No (Any number)");
            choice = handleInt();
        }
        while(choice == 1);
    }
    // Display all options
    private String displayOption()
    {
        // Build up the option easily without typing string in lengthy line
        StringBuilder option = new StringBuilder();
        // Append all string into one
        option.append("Select options.").append("\n");
        option.append("1. Insert Student").append("\n");
        option.append("2. Remove Student").append("\n");
        option.append("3. Get Student").append("\n");
        option.append("4. Clear Table").append("\n");
        option.append("5. Get Size of Elements in Hash Table").append("\n");
        option.append("6. Display Table").append("\n");
        option.append("7. Change Age and Subject");
        // Display options
        return option.toString();
    }
    // Display table by id
    private void printTable()
    {
        System.out.println("Displaying table");
        hash.printHashTableByID();
    }
    // Display table by name
    private void printTableByName()
    {
        System.out.println("Displaying table by name");
        hash.printHashTableByName();
    }
    // Handle integer inputs
    private int handleInt()
    {
        // Assuming this continue in a loop until the user has entered the integer
        boolean loop = true;
        // Continue this loop until the user has entered the input correctly
        while(loop)
        {
            // Try and catch error if the user has not entered the integer
            try
            {
                input = scan.nextInt();
                System.out.println();
                loop = false;
            }
            catch(InputMismatchException e)
            {
                System.out.println("Try again. Wrong input.");
                scan.nextLine();
            }
        }
        return input;
    }
}
