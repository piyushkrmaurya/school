package com.herokuapp.schoolmvc.controller;

import com.herokuapp.schoolmvc.dao.UserDAO;
import com.herokuapp.schoolmvc.form.UserForm;
import com.herokuapp.schoolmvc.model.User;
import com.herokuapp.schoolmvc.model.UserType;
import com.herokuapp.schoolmvc.validator.UserValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RegisterController {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private UserValidator userValidator;

    protected void initBinder(WebDataBinder dataBinder) {
        Object target = dataBinder.getTarget();
        if (target == null) {
            return;
        }
        if (target.getClass() == User.class) {
            dataBinder.setValidator(userValidator);
        }
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String viewRegister(Model model) {

        model.addAttribute(new UserForm());
        model.addAttribute("userTypes", UserType.values());

        return "register";
    }
    
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String saveRegister(
            Model model,
            @ModelAttribute("userForm") @Validated UserForm userForm,
            BindingResult result,
            final RedirectAttributes redirectAttributes
    ) {

        model.addAttribute("userTypes", UserType.values());
    
        if (result.hasErrors()) {
            return "register";
        }

        try {
            Long id = userDAO.createUserAccount(userForm);

            if(userForm.getType().equals(UserType.EMPLOYEE))
                return "redirect:/employee/"+id.toString();
            else
                model.addAttribute("message", "User added successfully.");
                return "successful";
        }
        catch (Exception e) {
            model.addAttribute("errorMessage", "Error: " + e.getMessage());
            return "register";
        }
    }

    @RequestMapping(value = "/successful")
    public String successful(Model model){
        return "successful";
    }
    

}