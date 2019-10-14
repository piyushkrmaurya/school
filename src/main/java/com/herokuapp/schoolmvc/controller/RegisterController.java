package com.herokuapp.schoolmvc.controller;

import com.herokuapp.schoolmvc.dao.ClassDAO;
import com.herokuapp.schoolmvc.dao.CourseDAO;
import com.herokuapp.schoolmvc.dao.RoleDAO;
import com.herokuapp.schoolmvc.dao.UserDAO;
import com.herokuapp.schoolmvc.form.UserForm;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RegisterController {

    @Autowired
    private UserDAO userDAO;

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
        model.addAttribute("title", "Add New User");

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
        model.addAttribute("title", "Add New User");
    
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

    @RequestMapping(value = "/successful")
    public String successful(Model model){
        return "successful";
    }
    

}