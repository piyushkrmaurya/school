package com.herokuapp.schoolmvc.model;

public class _Class{
    private Long level;

    @Override
    public String toString() {
        return level.toString();
    }

    public _Class() {
    }

    public _Class(Long level) {
        this.level = level;
    }

    public Long getLevel() {
        return level;
    }

    public void setLevel(Long level) {
        this.level = level;
    }
}