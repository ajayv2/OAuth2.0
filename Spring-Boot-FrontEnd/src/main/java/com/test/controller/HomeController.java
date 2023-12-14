package com.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/employee")
    public String addEmployee(){
        return "employee";
    }
    @GetMapping("/department")
    public String addDepartment(){
        return "department";
    }
    @GetMapping("/organization")
    public String addOrganization(){
        return "organization";
    }

    @GetMapping("/allemployee")
    public String getAllEmployee(){
        return "home";
    }

    @GetMapping("/home")
    public String home(){
        return "indexpage";
    }

    @GetMapping("/alldepartment")
    public String getAllDepartment(){
        return "alldepartment";
    }
    @GetMapping("/allorganization")
    public String getAllOrganization(){
        return "allorganization";
    }
}
