package com.herokuapp.schoolmvc.controller;

import java.security.Principal;

import com.herokuapp.schoolmvc.dao.UserDAO;
import com.herokuapp.schoolmvc.model.User;

import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {

    @Autowired
    private UserDAO userDAO;
 
    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String profile(Model model, Principal principal) {
 
        org.springframework.security.core.userdetails.User loggedinUser = 
        (org.springframework.security.core.userdetails.User) 
        ((Authentication) principal).getPrincipal();
 
        User profile = userDAO.findUserAccount(loggedinUser.getUsername());

        model.addAttribute("profile", profile);
 
        return "profile";
    }

    @RequestMapping(value = "/users")
    public String welcomePage(Model model) {
        model.addAttribute("users", userDAO.listUserAccounts());
        return "users";
    }
}