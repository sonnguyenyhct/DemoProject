package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String index(){
        return "home";
    }
    @GetMapping("/student")
    public String homeStudent(){
        return "homeStudent";
    }
    @GetMapping("/teacher")
    public String homeTeacher(){
        return "homeTeacher";
    }
    @GetMapping("/admin")
    public String homeAdmin(){
        return "homeAdmin";
    }
    @GetMapping("/page403")
    public String noRedirect(){
        return "noredirect";
    }
}
