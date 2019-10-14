package com.herokuapp.schoolmvc.controller;

import java.security.Principal;

import com.herokuapp.schoolmvc.dao.SalaryDAO;
import com.herokuapp.schoolmvc.dao.UserDAO;
import com.herokuapp.schoolmvc.model.Month;
import com.herokuapp.schoolmvc.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SalaryController {

    @Autowired
    private SalaryDAO salaryDAO; 

    @Autowired
    private UserDAO userDao;


    @RequestMapping(value = "/salaries")
    public String viewAllSalaries(Model model){
        model.addAttribute("title", "Salary List");
        model.addAttribute("salaries", salaryDAO.listSalaryInstallments());
        return "salaries";
    }

    @RequestMapping(value = "/my-salaries")
    public String mySalary(Model model, Principal principal){
        model.addAttribute("title", "My Salary Installments");
        User user = userDao.findUserAccount(principal.getName());
        model.addAttribute("salaries", salaryDAO.listSalaryInstallmentsByEmpID(user.getUserId()));
        return "my-salaries";
    }

    @RequestMapping(value = "/dispatch-salary", method = RequestMethod.GET)
    public String dispatchSalaryView(Model model){
        model.addAttribute("title", "Dispatch Salary");
        model.addAttribute("months", Month.values()); 
        return "dispatch-salary";
    }

    @RequestMapping(value = "/dispatch-salary", method = RequestMethod.POST)
    public String dispatchSalaryView(Model model, @RequestParam("month") String month, @RequestParam("year") Long year, Principal principal){
        model.addAttribute("title", "Dispatch Salary");
        User user = userDao.findUserAccount(principal.getName());
        salaryDAO.nextSalaryInstallments(user.getUserId(), month, year);
        return "redirect:/successful";
    }
}