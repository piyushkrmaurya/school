package com.herokuapp.schoolmvc.controller;

import java.security.Principal;

import com.herokuapp.schoolmvc.dao.ClassDAO;
import com.herokuapp.schoolmvc.dao.CourseDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TeacherController {

    @Autowired
    private ClassDAO classDao; 

    @Autowired
    private CourseDAO courseDao; 

    @RequestMapping(value = "/classes")
    public String viewClasses(Model model){
        model.addAttribute("classes", classDao.listAllClasses());
        return "classes";
    }
}