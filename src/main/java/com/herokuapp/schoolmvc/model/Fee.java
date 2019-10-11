package com.herokuapp.schoolmvc.model;

import java.sql.Date;

public class Fee {
    Long feeId;
    Student student;
    Long amount;
    Date date;

    public Fee(){
        
    }

    public Fee(Long feeId, Student student, Long amount, Date date) {
        this.student = student;
        this.feeId = feeId;
        this.amount = amount;
        this.date = date;
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

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    
}