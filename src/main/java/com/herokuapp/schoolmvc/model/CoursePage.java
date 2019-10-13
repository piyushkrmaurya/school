package com.herokuapp.schoolmvc.model;

public class CoursePage{
    private Long cpId;
    private Course course;
    private Long year;

    public CoursePage(Long cpId, Course course, Long year) {
        this.cpId = cpId;
        this.course = course;
        this.year = year;
    }

    public Long getCpId() {
        return cpId;
    }

    public void setCpId(Long cpId) {
        this.cpId = cpId;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Long getYear() {
        return year;
    }

    public void setYear(Long year) {
        this.year = year;
    }

    
}