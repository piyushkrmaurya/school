package com.herokuapp.schoolmvc.model;

import java.sql.Date;

public class Expense {
    Long expenseId;
    Long cost;
    Date date;
    Employee manager;

    public Expense() {

    }

    public Expense(Long expenseId, Long cost, Date date, Employee manager) {
        this.expenseId = expenseId;
        this.cost = cost;
        this.date = date;
        this.manager = manager;
    }

    public Long getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(Long expenseId) {
        this.expenseId = expenseId;
    }

    public Long getCost() {
        return cost;
    }

    public void setCost(Long cost) {
        this.cost = cost;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }
}