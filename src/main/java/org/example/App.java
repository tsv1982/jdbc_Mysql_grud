package org.example;

import java.util.Map;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        //get all
        Map<Integer, Student> studentMap = StudentController.getAll();
        for (Map.Entry<Integer, Student> entry : studentMap.entrySet()) {
            System.out.println("Key : " + entry.getKey() + ", Value : " + entry.getValue().getFirst_Name());
            System.out.println(entry.getValue().getId());
            System.out.println(entry.getValue().getLast_Name());
        }

        //add
//        Student student = new Student();
//        student.setId(3);
//        student.setFirst_Name("Petro");
//        student.setLast_Name("Poroshenko");
//        student.setAddress("Poland");
//        student.setGroup_ID(12);
//        StudentController.addStudent(student);
        //update
//        StudentController.update(7, "Petrorrrr", "Porox", "UA", 12);
        //delete
//        StudentController.delete(7);

        //search id
       // Student student = (Student) StudentController.search(5).get(5);
     //   System.out.println(student.getFirst_Name());
    }
}
