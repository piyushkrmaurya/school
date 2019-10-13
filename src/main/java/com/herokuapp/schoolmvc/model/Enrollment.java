package com.herokuapp.schoolmvc.model;

public class Enrollment{
    private Long enrollId;
    private Student student;
    private _Class _class;
    private Status status;
    private Long year;

    public Enrollment(){
        
    }

    public Enrollment(Long enrollId, Student student, _Class _class, Status status, Long year) {
        this.enrollId = enrollId;
        this.student = student;
        this._class = _class;
        this.status = status;
        this.year = year;
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

    public Long getYear() {
        return year;
    }

    public void setYear(Long year) {
        this.year = year;
    }

}