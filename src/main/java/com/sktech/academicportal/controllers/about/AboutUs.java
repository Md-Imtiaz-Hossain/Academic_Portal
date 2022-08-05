package com.sktech.academicportal.controllers.about;

import com.sktech.academicportal.entity.User;
import com.sktech.academicportal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/about")
public class AboutUs {

    @Autowired
    UserService userService;


    @GetMapping("/us")
    public String aboutUs(){
        return "about";
    }
}
