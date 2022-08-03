package com.sktech.academicportal.controllers.studentapplication;

import com.sktech.academicportal.entity.AllApplication;
import com.sktech.academicportal.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/student-application")
public class StudentApplication {

    @Autowired
    ApplicationService applicationService;

    @GetMapping("/send")
    public String doApplication(Model model, Principal principal) {
        model.addAttribute("from", principal.getName());
        model.addAttribute("application", new AllApplication());
        return "studentapplication/application-form";
    }


    @PostMapping("/edit")
    public String edit(@ModelAttribute("application") AllApplication application,
                             @RequestParam(value="action", required=true) String action,
                       Principal principal) {

        LocalDateTime localDateTime = LocalDateTime.now();

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
            application.setApplicationTime(LocalDateTime.now());
            applicationService.saveApplication(application);
        }
        return "redirect:/student-application/send";
    }

    @GetMapping("/application-box")
    public String allApplication(Model model, Principal principal){
        System.out.println("-------------------------------" + LocalDateTime.now());
        System.out.println("${#temporals.format(localDateTime, 'dd-MMMM-yyyy', new java.util.Locale('en', 'EN'))}");


        model.addAttribute("datetime", LocalDateTime.now());
        model.addAttribute("allApplication", applicationService.findAll());
        return  "studentapplication/application-box";
    }

    @GetMapping("/application-details/{applicationId}")
    public String applicationDetails(@PathVariable Integer applicationId,
                                     Model model, Principal principal){

        model.addAttribute("specificApplication", applicationService.findById(applicationId));

        return  "studentapplication/application-details";
    }



}
