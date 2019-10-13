package com.herokuapp.schoolmvc.controller;

import com.herokuapp.schoolmvc.dao.ClassDAO;
import com.herokuapp.schoolmvc.dao.CourseDAO;
import com.herokuapp.schoolmvc.dao.TeacherDAO;
import com.herokuapp.schoolmvc.form.CourseForm;
import com.herokuapp.schoolmvc.model.Course;
import com.herokuapp.schoolmvc.model.Teacher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CourseController {

    @Autowired
    private ClassDAO classDao; 

    @Autowired
    private CourseDAO courseDao; 

    @Autowired
    private TeacherDAO teacherDao;

    @RequestMapping(value = "/courses")
    public String viewCourses(Model model){
        model.addAttribute("courses", courseDao.listAllCourses());
        return "courses";
    }


    @RequestMapping(value = "/courses/{level}")
    public String viewCoursesByClass(Model model, @PathVariable Long level){
        model.addAttribute("courses", courseDao.listCoursesByClass(level));
        model.addAttribute("label", "Class "+level);
        return "courses";
    }

    @RequestMapping(value = "/teacher/{id}/courses")
    public String viewCoursesByTeacher(Model model, @PathVariable Long id){
        Teacher teacher = teacherDao.findTeacherById(id);
        model.addAttribute("courses", courseDao.listCoursesByTeacherId(id));
        model.addAttribute("label", "Taught By  "+teacher.getName());
        return "courses";
    }

    @RequestMapping(value = "/course/new", method = RequestMethod.GET)
    public String newCourse(Model model){
        model.addAttribute("courseForm", new CourseForm());
        model.addAttribute("classes", classDao.listAllClasses());
        model.addAttribute("teachers", teacherDao.listAllTeachers());
        return "new-course";
    }

    @RequestMapping(value = "/course/new", method = RequestMethod.POST)
    public String newCourseSubmit(
        Model model,
        @ModelAttribute("courseForm") CourseForm courseForm
    ){
        courseDao.createCourse(courseForm);
        return "redirect:/courses";
    }

    @RequestMapping(value = "/course/{id}/manage", method = RequestMethod.GET)
    public String manageCourse(
        Model model,
        @PathVariable Long id
    ){
        Course course = courseDao.findCourseById(id);
        CourseForm courseForm = new CourseForm(course.getName(), course.get_class().getLevel(), course.getTeacher().getTeacherId());
        model.addAttribute("courseForm", courseForm);
        model.addAttribute("classes", classDao.listAllClasses());
        model.addAttribute("teachers", teacherDao.listAllTeachers());
        return "manage-course";
    }
}