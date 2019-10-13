package com.herokuapp.schoolmvc.controller;

import java.security.Principal;
import java.util.List;

import com.herokuapp.schoolmvc.dao.ClassDAO;
import com.herokuapp.schoolmvc.dao.CourseDAO;
import com.herokuapp.schoolmvc.dao.UserDAO;
import com.herokuapp.schoolmvc.dao.RoleDAO;

import com.herokuapp.schoolmvc.model.Course;
import com.herokuapp.schoolmvc.model.Role;
import com.herokuapp.schoolmvc.model.User;
import com.herokuapp.schoolmvc.model.UserType;
import com.herokuapp.schoolmvc.model._Class;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {

    @Autowired
    private UserDAO userDao;

    @Autowired
    private ClassDAO classDao; 

    @Autowired
    private CourseDAO courseDao; 

    @Autowired
    private RoleDAO roleDAO;

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String profile(Model model, Principal principal) {
 
        User profile = userDao.findUserAccount(principal.getName());

        model.addAttribute("profile", profile);
 
        return "profile";
    }


    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public String dashboard(Model model, Principal principal) {
        User profile = userDao.findUserAccount(principal.getName());
        model.addAttribute("profile", profile);

        if(profile.getType() == UserType.STUDENT){
            _Class _class = classDao.getCurrentClassByStudentId(profile.getUserId());
            model.addAttribute("class", _class);
    
            List<Course> courses = courseDao.listCoursesByClass(_class.getLevel());
            model.addAttribute("courses", courses);
        }

        else if(profile.getType() == UserType.STUDENT){
            List<Role> roles = roleDAO.getAssignedRoles(profile.getUserId());
        }

        return "dashboard";
    }

    @RequestMapping(value = "/users")
    public String welcomePage(Model model) {
        model.addAttribute("users", userDao.listUserAccounts());
        return "users";
    }
}