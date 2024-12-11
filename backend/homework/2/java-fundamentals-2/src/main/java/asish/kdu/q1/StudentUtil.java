package asish.kdu;

import java.util.HashMap;

public class StudentUtil {
    public static double[] calculateGPA(int[] studentIdList, char[][]
            studentsGrades) {

        HashMap<Character, Integer> gradeMap = new HashMap<>() {{
            put('A', 4);
            put('B', 3);
            put('C', 2);
        }};

        int studentListSize = studentIdList.length;

        double[] gpaResult = new double[studentListSize];

        for(int i = 0; i < studentListSize; i++) {
            double studentGPA = 0.0;
            int numberGPA = 0;
            for (int j = 0; j < studentsGrades[i].length; j++) {
                studentGPA += gradeMap.get(studentsGrades[i][j]);
                numberGPA++;
            }

            gpaResult[i] = studentGPA / numberGPA;
        }

        return gpaResult;
    }

    public static int[] getStudentsByGPA(double lower, double higher, int[]
            studentIdList, char[][] studentsGrades) {
        if(lower < 0 || higher < 0 || lower > 4 || higher > 4 || higher < lower)
            return null;

        double[] gpaResult = calculateGPA(studentIdList, studentsGrades);

        int resultSize = 0;

        for(int i = 0; i < gpaResult.length; i++) {
            if(gpaResult[i] >= lower && gpaResult[i] <= higher)
                resultSize++;
        }

        int[] resultList = new int[resultSize];
        int k = 0;

        for(int i = 0; i < gpaResult.length; i++) {
            if(gpaResult[i] >= lower && gpaResult[i] <= higher) {
                resultList[k] = studentIdList[i];
                k++;
            }
        }

        return resultList;
    }

    public static void main(String[] args) {
        int[] studentList = new int[]{1001, 1002};

        char[][] studentGrades = new char[][] {{'A', 'A', 'A', 'B'}, {'A', 'B', 'B'}};

        double[] resultGPA = calculateGPA(studentList, studentGrades);

        int[] result = getStudentsByGPA(3.2, 3.5, studentList, studentGrades);

        for (int r:
             result) {
            System.out.println(r);
        }
    }
}