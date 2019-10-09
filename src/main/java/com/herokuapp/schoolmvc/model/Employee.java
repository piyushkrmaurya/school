package com.herokuapp.schoolmvc.model;

public class Employee {
    
    private Long empId;
    private Long salary;

    public Employee(Long empId, Long salary) {
        this.empId = empId;
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