package asish.kdu.q1;

import java.util.Arrays;

import asish.kdu.logging.CustomLogger;
import asish.kdu.logging.CustomLogger.LoggerType;

public class Main {
    public static void main(String[] args) {
        int[] ids = { 1001, 1002 };
        char[][] grades = { { 'A', 'B' }, { 'A', ' ' } };
        try {
            int[] ans = StudentUtil.getStudentsByGPA(2.5, 3.5, ids, grades);
            CustomLogger.printLogger("Student output - ", LoggerType.INFO);
            CustomLogger.printLogger(Arrays.toString(ans), LoggerType.INFO);
        } catch (Exception e) {
            CustomLogger.printLogger("InvalidDataException: " + e.getMessage(), LoggerType.ERROR);
            CustomLogger.printLogger("Underlying cause: " + e.getCause().getMessage(), LoggerType.ERROR);
        }
    }
}