package com.herokuapp.schoolmvc.model;

import java.util.HashMap;
import java.util.Map;

public enum UserType {
    ADMIN ("Admin"),
    EMPLOYEE ("Employee"),
    STUDENT ("Student");

    private final String name;

    private static final Map<String, UserType> lookup = new HashMap<String, UserType>();

    static {
        for (UserType type : UserType.values()) {
            lookup.put(type.getName(), type);
        }
    }

    private UserType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static UserType get(String type) {
        return lookup.get(type);
    }
    
}