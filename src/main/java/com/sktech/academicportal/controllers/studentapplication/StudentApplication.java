package com.sktech.academicportal.controllers.studentapplication;

import com.sktech.academicportal.entity.Application;
import com.sktech.academicportal.service.ApplicationService;
import jdk.jfr.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
@RequestMapping("/student-application")
public class StudentApplication {

    @Autowired
    ApplicationService applicationService;

    @GetMapping("/send")
    public String doApplication(Model model, Principal principal) {
        model.addAttribute("from", principal.getName());
        model.addAttribute("application", new Application());
        return "studentapplication/application-form";
    }


    @PostMapping("/edit")
    public String edit(@ModelAttribute("application") Application application,
                             @RequestParam(value="action", required=true) String action,
                       Principal principal) {

        if (action.equals("save")) {
            application.setApplicationStatus("Save");
            System.out.println(action);
            application.setSendFrom(principal.getName());
            applicationService.saveApplication(application);
        }

        if (action.equals("send")) {
            System.out.println(action);
            application.setSendFrom(principal.getName());
            application.setApplicationStatus("Send");
            applicationService.saveApplication(application);
        }
        return "redirect:/student-application/send";
    }

}
