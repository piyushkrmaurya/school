package com.herokuapp.schoolmvc.form;

import java.util.ArrayList;
import java.util.List;

import com.herokuapp.schoolmvc.model.UserType;

public class UserForm {
 
    private String userName;
    private String name;
    private String address;
    private String password;
    private String gender;
    private UserType type;
    private Long level;
    private Long year;
    private String qualifications;
    private Long salary;
    private List<Long> roles = new ArrayList<Long>();
    private List<Long> courses;
 
    public UserForm() {
 
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

    public Long getLevel() {
        return level;
    }

    public void setLevel(Long level) {
        this.level = level;
    }

    public String getQualifications() {
        return qualifications;
    }

    public void setQualifications(String qualifications) {
        this.qualifications = qualifications;
    }

    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }

    public List<Long> getRoles() {
        return roles;
    }

    public void setRoles(List<Long> roles) {
        this.roles = roles;
    }

    public void addRole(Long role) {
        roles.add(role);
    }

    public List<Long> getCourses() {
        return courses;
    }

    public void setCourses(List<Long> courses) {
        this.courses = courses;
    }

    public Long getYear() {
        return year;
    }

    public void setYear(Long year) {
        this.year = year;
    }
 
}