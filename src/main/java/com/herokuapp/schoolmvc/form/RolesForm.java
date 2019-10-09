package com.herokuapp.schoolmvc.form;

import java.util.ArrayList;
import java.util.List;

public class RolesForm {
    private List<Long> roles;
    
    public RolesForm(){
        this.roles = new ArrayList<Long>();
    }

    public RolesForm(List<Long> roles) {
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
}