package com.herokuapp.schoolmvc.model;

public class Result {
    private Enrollment enrollment;
    private Grade grade;
    private Course course;

    public Result() {
        
    }

    public Result(Enrollment enrollment, Grade grade, Course course) {
        this.enrollment = enrollment;
        this.grade = grade;
        this.course = course;
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

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
    
}