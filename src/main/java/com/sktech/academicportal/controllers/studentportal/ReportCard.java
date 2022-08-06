package com.sktech.academicportal.controllers.studentportal;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/report-card")
public class ReportCard {

    @GetMapping("/view")
    public String cardHome(Model model, Principal principal){
        return "/studentportal/student-report-card";
    }

}
