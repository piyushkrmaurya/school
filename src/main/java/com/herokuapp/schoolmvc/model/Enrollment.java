package com.herokuapp.schoolmvc.model;

public class Enrollment{
    private Student student;
    private _Class _class;
    private Status status;
    private Grade grade;

    public Enrollment(){
        
    }

    public Enrollment(Student student, _Class _class, Status status, Grade grade) {
        this.student = student;
        this._class = _class;
        this.status = status;
        this.grade = grade;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public _Class get_class() {
        return _class;
    }

    public void set_class(_Class _class) {
        this._class = _class;
    }

    public Enrollment(Student student, _Class _class) {
        this.student = student;
        this._class = _class;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

}