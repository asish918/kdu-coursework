package asish.kdu.q1;

import java.util.HashMap;

public class StudentUtil {
    public static double[] calculateGPA(int[] studentIdList, char[][]
            studentsGrades) {

        HashMap<Character, Integer> gradeMap = new HashMap<>();

        gradeMap.put('A', 4);
        gradeMap.put('B', 3);
        gradeMap.put('C', 2);

        int studentListSize = studentIdList.length;

        double[] gpaResult = new double[studentListSize];

        for(int i = 0; i < studentListSize; i++) {
            double studentGPA = 0.0;
            int totalSubjects = 0;
            for (int j = 0; j < studentsGrades[i].length; j++) {
                studentGPA += gradeMap.get(studentsGrades[i][j]);
                totalSubjects++;
            }

            if(totalSubjects != 0)
                gpaResult[i] = studentGPA / totalSubjects;
            else
                break;
        }

        return gpaResult;
    }

    public static int[] getStudentsByGPA(double lower, double higher, int[]
            studentIdList, char[][] studentsGrades) {
        if(lower < 0 || higher < 0 || lower > 4 || higher > 4 || higher < lower)
            return new int[0];

        double[] gpaResult = calculateGPA(studentIdList, studentsGrades);

        int resultSize = 0;

        for (double v : gpaResult) {
            if (v >= lower && v <= higher)
                resultSize++;
        }

        int[] resultList = new int[resultSize];
        int resultIndex = 0;

        for(int i = 0; i < gpaResult.length; i++) {
            if(gpaResult[i] >= lower && gpaResult[i] <= higher) {
                resultList[resultIndex] = studentIdList[i];
                resultIndex++;
            }
        }

        return resultList;
    }

    public static void main(String[] args) {
        int[] studentList = new int[]{1001, 1002};

        char[][] studentGrades = new char[][] {{'A', 'A', 'A', 'B'}, {'A', 'B', 'B'}};

        double[] resultGPA = calculateGPA(studentList, studentGrades);

        int[] resultStudents = getStudentsByGPA(3.2, 3.5, studentList, studentGrades);

        Q1Utils.printStudentIds(resultStudents);
        Q1Utils.printGPA(resultGPA);
    }
}