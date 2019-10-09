package com.herokuapp.schoolmvc.model;

public class Enrollment{
    private Student student;
    private Class _class;

    public Enrollment(){
        
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Class get_class() {
        return _class;
    }

    public void set_class(Class _class) {
        this._class = _class;
    }

    public Enrollment(Student student, Class _class) {
        this.student = student;
        this._class = _class;
    }

        

}