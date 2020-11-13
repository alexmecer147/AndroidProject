package com.kanfeer.sqliteexamapplication.entity;

public class Student {
    private int studentId;
    private String studentName;
    private int studentAge;
    private String studentMajor;


    public String toString(){
        return "Id-"+studentId+" Name-"+studentName+" Age-"+studentAge+" Major-"+studentMajor;
    }

    public Student(){

    }

    public Student(int studentId, String studentName, int studentAge, String studentMajor) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.studentAge = studentAge;
        this.studentMajor = studentMajor;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public int getStudentAge() {
        return studentAge;
    }

    public void setStudentAge(int studentAge) {
        this.studentAge = studentAge;
    }

    public String getStudentMajor() {
        return studentMajor;
    }

    public void setStudentMajor(String studentMajor) {
        this.studentMajor = studentMajor;
    }


}
