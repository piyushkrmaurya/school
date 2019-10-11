package com.herokuapp.schoolmvc.controller;

import com.herokuapp.schoolmvc.dao.ClassDAO;
import com.herokuapp.schoolmvc.dao.CourseDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StudentController {

    @Autowired
    private ClassDAO classDao; 

    @Autowired
    private CourseDAO courseDao; 
    
    @RequestMapping(value = "/courses/{level}")
    public String viewCourses(Model model, @PathVariable Long level){
        model.addAttribute("courses", courseDao.listCoursesByClass(level));
        return "courses";
    }
}