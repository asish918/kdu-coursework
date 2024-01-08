package asish.kdu;

//Create a Student class with attributes like id, name, age, and grade.
//Implement appropriate getters and setters.
//Use primitive variables like int, String, etc.,
// for attributes in the Student class.
// Demonstrate type casting (if applicable) while working with different types of variables.

public class Student {
    private Integer id, age = null;
    private String name = null;
    private Character grade = null;
    Student(Integer id, Integer age, String name, Character grade) {
        this.id = id;
        this.age = age;
        this.name = name;
        this.grade = grade;
    }

    // Getters
    public Integer getId() {
        return id;
    }
    public Integer getAge() {
        return age;
    }
    public String getName() {
        return name;
    }
    public Character getGrade() {
        return grade;
    }

    // Setters
    public void setName(String newName) {
        this.name = newName;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public void setGrade(Character grade) {
        this.grade = grade;
    }
    public void setAge(Integer age) {
        this.age = age;
    }
}
