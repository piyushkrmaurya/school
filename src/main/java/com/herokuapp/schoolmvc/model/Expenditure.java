package com.herokuapp.schoolmvc.model;

import java.sql.Date;

public class Expenditure {
    Long expenseId;
    Long cost;
    Date date;

    public Expenditure() {

    }

    public Expenditure(Long expenseId, Long cost, Date date) {
        this.expenseId = expenseId;
        this.cost = cost;
        this.date = date;
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
}