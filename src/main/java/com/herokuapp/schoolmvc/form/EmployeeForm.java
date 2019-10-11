package com.herokuapp.schoolmvc.form;

import java.util.ArrayList;
import java.util.List;

public class EmployeeForm {
    private Long salary;
    private List<Long> roles;
    
    public EmployeeForm(){
        this.roles = new ArrayList<Long>();
    }

    public EmployeeForm(Long salary, List<Long> roles) {
        this.salary = salary;
        this.roles = roles;
    }

    public void add(Long role){
        this.roles.add(role);
    }

    public List<Long> getRoles() {
        return roles;
    }

    public void setRoles(List<Long> roles) {
        this.roles = roles;
    }

    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }
}