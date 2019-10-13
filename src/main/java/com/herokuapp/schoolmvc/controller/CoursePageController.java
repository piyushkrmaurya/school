package com.herokuapp.schoolmvc.controller;

import java.util.List;

import com.herokuapp.schoolmvc.dao.ClassDAO;
import com.herokuapp.schoolmvc.dao.CourseDAO;
import com.herokuapp.schoolmvc.dao.CoursePageDAO;
import com.herokuapp.schoolmvc.dao.EnrollmentDAO;
import com.herokuapp.schoolmvc.dao.NotificationDAO;
import com.herokuapp.schoolmvc.dao.ResultDAO;
import com.herokuapp.schoolmvc.dao.TeacherDAO;
import com.herokuapp.schoolmvc.form.CourseForm;
import com.herokuapp.schoolmvc.model.Course;
import com.herokuapp.schoolmvc.model.CoursePage;
import com.herokuapp.schoolmvc.model.Enrollment;
import com.herokuapp.schoolmvc.model.Grade;
import com.herokuapp.schoolmvc.form.NotificationForm;
import com.herokuapp.schoolmvc.form.ResultForm;
import com.herokuapp.schoolmvc.form.SingleResult;
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
    private CoursePageDAO coursePageDao; 

    @Autowired
    private ResultDAO resultDao; 

    @Autowired
    private NotificationDAO notificationDao; 

    @Autowired
    private EnrollmentDAO enrollmentDao; 

    @RequestMapping(value = "/course-page/{id}")
    public String viewCourses(Model model, @PathVariable Long id) {
        CoursePage coursePage = coursePageDao.findLastestCoursePageByCourseId(id);
        model.addAttribute("coursePage", coursePage);
        model.addAttribute("notifications", notificationDao.listNotificationsByCpId(coursePage.getCpId()));
        model.addAttribute("notificationForm", new NotificationForm());
        return "course-page";
    }

    @RequestMapping(value = "/notification/new")
    public String viewCourses(Model model, @ModelAttribute NotificationForm notificationForm) {
        java.util.Date today = new java.util.Date();
        notificationForm.setDate(new java.sql.Date(today.getTime()));
        notificationDao.newNotification(notificationForm);
        return "redirect:/course-page/"+notificationForm.getCourseId();
    }

    @RequestMapping(value = "/declare-result/{courseId}", method = RequestMethod.GET)
    public String declareResultView(Model model, @PathVariable Long courseId){
        List<Enrollment> enrollments = enrollmentDao.listCurrentEnrollmentsByCourseId(courseId);
        model.addAttribute("enrollments", enrollments);
        ResultForm resultForm = new ResultForm();
        for(Enrollment enrollment: enrollments){
            resultForm.addResult(enrollment.getEnrollId());
        }
        model.addAttribute("resultForm", resultForm);
        model.addAttribute("grades", Grade.values());
        CoursePage coursePage = coursePageDao.findLastestCoursePageByCourseId(courseId);
        model.addAttribute("coursePage", coursePage);
        return "declare-result";
    }

    @RequestMapping(value = "/declare-result/{courseId}", method = RequestMethod.POST)
    public String declareResult(Model model, @PathVariable Long courseId,  @ModelAttribute ResultForm resultForm){
        resultDao.createResultForCourse(courseId, resultForm);
        return "redirect:/successful";
    }
}