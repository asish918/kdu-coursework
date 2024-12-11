package asish.kdu;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        StudentRepository accessRepo = new StudentRepository();

        Scanner sc = new Scanner(System.in);
        int choice = 0;

        do {
            System.out.println("\n----------------------\n|   STUDENT PORTAL   |\n|--------------------|");
            System.out.println("|1. To Create Student|");
            System.out.println("|2. To Update Student|");
            System.out.println("|3. To Retrieve Data |");
            System.out.println("|4. To Exit          |");
            System.out.println("----------------------");
            System.out.println("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Enter ID - ");
                    Integer id = sc.nextInt();
                    System.out.println("Enter Name - ");
                    String name = sc.next();
                    System.out.println("Enter Age - ");
                    Integer age = sc.nextInt();
                    System.out.println("Enter Grade - ");
                    Character grade = sc.next().charAt(0);

                    accessRepo.addStudent(new Student(
                            id,
                            age,
                            name,
                            grade));
                    break;
                case 2:
                    System.out.println("Enter ID - ");
                    id = sc.nextInt();
                    System.out.println("Enter Updated name - ");
                    name = sc.next();
                    System.out.println("Enter Updated age - ");
                    age = sc.nextInt();
                    System.out.println("Enter Updated grade - ");
                    grade = sc.next().charAt(0);
                    accessRepo.updateStudent(new Student(
                            id,
                            age,
                            name,
                            grade));
                    break;
                case 3:
                    int viewChoice = 1;
                    System.out.println("1. Want to view everyone");
                    System.out.println("2. Want to search based on ID/Name");
                    System.out.println("Enter your choice: ");
                    viewChoice = sc.nextInt();

                    switch (viewChoice) {
                        case 1:
                            ArrayList<Student> list = accessRepo.retrieve();
                            Utils.viewStudents(list);
                            break;
                        case 2:
                            System.out.println("1. ID");
                            System.out.println("2. Name");
                            int printChoice = sc.nextInt();

                            if (printChoice == 1) {
                                System.out.println("Enter ID - ");
                                Integer searchId = sc.nextInt();
                                Student st = accessRepo.retrieve(searchId);
                                if (st.getId() != null)
                                    Utils.viewStudent(st);
                                else
                                    System.out.println("No Students to view");
                            } else if (printChoice == 2) {
                                System.out.println("Enter Name - ");
                                String searchName = sc.next();
                                ArrayList<Student> stList = accessRepo.retrieve(searchName);

                                if (stList.size() == 1)
                                    Utils.viewStudent(stList.get(0));
                                else if (stList.size() > 1)
                                    Utils.viewStudents(stList);
                                else
                                    System.out.println("No Students to view");
                            }
                    }
                    break;
                default:
                    System.out.println("Invalid Choice/Exit");
                    break;
            }
        } while (choice < 4);
    }
}
