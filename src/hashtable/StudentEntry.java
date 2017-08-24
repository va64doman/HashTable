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

// The student has details to contain as an existence to the object
public class StudentEntry 
{
    // Student's ID
    // The id is going to be the hash table value
    private final int id;
    // Stundent's name
    // The name is going to be the hash table key
    private String name;
    // Student's age
    private int age;
    // Student's subject of choice
    private String subject;
    // Next student in a hash table after this student
    private StudentEntry next;
    // Previous student in a hash table before this student
    private StudentEntry previous;
    // Parameterised constructor to initialise object
    public StudentEntry(int sId, String sName, int sAge, String sSubject)
    {
        id = sId;
        name = sName;
        age = sAge;
        subject = sSubject;
        previous = null;
        next = null;
    }
    // Getters
    // Get student's id
    public int getID()
    {
        return id;
    }
    // Get student's name
    public String getName()
    {
        return name;
    }
    // Get student's age
    public int getAge()
    {
        return age;
    }
    // Get student's subject
    public String getSubject()
    {
        return subject;
    }
    // Get previous chained student after this student
    public StudentEntry getPrevious()
    {
        return previous;
    }
    // Get next chained student after this student
    public StudentEntry getNext()
    {
        return next;
    }
    // Setters
    // Set new name
    public void setName(String sName)
    {
        name = sName;
    }
    // Set new age
    public void setAge(int sAge)
    {
        age = sAge;
    }
    // Set new subject
    public void setSubject(String sSubject)
    {
        subject = sSubject;
    }
    // Set new key-value pairs after this student
    public void setNext(StudentEntry student)
    {
        next = student;
    }
    // Set new key-value pairs before this student
    public void setPrevious(StudentEntry student)
    {
        previous = student;
    }
    // Print details
    public void printDetails()
    {
        System.out.println("Student's ID: " + id);
        System.out.println("Student's name: " + name);
        System.out.println("Student's age: " + age);
        System.out.println("Student's subject: " + subject);
    }
}
