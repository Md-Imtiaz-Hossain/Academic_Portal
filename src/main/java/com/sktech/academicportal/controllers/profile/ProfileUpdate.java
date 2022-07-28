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

    @PostMapping("/info/{id}")
    public String profileInfo(@PathVariable Integer id,
                              @ModelAttribute("profile") Profile profile,
                              Principal principal) {
        User user = userService.getUserByEmail(principal.getName());
        Profile existingProfile = profile;
        existingProfile.setId(id);
        existingProfile.setUser(user);
        existingProfile.setSkills(profile.getSkills());
        profileService.save(existingProfile);
        return "redirect:/after-login-dashboard";
    }
}
