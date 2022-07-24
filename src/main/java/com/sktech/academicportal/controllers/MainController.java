package com.sktech.academicportal.controllers;

import com.sktech.academicportal.controllers.allusers.LoggedInUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class MainController {

    @Autowired
    LoggedInUser loggedInUser;

    @GetMapping("/")
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
    public String afterLogin(Model model, Principal principal) {
//        loggedInUser.commonDate(model, principal);
        model.addAttribute("pageTitle", "Welcome | Dashboard");
        return "after-login-dashboard";
    }

    @GetMapping("/calendar")
    public String calendar(Model model) {
        model.addAttribute("pageTitle", "Calendar");
        return "/pages/calendar";
    }



}
