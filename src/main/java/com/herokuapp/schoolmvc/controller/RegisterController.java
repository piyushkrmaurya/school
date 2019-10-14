package com.herokuapp.schoolmvc.controller;

import java.util.List;

import com.herokuapp.schoolmvc.dao.ClassDAO;
import com.herokuapp.schoolmvc.dao.CourseDAO;
import com.herokuapp.schoolmvc.dao.EmployeeDAO;
import com.herokuapp.schoolmvc.dao.RoleDAO;
import com.herokuapp.schoolmvc.dao.StudentDAO;
import com.herokuapp.schoolmvc.dao.TeacherDAO;
import com.herokuapp.schoolmvc.dao.UserDAO;
import com.herokuapp.schoolmvc.form.UserForm;
import com.herokuapp.schoolmvc.model.Employee;
import com.herokuapp.schoolmvc.model.Role;
import com.herokuapp.schoolmvc.model.Student;
import com.herokuapp.schoolmvc.model.Teacher;
import com.herokuapp.schoolmvc.model.User;
import com.herokuapp.schoolmvc.model.UserType;
import com.herokuapp.schoolmvc.validator.UserFormValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RegisterController {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private EmployeeDAO employeeDAO;

    @Autowired
    private TeacherDAO teacherDAO;

    @Autowired
    private ClassDAO classDao;

    @Autowired
    private CourseDAO courseDao;

    @Autowired
    private UserFormValidator userFormValidator;

    @Autowired
    private RoleDAO roleDAO;

    @InitBinder
    protected void initBinder(WebDataBinder dataBinder) {
        Object target = dataBinder.getTarget();
        if (target == null) {
            return;
        }
        if (target.getClass() == UserForm.class) {
            dataBinder.setValidator(userFormValidator);
        }
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String viewRegister(Model model) {
        model.addAttribute("title", "User Registration");

        model.addAttribute("userForm", new UserForm());
        model.addAttribute("userTypes", UserType.values());
        model.addAttribute("classes", classDao.listAllClasses());
        model.addAttribute("courses", courseDao.listAllCourses());
        model.addAttribute("roles", roleDAO.getAllRoles());

        return "register";
    }
    
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String saveRegister(
            Model model,
            @ModelAttribute("userForm") @Validated UserForm userForm,
            BindingResult result,
            final RedirectAttributes redirectAttributes
    ) {
        model.addAttribute("title", "User Registration");

        model.addAttribute("userTypes", UserType.values());
        model.addAttribute("classes", classDao.listAllClasses());
        model.addAttribute("courses", courseDao.listAllCourses());
        model.addAttribute("roles", roleDAO.getAllRoles());
    
        if (result.hasErrors()) {
            return "register";
        }

        try {
            userDAO.createUserAccount(userForm);
            model.addAttribute("message", "User added successfully.");
            return "successful";
        }
        catch (Exception e) {
            model.addAttribute("errorMessage", "Some error occurred");
            return "register";
        }
    }

    @RequestMapping(value = "/update-user/{id}", method = RequestMethod.GET)
    public String updateUserView(Model model, @PathVariable Long id) {
        User profile = userDAO.findUserAccountById(id);
        UserForm userForm = new UserForm();
        Employee employee;
        Teacher teacher;
        if(profile.getType() == UserType.EMPLOYEE){
            employee = teacherDAO.findTeacherById(profile.getUserId());
            userForm.setSalary(employee.getSalary());
            List<Role> assignedRoles = roleDAO.getAssignedRoles(employee.getEmpId());
            for(Role role: assignedRoles) {
                userForm.addRole(role.getRoleId());
            }
            for(Role role: assignedRoles) {
                if(role.toString().equals("TEACHER")){
                    teacher = teacherDAO.findTeacherById(profile.getUserId());
                    userForm.setQualifications(teacher.getQualification());
                }
            }
        }
        userForm.setUserName(profile.getUserName());
        userForm.setQualifications(profile.getUserName());
        userForm.setName(profile.getName());
        userForm.setAddress(profile.getAddress());
        userForm.setGender(profile.getGender());

        List<Role> roles = roleDAO.getAllRoles();

        model.addAttribute("roles", roles);        

        model.addAttribute("title", "Update User");
        model.addAttribute("userForm", userForm);
        model.addAttribute("roles", roleDAO.getAllRoles());

        return "update-user";
    }
    
    @RequestMapping(value = "/update-user/{id}", method = RequestMethod.POST)
    public String updateUserView(
            Model model,
            @PathVariable Long id,
            @ModelAttribute("userForm") @Validated UserForm userForm,
            BindingResult result,
            final RedirectAttributes redirectAttributes
    ) {
        model.addAttribute("title", "Update User");

        model.addAttribute("userTypes", UserType.values());
        model.addAttribute("roles", roleDAO.getAllRoles());
    
        if (result.hasErrors()) {
            return "register";
        }

        try {
            userDAO.updateUserAccount(id, userForm);
            model.addAttribute("message", "User updated successfully.");
            return "successful";
        }
        catch (Exception e) {
            model.addAttribute("errorMessage", "Some error occurred");
            return "update-user";
        }
    }

    @RequestMapping(value = "/successful")
    public String successful(Model model){
        return "successful";
    }
    

}