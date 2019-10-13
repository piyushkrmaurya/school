package com.herokuapp.schoolmvc.model;

import java.sql.Date;

public class Salary {
    private Long salaryId;
    private Employee employee;
    private Employee manager;
    private Long amount;
    private Date date;
    private Month month;
    private Long year;

    public Salary() {

    }

    public Salary(Long salaryId, Employee employee, Employee manager, Long amount, Month month, Long year, Date date) {
        this.salaryId = salaryId;
        this.employee = employee;
        this.manager = manager;
        this.amount = amount;
        this.date = date;
        this.month = month;
        this.year = year;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
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