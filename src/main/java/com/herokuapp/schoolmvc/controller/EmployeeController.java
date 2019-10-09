package com.herokuapp.schoolmvc.controller;

import java.util.List;

import com.herokuapp.schoolmvc.dao.EmployeeDAO;
import com.herokuapp.schoolmvc.dao.RoleDAO;
import com.herokuapp.schoolmvc.form.RolesForm;
import com.herokuapp.schoolmvc.model.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeDAO employeeDAO;
    
    @Autowired
    private RoleDAO roleDAO;

    @RequestMapping(value = "/employee/{id}", method = RequestMethod.GET)
    public String employeeEdit(Model model, @PathVariable Long id) {

        model.addAttribute("employee", employeeDAO.findEmployeeById(id));
        
        List<Role> assignedRoles = roleDAO.getAssignedRoles(id);
        List<Role> roles = roleDAO.getAllRoles();

        model.addAttribute("roles", roles);        
        
        RolesForm rolesForm = new RolesForm();
        for(Role role: roles){
            if(assignedRoles.contains(role))
                rolesForm.add(role.getRoleId());
        }
        
        model.addAttribute("rolesForm", rolesForm);
        
        return "employee-edit";
    }

    @RequestMapping(value = "/employee/{id}/save", method = RequestMethod.POST)
    public String saveEmployee(
            Model model,
            @ModelAttribute("rolesForm") RolesForm rolesForm,
            @PathVariable Long id
    ) {
        roleDAO.updateRoles(id, rolesForm);
        return "registerSuccessful";

    }
}