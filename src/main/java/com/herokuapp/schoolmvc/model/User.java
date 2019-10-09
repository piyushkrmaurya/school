package com.herokuapp.schoolmvc.model;

public class User {
 
    private Long userId;
    private String userName;
    private String name;
    private String password;
    private String gender;
    private String address;
    private UserType type;
 
    public User() {
 
    }
 
    public User(Long userId, String userName, String name, String password, String gender, String address, UserType type) {
        this.userId = userId;
        this.userName = userName;
        this.name = name;
        this.password = password;
        this.gender = gender;
        this.address = address;
        this.type = type;
    }
 
    public Long getUserId() {
        return userId;
    }
 
    public void setUserId(Long userId) {
        this.userId = userId;
    }
 
    public String getUserName() {
        return userName;
    }
 
    public void setUserName(String userName) {
        this.userName = userName;
    }
 
    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }
 
    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }
 
}