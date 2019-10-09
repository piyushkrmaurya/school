package com.herokuapp.schoolmvc.model;

public class Class{
    private String level;

    @Override
    public String toString() {
        return level;
    }

    public Class(String level) {
        this.level = level;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}