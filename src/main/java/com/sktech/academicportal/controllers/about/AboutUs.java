package com.sktech.academicportal.controllers.about;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/about")
public class AboutUs {

    @GetMapping("/us")
    public String aboutUs(){
        return "about";
    }

    @GetMapping("/dummy-user")
    public String dummyUser(){
        return "user";
    }
}
