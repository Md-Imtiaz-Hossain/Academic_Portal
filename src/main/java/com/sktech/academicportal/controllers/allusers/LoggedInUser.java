package com.sktech.academicportal.controllers.allusers;

import com.sktech.academicportal.entity.User;
import com.sktech.academicportal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("looged-in-user")
public class LoggedInUser {

    @Autowired
    UserService userService;

    // Logged In User Name
    @GetMapping("/info")
    public String commonDate(Model model, Principal principal){
        String userEmail = principal.getName();
        User user = userService.getUserByEmail(userEmail);
        model.addAttribute("userPhoto", user.getPhotos());
        model.addAttribute("principleUser", user);
        return "fragments-list";
    }

}
