package com.herokuapp.schoolmvc.model;

public class Student{
    private User user;
    private String rollNumber;

    public Student(){
        
    }

    public Student(User user, String rollNumber) {
        this.user = user;
        this.rollNumber = rollNumber;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(String rollNumber) {
        this.rollNumber = rollNumber;
    }
    
}