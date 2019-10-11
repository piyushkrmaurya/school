package com.herokuapp.schoolmvc.model;

public enum Status {
    ONGOING ("Ongoing"),
    PASSED ("Passed"),
    FAILED ("Failed");

    private final String name;

    private Status(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}