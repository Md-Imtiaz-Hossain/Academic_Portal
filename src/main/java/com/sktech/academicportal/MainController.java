package com.sktech.academicportal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/home")
    public String viewHomePage() {
        return "home-page";
        //return "starter-page";
    }


    @GetMapping("/after-login-dashboard")
    public String afterLogin() {
        return "/after-login-dashboard";
    }



}
