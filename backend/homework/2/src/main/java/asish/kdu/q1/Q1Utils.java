package asish.kdu.q1;

import asish.kdu.logging.CustomLogger;

public class Q1Utils {
    private Q1Utils() {

    }

    public static void printStudentIds(int[] data) {
        CustomLogger.printLogger("Student IDs - ");

        for(int student : data)
            CustomLogger.printLogger(Integer.toString(student));
    }

    public static void printGPA(double[] data) {
        CustomLogger.printLogger("GPAs - ");

        for(double gpa : data)
            CustomLogger.printLogger(Double.toString(gpa));
    }
}
