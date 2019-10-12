package com.herokuapp.schoolmvc.model;

public class Employee extends User {
    
    private Long empId;
    private Long salary;

    public Employee(){

    }

    public Employee(Long empId, Long salary, String userName, String name, String gender, String address, UserType type) {
        super(userName, name, gender, address, type);
        this.empId = empId;
        this.salary = salary;
    }

    public Employee(Long salary, String userName, String name, String gender, String address, UserType type){
        super(userName, name, gender, address, type);
        this.salary = salary;
    }

    public Long getEmpId() {
        return empId;
    }

    public void setEmpId(Long empId) {
        this.empId = empId;
    }

    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }
}