package com.herokuapp.schoolmvc.controller;

import java.util.List;

import com.herokuapp.schoolmvc.dao.EmployeeDAO;
import com.herokuapp.schoolmvc.dao.RoleDAO;
import com.herokuapp.schoolmvc.form.EmployeeForm;
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
    private EmployeeDAO employeeDao;
    
    @Autowired
    private RoleDAO roleDAO;

    @RequestMapping(value = "/employee/{id}", method = RequestMethod.GET)
    public String employeeEdit(Model model, @PathVariable Long id) {
        model.addAttribute("title", "Edit roles");

        model.addAttribute("employee", employeeDao.findEmployeeById(id));
        
        List<Role> assignedRoles = roleDAO.getAssignedRoles(id);
        List<Role> roles = roleDAO.getAllRoles();

        model.addAttribute("roles", roles);        
        
        EmployeeForm empForm = new EmployeeForm();
        for(Role role: assignedRoles){
            empForm.add(role.getRoleId());
        }
        
        model.addAttribute("empForm", empForm);
        
        return "employee-edit";
    }

    @RequestMapping(value = "/employee/{id}/save", method = RequestMethod.POST)
    public String saveEmployee(
            Model model,
            @ModelAttribute("empForm") EmployeeForm empForm,
            @PathVariable Long id
    ) {
        employeeDao.updateEmployee(id, empForm);
        return "redirect:/successful";
    }
}