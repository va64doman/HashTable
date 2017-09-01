/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hashtable;

/**
 *
 * @author Van Do
 */

// This class holds the hash table's data and method of using this table
public class HashTable 
{
    // Hash table's size
    private final int tSize;
    // Size of elements in table
    private int size;
    // Array of buckets in table
    private final StudentEntry[] table;
    // Parameterised constructor to set table size
    public HashTable(int tabSize)
    {
        size = 0;
        tSize = tabSize;
        table = new StudentEntry[tSize];
        for(int count = 0; count < tSize; count++)
        {
            table[count] = null;
        }
    }
    // Method to get number of key-value pairs
    public int getSize()
    {
        return size;
    }
    // Method to check if the table is empty
    public boolean isEmpty()
    {
        return size == 0;
    }
    // Method to make the table empty
    public void makeEmpty()
    {
        // Set size to 0
        size = 0;
        // Iterate the loop and make all elements null
        for(int count = 0; count < tSize; count++)
        {
            table[count] = null;
        }
    }
    // Function to get student's details
    public StudentEntry get(String key)
    {
        // Check if the table is not empty
        if(!isEmpty())
        {
            // Set this value for checking if the bucket of the table is not null
            int hash = firstHash(key) % tSize;
            // Check if bucket in the hash table is empty
            if(table[hash] == null)
            {
                return null;
            }
            // If bucket in the hash table is not empty
            else
            {
                // Set entry as array of buckets from this index by hash
                StudentEntry entry = table[hash];
                // Check through every chains in this bucket, the key for getting
                // this bucket is name
                while(entry != null && !entry.getName().equals(key))
                {
                    // Set entry as the next linked entry of this bucket
                    entry = entry.getNext();
                }
                // If get is not found, return as null
                if(entry == null)
                {
                    return null;
                }
                // If get is found, return element of table from hash index
                else
                {
                    return entry;
                }

            }
        }
        // If table is empty, display this message
        else
        {
            return null;
        }
    }
    // Function for giving a hash value for a given string
    private int firstHash(String key)
    {
        // Bucketing hash table and set hashVal
        int hashVal = key.hashCode();
        // hashVal will be the remainder of hashVal / tSize
        hashVal %= tSize;
        // If hashVal is less than 0, increase hashVal by the table size
        if(hashVal < 0)
        {
            hashVal += tSize;
        }
        // Return hashVal
        return hashVal;
    }
    // Function for inserting a key value pair
    public void insert(int id, String name, int age, String subject)
    {
        // Get hash value for name
        int hash = firstHash(name);
        // Set pointer as new student's entry
        StudentEntry pointer = new StudentEntry(id, name, age, subject);
        // Set start at the index of hash from the table
        StudentEntry start = table[hash];
        // Check if this bucket does not have any elements
        if(table[hash] == null)
        {
            // Add pointer to this bucket
            table[hash] = pointer;
        }
        // If bucket is not empty
        else
        {
            // Set entry as start
            StudentEntry entry = start;
            // Check if the next node is not null
            while(entry.getNext() != null)
            {
                // Set entry as its next node
                entry = entry.getNext();
            }
            // If there are no more next pointer, set entry's next node as pointer
            entry.setNext(pointer);
            // Set pointer's previous node as entry
            pointer.setPrevious(entry);
        }
        // Increment size by 1
        size++;
    }
    // Function to remove key
    public void remove(String key)
    {
        // Check if the table is empty
        if(!isEmpty())
        {
            // Get hash value from the user's search
            int hash = firstHash(key);
            // Set start as the array of the bucket, where index is equals to hash
            StudentEntry start = table[hash];
            // Set end as start if the key is not found at start
            StudentEntry end = start;
            // Set search as using the get method from key
            StudentEntry search = get(key);
            // To avoid null exception, state that start is not empty
            if(start != null)
            {
                // Compare both objects, start and search, if match
                if(start == search)
                {
                    // Decrement size by 1
                    size--;
                    // Check if next student after start is empty
                    if(start.getNext() == null)
                    {
                        // Make this bucket null
                        table[hash] = null;
                    }
                    // If there are more than one student in the bucket
                    else
                    {
                        // Set start as the next student after start
                        start = start.getNext();
                        // Make new start's previous element null
                        start.setPrevious(null);
                        // Set this bucket as new start student of the list
                        table[hash] = start;
                    }
                }
                // If start and search are not match, check the rest of the list from this bucket
                else
                {
                    // Continue this loop when the end's next student is not null
                    // and next student's name is not equals to key
                    while(end.getNext() != null && !end.getNext().getName().equals(key))
                    {
                        // Set end as end's next student
                        end = end.getNext();
                    }
                    // If end's next student is null, show that element is not found
                    if(end.getNext() == null)
                    {
                        System.out.println("Element is not found.");
                    }
                    // If end's next student is found, do this function
                    else
                    {
                        // Decrement size by 1
                        size--;
                        // If end's next two student is null, set next student's next element null
                        if(end.getNext().getNext() == null)
                        {
                            end.setNext(null);
                        }
                        // If there is/are next element after end's next student
                        else
                        {
                            // Set previous as end for the next two students after end
                            end.getNext().getNext().setPrevious(end);
                            // Set end's next element as two students after end
                            end.setNext(end.getNext().getNext());
                            // Set this bucket as the start
                            table[hash] = start;
                        }
                    }
                }
            }
            else
            {
                // If start is null, show that element does not existed
                System.out.println("Element is not existed.");
            }
        }
        else
        {
            // If table is empty, show that table is empty
            System.out.println("The table is empty.");
        }
    }
    // Function to print hash table by ID
    public void printHashTableByID()
    {
        System.out.println();
        // Go through each buckets in the table
        for(int count = 0; count < table.length; count++)
        {
            // Increase count by 1 to avoid confusion
            System.out.print("Bucket " + (count + 1) + ": ");
            // Initialise start as index of the table
            StudentEntry start = table[count];
            // Keep looping until start is null
            while(start != null)
            {
                // Display ids in table
                System.out.print(start.getID() + " ");
                // Set start as start's next student
                start = start.getNext();
            }
            System.out.println();
        }
    }
    // Function to print hash table by name
    public void printHashTableByName()
    {
        System.out.println();
        // Go through each buckets in the table
        for(int count = 0; count < table.length; count++)
        {
            // Increase count by 1 to avoid confusion
            System.out.print("Bucket " + (count + 1) + ": ");
            // Initialise start as index of the table
            StudentEntry start = table[count];
            // Keep looping until start is null
            while(start != null)
            {
                // Display ids in table
                System.out.print(start.getName() + " ");
                // Set start as start's next student
                start = start.getNext();
            }
            System.out.println();
        }
    }
    // Function to check if key is existing
    public boolean checkExistKey(int key)
    {
        // Using foreach loop to check all buckets and elements in table
        // because it does not require numbers
        for (StudentEntry start : table) 
        {
            // Continue first-test loop until start is null
            while(start != null)
            {
                // If start's id is equal to key, return true
                if(start.getID() == key)
                {
                    return true;
                }
                // Set start as next student
                start = start.getNext();
            }
        }
        // If not found, return false
        return false;
    }
}
