package com.herokuapp.schoolmvc.controller;

import com.herokuapp.schoolmvc.dao.ClassDAO;
import com.herokuapp.schoolmvc.dao.CourseDAO;
import com.herokuapp.schoolmvc.dao.CoursePageDAO;
import com.herokuapp.schoolmvc.dao.NotificationDAO;
import com.herokuapp.schoolmvc.dao.TeacherDAO;
import com.herokuapp.schoolmvc.form.CourseForm;
import com.herokuapp.schoolmvc.model.Course;
import com.herokuapp.schoolmvc.model.CoursePage;
import com.herokuapp.schoolmvc.form.NotificationForm;
import com.herokuapp.schoolmvc.model.Teacher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CoursePageController {

    @Autowired
    private ClassDAO classDao; 

    @Autowired
    private CoursePageDAO coursePageDao; 

    @Autowired
    private CourseDAO courseDao; 

    @Autowired
    private TeacherDAO teacherDao; 


    @Autowired
    private NotificationDAO notificationDao; 

    @RequestMapping(value = "/course-page/{id}")
    public String viewCourses(Model model, @PathVariable Long id){
        CoursePage coursePage = coursePageDao.findLastestCoursePageByCourseId(id);
        model.addAttribute("coursePage", coursePage);
        model.addAttribute("notifications", notificationDao.listNotificationsByCpId(coursePage.getCpId()));
        model.addAttribute("notificationForm", new NotificationForm());
        return "course-page";
    }

    @RequestMapping(value = "/notification/new")
    public String viewCourses(Model model, @ModelAttribute NotificationForm notificationForm){
        java.util.Date today = new java.util.Date();
        notificationForm.setDate(new java.sql.Date(today.getTime()));
        notificationDao.newNotification(notificationForm);
        return "redirect:/course-page/"+notificationForm.getCourseId();
    }
}