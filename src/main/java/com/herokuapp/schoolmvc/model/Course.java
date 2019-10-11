package com.herokuapp.schoolmvc.model;

public class Course {
    
    private Long courseId;
    private String name;
    private Long level;
    private Long teacherid;

    public Course() {

    }

    public Course(Long courseId, String name, Long level, Long teacherid) {
        this.courseId = courseId;
        this.name = name;
        this.level = level;
        this.teacherid = teacherid;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getLevel() {
        return level;
    }

    public void setLevel(Long level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getTeacherId() {
        return teacherid;
    }

    public void setTeacherId(Long teacherid) {
        this.teacherid = teacherid;
    }
}