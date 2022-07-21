package com.sktech.academicportal.controllers.classroutine;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/routine")
public class Routine {

    @GetMapping("/all-class")
    public String home(Model model){
        System.out.println("+++++++++++++++++++++++");

        model.addAttribute("pageTitle", "All classes routine");

        return "/classroutine/routine-all-classes";
    }

    @GetMapping("/class/{classOf}")
    public String home(@PathVariable Integer classOf, Model model){
        System.out.println("+++++++++++++++++++++++");
        System.out.println(classOf);

        model.addAttribute("pageTitle", "Class - " + classOf + "  class routine");

        return "/classroutine/routine-using-class-name";
    }

}
