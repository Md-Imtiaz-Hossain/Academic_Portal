package com.sktech.academicportal;

import com.sktech.academicportal.service.HomepageRepositoryService;
import com.sktech.academicportal.service.PublicFilesRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @Autowired
    HomepageRepositoryService hrs;
    @Autowired
    PublicFilesRepositoryService publicFilesRepositoryService;
    

    @GetMapping("/")
    public String viewHomePage(Model model) {
//        if(hrs.getAll().isEmpty()) return "redirect:/homepage/init";
//        model.addAttribute("sections", hrs.getAll());
        return "index";
    }

    @GetMapping("/login")
    public String viewLoginPage() {
        return "login";
    }

    @GetMapping("/after-login-dashboard")
    public String afterLogin(Model model) {
        return "/after-login-dashboard";
    }

    @GetMapping("/edit/carousel")
    public String carouselEdit(Model model){
        return "carouselEditor";
    }



}
