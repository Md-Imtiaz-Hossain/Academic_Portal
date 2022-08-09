package com.sktech.academicportal.controllers.profile;

import com.sktech.academicportal.entity.Profile;
import com.sktech.academicportal.entity.User;
import com.sktech.academicportal.service.ProfileService;
import com.sktech.academicportal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/profile")
public class ProfileUpdate {

    @Autowired
    UserService userService;

    @Autowired
    ProfileService profileService;


    // Extra data go through model attribute before all other controller run.
    @ModelAttribute("loggedInUser")
    public User extraData(Principal principal) {
        return userService.getUserByEmail(principal.getName());
    }


    @GetMapping("/update")
    public String profileUpdate(Model model) {
        model.addAttribute("pageTitle", "Profile Update");
        model.addAttribute("profile", new Profile());
        return "profileupdate/update";
    }


    @PostMapping("/save")
    public String profileInfo(@ModelAttribute("profile") Profile profile,
                              Principal principal) {
        User user = userService.getUserByEmail(principal.getName());
        profile.setUser(user);
        profileService.save(profile);
        return "redirect:/after-login-dashboard";
    }


}
