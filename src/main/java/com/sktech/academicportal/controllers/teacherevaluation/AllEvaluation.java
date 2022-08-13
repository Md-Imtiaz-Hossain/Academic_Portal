package com.sktech.academicportal.controllers.teacherevaluation;


import com.sktech.academicportal.entity.TeacherEvaluation;
import com.sktech.academicportal.entity.User;
import com.sktech.academicportal.service.SubjectService;
import com.sktech.academicportal.service.TeacherEvaluationService;
import com.sktech.academicportal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

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

    // Extra data go through model attribute before all other controller run.
    @ModelAttribute("loggedInUser")
    public User extraData(Principal principal) {
        return userService.getUserByEmail(principal.getName());
    }

    @GetMapping("/list")
    public String evaluationList(Model model, Principal principal) {
        List<TeacherEvaluation> evaluation = teacherEvaluationService.findAll();
        model.addAttribute("pageTitle", "Teacher evaluation List");
        model.addAttribute("allReview", evaluation);
        return "teacherevaluation/all-evaluation-list";
    }

}
