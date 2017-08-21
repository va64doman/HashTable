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
    private int tSize;
    // Size of elements in table
    private int size;
    // Array of buckets in table
    private StudentEntry[] table;
    // Prime number
    private int primeSize;
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
        primeSize = getPrime();
    }
    // Method to get prime number less than table size to use for double hashing
    public int getPrime()
    {
        // Iterate loop until count is 0
        for(int count = tSize - 1; count >= 1; count--)
        {
            // Factor, assuming fact is 0 when there are two factors
            // If fact is greater than 0, it has more than two factors
            int fact = 0;
            // Keep adding factor until
            for(int factor = 2; factor <= (int)Math.sqrt(count); factor++)
            {
                // If remainder is 0, increment fact by 1
                if(count % factor == 0)
                {
                    fact++;
                }
            }
            // If there are only two factors, return this prime number(count)
            if(fact == 0)
            {
                return count;
            }
        }
        // Otherwise return 3
        return 3;
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
        int hash = firstHash(name);
        StudentEntry pointer = new StudentEntry(id, name, age, subject);
        StudentEntry start = table[hash];
        
        if(table[hash] == null)
        {
            table[hash] = pointer;
        }
        
        else
        {
            pointer.setNext(start);
            start.setPrevious(pointer);
            table[hash] = pointer;
        }
        size++;
    }
    // Function to remove key
    public void remove(String key)
    {
        int hash = firstHash(key);
        StudentEntry start = table[hash];
        StudentEntry end = start;
        if(start.getName().equals(key))
        {
            size--;
            if(start.getNext() != null)
            {
                table[hash] = null;
            }
            
            else
            {
                start = start.getNext();
                start.setPrevious(null);
                table[hash] = start;
            }
        }
        else
        {            
            while(end.getNext() != null && !end.getNext().getName().equals(key))
            {
                end = end.getNext();
            }
            
            if(end.getNext() == null)
            {
                System.out.println("Element is not found.");
            }
            
            else
            {
                size--;
                if(end.getNext().getNext() == null)
                {
                    end.setNext(null);
                }
                else
                {
                    end.getNext().getNext().setPrevious(end);
                    end.setNext(end.getNext().getNext());
                    
                    table[hash] = start;
                }
            }
        } 
    }
    
    public void printHashTable()
    {
        System.out.println();
        for(int count = 0; count < table.length; count++)
        {
            System.out.print("Bucket " + count + ": ");
            StudentEntry start = table[count];
            while(start != null)
            {
                System.out.print(start.getID() + " ");
                start = start.getNext();
            }
            System.out.println();
        }
    }
}
