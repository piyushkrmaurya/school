package com.herokuapp.schoolmvc.model;

public class Course {
    
    private Long courseId;
    private String courseName;
    private Long level;

    public Course(Long courseId, String courseName, Long level) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.level = level;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Long getLevel() {
        return level;
    }

    public void setLevel(Long level) {
        this.level = level;
    }
}