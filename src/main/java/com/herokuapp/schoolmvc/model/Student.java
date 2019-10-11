package com.herokuapp.schoolmvc.model;

public class Student{
    private User user;

    public Student(){
        
    }

    public Student(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
}