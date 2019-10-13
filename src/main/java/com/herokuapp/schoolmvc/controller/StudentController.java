package com.herokuapp.schoolmvc.controller;

import com.herokuapp.schoolmvc.dao.ClassDAO;
import com.herokuapp.schoolmvc.dao.CourseDAO;
import com.herokuapp.schoolmvc.dao.StudentDAO;
import com.herokuapp.schoolmvc.form.EnrollmentForm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class StudentController {

    @Autowired
    private ClassDAO classDao; 

    @Autowired
    private CourseDAO courseDao; 

    @Autowired
    private StudentDAO studentDao; 

    @RequestMapping(value = "/student/{id}", method = RequestMethod.GET)
    public String enrollStudent(Model model, @PathVariable Long id) {

        model.addAttribute("student", studentDao.findStudentById(id));
        
        EnrollmentForm enrollForm = new EnrollmentForm();
        
        model.addAttribute("enrollForm", enrollForm);
        
        return "student-edit";
    }

    @RequestMapping(value = "/class/{level}", method = RequestMethod.GET)
    public String viewClassStudents(Model model, @PathVariable Long level) {

        model.addAttribute("students", studentDao.listStudentsByClass(level));
        
        return "students";
    }
    
}