package com.herokuapp.schoolmvc.model;

public class Result {
    Enrollment enrollment;
    Grade grade;

    public Result() {
        
    }

    public Result(Enrollment enrollment, Grade grade) {
        this.enrollment = enrollment;
        this.grade = grade;
    }

    public Enrollment getEnrollment() {
        return enrollment;
    }

    public void setEnrollment(Enrollment enrollment) {
        this.enrollment = enrollment;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }
    
}