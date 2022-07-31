package com.sktech.academicportal.controllers.teacherevaluation;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@ComponentScan
@RequestMapping("/teacher-evaluation")
public class Evaluation {

    @GetMapping("/form")
    public String formView(){
        return "teacherevaluation/evaluation-form";
    }
}
