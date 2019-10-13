package com.herokuapp.schoolmvc.form;

import com.herokuapp.schoolmvc.model.Grade;

public class SingleResult {
    private Long enrollId;
    private Grade grade;

    public SingleResult() {

    }

    public SingleResult(Long enrollId) {
        this.enrollId = enrollId;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public Long getEnrollId() {
        return enrollId;
    }

    public void setEnrollId(Long enrollId) {
        this.enrollId = enrollId;
    }

}