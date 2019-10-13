package com.herokuapp.schoolmvc.form;

public class CourseForm {
    private String name;
    private Long level;
    private Long teacherId;
    private Long year;

    public CourseForm() {

    }

    public CourseForm(String name, Long level, Long teacherId) {
        this.name = name;
        this.level = level;
        this.teacherId = teacherId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getLevel() {
        return level;
    }

    public void setLevel(Long level) {
        this.level = level;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public Long getYear() {
        return year;
    }

    public void setYear(Long year) {
        this.year = year;
    }
}