package com.example.demo.controller;

import com.example.demo.model.Teacher;
import com.example.demo.service.teacher.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminController {

    @Autowired
    TeacherService teacherService;

    @GetMapping("admin/admin_user_account")
    public ModelAndView showFormUserAccount(){
        ModelAndView modelAndView = new ModelAndView("adminAccountStudent");
        return modelAndView;
    }

    @GetMapping("admin/admin_teacher_account")
    public ModelAndView showFormTeacherAccount(){
        ModelAndView modelAndView = new ModelAndView("adminAccountStudent");
        modelAndView.addObject("teacher",new Teacher());
        return modelAndView;
    }
}
