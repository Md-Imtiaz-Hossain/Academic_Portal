package com.sktech.academicportal;

import com.sktech.academicportal.service.HomepageRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @Autowired
    HomepageRepositoryService hrs;

    @GetMapping("/")
    public String viewHomePage(Model model) {
        if(hrs.getAll().isEmpty()) return "redirect:/homepage/init";
        model.addAttribute("sections", hrs.getAll());
        return "index";
    }

    @GetMapping("/login")
    public String viewLoginPage() {
        return "login";
    }

    @GetMapping("/after-login-dashboard")
    public String afterLogin() {
        return "/after-login-dashboard";
    }

}
