package com.sktech.academicportal.controllers;

import com.sktech.academicportal.entity.User;
import com.sktech.academicportal.repositories.RoleRepository;
import com.sktech.academicportal.service.ProfileService;
import com.sktech.academicportal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.annotation.PostConstruct;
import java.security.Principal;

@Controller
public class MainController {

    @Autowired
    UserService userService;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    ProfileService profileService;


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
    public String afterLogin(Model model, Principal  principal) {
        model.addAttribute("pageTitle", "Welcome | Dashboard");
        model.addAttribute("user", userService.getUserByEmail(principal.getName()));
        return "after-login-dashboard";
    }


}
