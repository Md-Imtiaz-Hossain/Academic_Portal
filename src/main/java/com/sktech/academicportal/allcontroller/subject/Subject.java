package com.sktech.academicportal.allcontroller.subject;

import com.sktech.academicportal.service.SubjectRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/subject")
public class Subject {

    @Autowired
    SubjectRepositoryService subjectRepositoryService;

    @GetMapping("/list")
    public String home(Model model){
        model.addAttribute("subjectList", subjectRepositoryService.getAllSubject());
        return "Subject/subject-list";
    }

}
