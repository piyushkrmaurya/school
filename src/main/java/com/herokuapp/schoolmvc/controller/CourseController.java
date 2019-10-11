package com.herokuapp.schoolmvc.controller;

import java.security.Principal;

import com.herokuapp.schoolmvc.dao.ClassDAO;
import com.herokuapp.schoolmvc.dao.CourseDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CourseController {

    @Autowired
    private ClassDAO classDao; 

    @Autowired
    private CourseDAO courseDao; 

    @RequestMapping(value = "/courses")
    public String viewCourses(Model model){
        model.addAttribute("courses", courseDao.listAllCourses());
        return "courses";
    }
}