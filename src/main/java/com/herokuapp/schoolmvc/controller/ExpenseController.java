package com.herokuapp.schoolmvc.controller;

import com.herokuapp.schoolmvc.dao.CourseDAO;
import com.herokuapp.schoolmvc.dao.ExpenseDAO;
import com.herokuapp.schoolmvc.dao.TeacherDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ExpenseController {

    @Autowired
    private ExpenseDAO expenseDao; 

    @Autowired
    private CourseDAO courseDao; 

    @Autowired
    private TeacherDAO teacherDao;

    @RequestMapping(value = "/expenses")
    public String viewCourses(Model model){
        model.addAttribute("title", "All expenses");
        model.addAttribute("expenses", expenseDao.listAllExpenses());
        return "expenses";
    }
}