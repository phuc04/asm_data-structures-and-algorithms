/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package asm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author hoang1
 */
public class StudentManagementSystem {

    private static ArrayList<Student> students = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("1. Add Student");
            System.out.println("2. Edit Student");
            System.out.println("3. Delete Student");
            System.out.println("4. Print Student Rankings");
            System.out.println("5. Search Student");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            String input = scanner.nextLine();
            int option;
            try {
                option = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                continue;
            }

            switch (option) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    editStudent();
                    break;
                case 3:
                    deleteStudent();
                    break;
                case 4:
                    printStudentRankings();
                    break;
                case 5:
                    searchStudent();
                    break;
                case 6:
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static void addStudent() {
        System.out.print("Enter Student Name: ");
        String name = readStringInput();

        System.out.print("Enter Student ID: ");
        String studentID = scanner.nextLine();

        System.out.print("Enter Class Name: ");
        String className = scanner.nextLine();

        double grade = 0.0;
        boolean validGrade = false;
        while (!validGrade) {
            try {
                System.out.print("Enter Grade (0-10): ");
                grade = scanner.nextDouble();
                if (grade < 0 || grade > 10) {
                    System.out.println("Grade must be between 0 and 10.");
                    scanner.nextLine(); // consume newline
                } else {
                    validGrade = true;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine(); // consume invalid input
            }
        }
        scanner.nextLine(); // consume newline after reading grade

        students.add(new Student(name, studentID, className, grade));
        System.out.println("Student added successfully!");
    }

    private static void editStudent() {
        System.out.print("Enter Student ID to edit: ");
        String studentID = scanner.nextLine();

        Student student = findStudentById(studentID);
        if (student == null) {
            System.out.println("Student not found!");
            return;
        }

        System.out.print("Enter New Name (leave empty to keep unchanged): ");
        String name = scanner.nextLine();
        if (!name.isEmpty()) {
            student.setName(name);
        }

        System.out.print("Enter New Class (leave empty to keep unchanged): ");
        String className = scanner.nextLine();
        if (!className.isEmpty()) {
            student.setClassName(className);
        }

        System.out.print("Enter New Grade (0-10): ");
        double grade;
        try {
            grade = Double.parseDouble(scanner.nextLine());
            if (grade >= 0 && grade <= 10) {
                student.setGrade(grade);
                System.out.println("Student updated successfully!");
            } else {
                System.out.println("Invalid grade. Update failed.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Update failed.");
        }
    }

    private static void deleteStudent() {
        System.out.print("Enter Student ID to delete: ");
        String studentID = scanner.nextLine();

        Student student = findStudentById(studentID);
        if (student == null) {
            System.out.println("Student not found!");
            return;
        }

        students.remove(student);
        System.out.println("Student deleted successfully!");
    }

    private static void printStudentRankings() {
        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }

        ArrayList<Student> sortedStudents = new ArrayList<>(students);
        Collections.sort(sortedStudents, new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                return Double.compare(s2.getGrade(), s1.getGrade());
            }
        });

        int rank = 1;
        for (Student student : sortedStudents) {
            System.out.println("Rank " + rank + ": " + student);
            rank++;
        }
    }

    private static void searchStudent() {
        System.out.print("Enter Student ID to search: ");
        String studentID = scanner.nextLine();

        Student student = findStudentById(studentID);
        if (student == null) {
            System.out.println("Student not found!");
        } else {
            System.out.println(student);
        }
    }

    private static Student findStudentById(String studentID) {
        for (Student student : students) {
            if (student.getStudentID().equals(studentID)) {
                return student;
            }
        }
        return null;
    }

    private static String readStringInput() {
        while (true) {
            String input = scanner.nextLine();
            if (input.matches("[a-zA-Z ]+")) {
                return input;
            } else {
                System.out.println("Invalid input. Please enter letters only.");
            }
        }
    }
}

