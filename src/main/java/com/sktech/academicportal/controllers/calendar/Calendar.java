package com.sktech.academicportal.controllers.calendar;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/calendar")
public class Calendar {


    @GetMapping("/view")
    public String calendar(Model model) {
        model.addAttribute("pageTitle", "Calendar");
        return "pages/calendar";
    }


}
