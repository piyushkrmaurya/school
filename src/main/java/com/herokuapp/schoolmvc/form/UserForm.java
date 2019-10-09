package com.herokuapp.schoolmvc.form;

import com.herokuapp.schoolmvc.model.UserType;

public class UserForm {
 
    private String userName;
    private String name;
    private String address;
    private String password;
    private String gender;
    private UserType type;
 
    public UserForm() {
 
    }

    public UserForm(String userName, String password, String name, String gender, String address,
    UserType type) {
        this.userName = userName;
        this.password = password;
        this.name = name;
        this.gender = gender;
        this.address = address;
        this.type = type;
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
 
    @Override
    public String toString() {
        return this.userName + "/" + this.password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }
 
    public void setGender(String gender) {
        this.gender = gender;
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