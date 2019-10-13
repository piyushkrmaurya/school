package com.herokuapp.schoolmvc.model;

import java.sql.Date;

public class Fee {
    private Long feeId;
    private Student student;
    private Employee manager;
    private Long amount;
    private Date date;
    private Month month;
    private Long year;

    public Fee() {

    }

    public Fee(Long feeId, Student student, Employee manager, Long amount, Month month, Long year, Date date) {
        this.feeId = feeId;
        this.student = student;
        this.manager = manager;
        this.amount = amount;
        this.date = date;
        this.month = month;
        this.year = year;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student employee) {
        this.student = employee;
    }

    public Long getFeeId() {
        return feeId;
    }

    public void setFeeId(Long feeId) {
        this.feeId = feeId;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Month getMonth() {
        return month;
    }

    public void setMonth(Month month) {
        this.month = month;
    }

    public Long getYear() {
        return year;
    }

    public void setYear(Long year) {
        this.year = year;
    }

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    
}