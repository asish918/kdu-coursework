package asish.kdu;

import java.util.ArrayList;

public class Utils {
    public static void viewStudents(ArrayList<Student> list) {
        System.out.println("\nAll Students - ");
        for(Student stu : list) {
            System.out.println("ID - " + stu.getId());
            System.out.println("Name - " + stu.getName());
            System.out.println("Age - " + stu.getAge());
            System.out.println("Grade - " + stu.getGrade());
            System.out.println("--------------------");
        }
    }

    public static void viewStudent(Student stu) {
        System.out.println("\nStudent Details - ");
        System.out.println("ID - " + stu.getId());
        System.out.println("Name - " + stu.getName());
        System.out.println("Age - " + stu.getAge());
        System.out.println("Grade - " + stu.getGrade());
        System.out.println("--------------------");
    }
}
