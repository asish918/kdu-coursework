package asish.kdu.q1;

/**
 * The Student ID is pretty much useless here but since it was
 * mentioned in the boiler plate code, I used it.
 */

public class MissingGradeException extends Exception {
    private final int studentId;

    public MissingGradeException(int studentId) {
        super("One or more Grades are missing");
        this.studentId = studentId;
    }

    public int getStudentId() {
        return studentId;
    }
}
