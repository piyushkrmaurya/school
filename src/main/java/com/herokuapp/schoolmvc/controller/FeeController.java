package com.herokuapp.schoolmvc.controller;

import java.security.Principal;

import com.herokuapp.schoolmvc.dao.FeeDAO;
import com.herokuapp.schoolmvc.dao.StudentDAO;
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
public class FeeController {

    @Autowired
    private FeeDAO feesDAO; 

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private StudentDAO studentDAO;

    @RequestMapping(value = "/fees")
    public String fees(Model model){
        model.addAttribute("title", "Fee payments");
        model.addAttribute("fees", feesDAO.listFeePayments());
        return "fees";
    }

    @RequestMapping(value = "/my-fees")
    public String myFees(Model model, Principal principal) {
        model.addAttribute("title", "My fee payments");
        User user = userDAO.findUserAccount(principal.getName());
        model.addAttribute("fees", feesDAO.listFeePaymentsByStudntId(user.getUserId()));
        return "my-fees";
    }

    @RequestMapping(value = "/receive-fee", method = RequestMethod.GET)
    public String receivePaymentView(Model model){
        model.addAttribute("title", "New fee payment");
        model.addAttribute("students", studentDAO.listStudents());
        model.addAttribute("months", Month.values()); 
        return "receive-fee";
    }

    @RequestMapping(value = "/receive-fee", method = RequestMethod.POST)
    public String receivePayment(Model model, @RequestParam("studentId") Long studentId, @RequestParam("month") String month, @RequestParam("year") Long year, Principal principal){
        User user = userDAO.findUserAccount(principal.getName());
        Long amount = studentDAO.getCurrentFee(studentId);
        feesDAO.newFeePayment(studentId, user.getUserId(), amount, month, year);
        return "redirect:/successful";
    }
}