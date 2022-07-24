package com.sktech.academicportal.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class MainController {


    @GetMapping("/view")
    public String viewHomePage(Model model) {
        model.addAttribute("pageTitle", "Homepage");
        return "home-page";
    }

    @GetMapping("/login")
    public String viewLoginPage(Model model) {
        model.addAttribute("pageTitle", "Log in here");
        return "login";
    }


    @GetMapping("/after-login-dashboard")
    public String afterLogin(Model model) {
        model.addAttribute("pageTitle", "Welcome | Dashboard");
        return "after-login-dashboard";
    }



}
