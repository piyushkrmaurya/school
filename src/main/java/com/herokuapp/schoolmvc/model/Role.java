package com.herokuapp.schoolmvc.model;

public class Role {
    private Long roleId;
    private String roleName;

    public Role(Long roleId, String roleName) {
        this.roleId = roleId;
        this.roleName = roleName;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public String toString(){
        return getRoleName();
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    
}
