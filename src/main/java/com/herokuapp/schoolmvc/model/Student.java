package com.herokuapp.schoolmvc.model;

public class Student extends User{
    private Long studentId;

    public Student(){
        
    }

    public Student(Long studentId, String userName, String name, String gender, String address, UserType type) {
        super(userName, name, gender, address, type);
        this.studentId = studentId;
    }

    public Student(Long studentId) {
        this.studentId = studentId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }
    
}