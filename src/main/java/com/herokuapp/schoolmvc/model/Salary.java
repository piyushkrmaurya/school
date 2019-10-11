package com.herokuapp.schoolmvc.model;

import java.sql.Date;

public class Salary {
    Long empId;
    Long salaryId;
    Long amount;
    Date date;

    public Salary() {

    }

    public Salary(Long empId, Long salaryId, Long amount, Date date) {
        this.empId = empId;
        this.salaryId = salaryId;
        this.amount = amount;
        this.date = date;
    }

    public Long getEmpId() {
        return empId;
    }

    public void setEmpId(Long empId) {
        this.empId = empId;
    }

    public Long getSalaryId() {
        return salaryId;
    }

    public void setSalaryId(Long salaryId) {
        this.salaryId = salaryId;
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

    
}