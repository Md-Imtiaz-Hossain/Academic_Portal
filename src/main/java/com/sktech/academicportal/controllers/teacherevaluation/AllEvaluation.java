package com.sktech.academicportal.controllers.teacherevaluation;


import com.sktech.academicportal.entity.Subject;
import com.sktech.academicportal.entity.TeacherEvaluation;
import com.sktech.academicportal.entity.User;
import com.sktech.academicportal.service.SubjectService;
import com.sktech.academicportal.service.TeacherEvaluationService;
import com.sktech.academicportal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
@ComponentScan
@RequestMapping("/all-teacher-evaluation")
public class AllEvaluation {


    @Autowired
    UserService userService;

    @Autowired
    SubjectService subjectService;

    @Autowired
    TeacherEvaluationService teacherEvaluationService;

    @GetMapping("/list")
    public String evaluationList(Model model, Principal principal) {
        List<TeacherEvaluation> evaluation = teacherEvaluationService.findAll();
        model.addAttribute("pageTitle", "Teacher evaluation List");
        model.addAttribute("allReview", evaluation);
        return "teacherevaluation/all-evaluation-list";
    }


}
