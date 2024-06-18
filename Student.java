/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package asm;
/**
 *
 * @author hoang
 */
class Student {
    private String name;
    private String studentID;
    private String className;
    private double grade;

    public Student(String name, String studentID, String className, double grade) {
        this.name = name;
        this.studentID = studentID;
        this.className = className;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public String getGradeCategory() {
        if (grade < 5.0) return "Fail";
        else if (grade < 6.5) return "Medium";
        else if (grade < 7.5) return "Good";
        else if (grade < 9.0) return "Very Good";
        else return "Excellent";
    }

    @Override
    public String toString() {
        return "Student ID: " + studentID + ", Name: " + name + ", Class: " + className + ", Grade: " + grade + ", Rank: " + getGradeCategory();
    }
}