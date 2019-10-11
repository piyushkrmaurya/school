package com.herokuapp.schoolmvc.model;

public class Course {
    private Long courseId;
    private String name;
    private _Class _class;
    private Teacher teacher;

    public Course() {

    }

    public Course(Long courseId, String name, _Class _class, Teacher teacher) {
        this.courseId = courseId;
        this.name = name;
        this._class = _class;
        this.teacher = teacher;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public _Class get_Class() {
        return _class;
    }

    public void set_Class(_Class _class) {
        this._class = _class;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}