package com.herokuapp.schoolmvc.model;

public class Teacher {
    
    private Long techerId;
    private String qualification;

    public Teacher() {

    }

    public Teacher(Long techerId, String qualification) {
        this.techerId = techerId;
        this.qualification = qualification;
    }

    public Long getTeacherId() {
        return techerId;
    }

    public void setTeacherId(Long techerId) {
        this.techerId = techerId;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }
}