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
        System.out.println("Enter the number of buckets in the hash table.");
        int size = handleInt();
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
                
            }
            // Display this message if wanted to continue this app
            System.out.println("Do you want to continue? Yes (1) or No (Any number)");
            choice = handleInt();
        }
        while(choice == 1);
    }
    // Display all options
    public String displayOption()
    {
        // Build up the option easily without typing string in lengthy line
        StringBuilder option = new StringBuilder();
        // Append all string into one
        option.append("Select options.").append("\n");
        
        // Display options
        return option.toString();
    }
    // Handle integer inputs
    public int handleInt()
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
