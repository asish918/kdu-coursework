package asish.kdu;

//Implement a StudentRepository class that uses an array to store multiple Student objects.
//Implement methods to add, retrieve, and update students in the repository.
//Demonstrate method overloading for retrieving students based on different parameters (e.g., ID, name).

import java.util.ArrayList;
import java.util.Objects;

public class StudentRepository {
    private static ArrayList<Student> studentDB = new ArrayList();

    public void addStudent(Student student) {
        try {
            studentDB.add(student);
            CustomLogger.printLogger("Database Entry created");
            System.out.println("Student entry created");
        } catch (Exception e) {
            CustomLogger.printLogger(e.toString());
            System.out.println("Some error occurred while creating entry in database");
        }
    }

    public void updateStudent(Student newStudent) {
        if(App.propertyNullCheck(newStudent)) {
            App.linearSearchUpdate(studentDB, newStudent);
        }
        else {
            App.linearSearchUpdate(studentDB, newStudent);
        }

        System.out.println("Student doesn't exist!");
    }

    public ArrayList<Student> retrieve() {
        return studentDB;
    }

    public Student retrieve(Integer id) {
        for (Student student : studentDB) {
            if (Objects.equals(student.getId(), id))
                return student;
        }

        return new Student(null, null, null, null);
    }

    public ArrayList<Student> retrieve(String name) {
        ArrayList<Student> searchResult = new ArrayList<>();

        for (Student student : studentDB) {
            if (Objects.equals(student.getName(), name))
                searchResult.add(student);
        }

        return  searchResult;
    }
}
