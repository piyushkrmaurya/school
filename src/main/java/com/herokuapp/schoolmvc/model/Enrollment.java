package com.herokuapp.schoolmvc.model;

public class Enrollment{
    private Long enrollId;
    private Student student;
    private _Class _class;
    private Status status;

    public Enrollment(){
        
    }

    public Enrollment(Long enrollId, Student student, _Class _class, Status status) {
        this.enrollId = enrollId;
        this.student = student;
        this._class = _class;
        this.status = status;
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

    public Long getEnrollId() {
        return enrollId;
    }

    public void setEnrollId(Long enrollId) {
        this.enrollId = enrollId;
    }

}