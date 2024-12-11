package asish.kdu;

import java.util.ArrayList;
import java.util.Objects;

public class App
{
    public static boolean propertyNullCheck(Student student) {
        return student.getId() != null || student.getGrade() != null || student.getName() != null || student.getAge() != null;
    }
    public static void setNonNullProps(Student ogStudent, Student student) {
        if(student.getName() != null) {
            ogStudent.setName(student.getName());
        }

        if(student.getAge() != null) {
            ogStudent.setAge(student.getAge());
        }

        if(student.getGrade() != null) {
            ogStudent.setGrade(student.getGrade());
        }
    }

    public static void linearSearchUpdate(ArrayList<Student> studentDB, Student newStudent) {
        for (Student student : studentDB) {
            if (Objects.equals(student.getId(), newStudent.getId())) {
                App.setNonNullProps(student, newStudent);
                System.out.println("Student Entry updated!");
                CustomLogger.printLogger("DB Updated");
                return;
            }
        }
    }
}
