package com.sktech.academicportal.controllers.studentapplication;

import com.sktech.academicportal.entity.AllApplication;
import com.sktech.academicportal.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/student-application")
public class StudentApplication {

    @Autowired
    ApplicationService applicationService;

    // Application Home
    @GetMapping("/send")
    public String doApplication(Model model, Principal principal) {
        model.addAttribute("pageTitle", "Applications");
        model.addAttribute("from", principal.getName());
        model.addAttribute("application", new AllApplication());
        return "studentapplication/application-form";
    }


    // Save or Send the application
    @PostMapping("/edit")
    public String edit(@ModelAttribute("application") AllApplication application,
                       @RequestParam(value = "action", required = true) String action,
                       Principal principal) {

        if (action.equals("save")) {
            application.setApplicationStatus("Save");
            System.out.println(action);
            application.setSendFrom(principal.getName());
            application.setApplicationTime(LocalDateTime.now());
            applicationService.saveApplication(application);
        }

        if (action.equals("send")) {
            System.out.println(action);
            application.setSendFrom(principal.getName());
            application.setApplicationStatus("Send");
            application.setApplicationTime(LocalDateTime.now());
            applicationService.saveApplication(application);
        }
        return "redirect:/student-application/send";
    }

    // All Application(Status==Send) For Admin
    @GetMapping("/application-box")
    public String allApplication(Model model, Principal principal) {
        List<AllApplication> allApplications = applicationService.findAllApplicationWithoutSaveStatus();
        model.addAttribute("allApplication", allApplications);
        model.addAttribute("pageTitle", "Admin | All Applications");
        return "studentapplication/application-box";
    }


    //  Application Came in tto Logged-in user
    @GetMapping("/user-application-box")
    public String userApplication(Model model, Principal principal) {
        List<AllApplication> allApplications = applicationService.findAllApplicationReceived(principal, "Send");
        model.addAttribute("allApplication", allApplications);
        model.addAttribute("pageTitle", "User | All Applications");
        return "studentapplication/application-box";
    }

    // Application Details
    @GetMapping("/application-details/{applicationId}")
    public String applicationDetails(@PathVariable Integer applicationId,
                                     Model model, Principal principal) {
        model.addAttribute("specificApplication", applicationService.findById(applicationId));
        model.addAttribute("pageTitle", "Application details");
        return "studentapplication/application-details";
    }


    // All send application from logged-in user
    @GetMapping("/all-send-application")
    public String allSendApplication(Model model, Principal principal) {
        List<AllApplication> allApplications = applicationService.findAllApplicationWithSendFromMe(principal, "Send");
        model.addAttribute("allApplication", allApplications);
        model.addAttribute("pageTitle", "Application Send");
        return "studentapplication/application-box";
    }

    // Delete Application
    @GetMapping("/delete/{applicationId}")
    public String deleteApplication(@PathVariable Integer applicationId) {
        applicationService.deleteApplicationById(applicationId);
        return "redirect:/student-application/send";
    }


}
