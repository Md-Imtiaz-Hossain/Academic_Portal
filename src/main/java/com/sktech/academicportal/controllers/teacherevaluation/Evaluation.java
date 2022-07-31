package com.sktech.academicportal.controllers.teacherevaluation;


import com.sktech.academicportal.entity.StudentResult;
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

@Controller
@ComponentScan
@RequestMapping("/teacher-evaluation")
public class Evaluation {


    @Autowired
    UserService userService;

    @Autowired
    SubjectService subjectService;

    @Autowired
    TeacherEvaluationService teacherEvaluationService;

    @GetMapping("/list")
    public String evaluationList(Model model, Principal principal) {
        model.addAttribute("pageTitle", "Teacher evaluation List");
//        model.addAttribute("allReview", teacherEvaluationService.findAll());
        model.addAttribute("allReview", teacherEvaluationService.findAllReviewUsingPrincipal(principal));
        return "teacherevaluation/evaluation-list";
    }

    @GetMapping("/form")
    public String formView(Model model, Principal principal) {
        model.addAttribute("pageTitle", "Teacher evaluation");
        model.addAttribute("user", userService.getStudentByLoggedInformation(principal.getName()));
        return "teacherevaluation/evaluation-form";
    }

    @GetMapping("/add-review/{studentId}/{subjectId}")
    public String addReview(@PathVariable Integer studentId,
                            @PathVariable Integer subjectId,
                            Principal principal, Model model) {

        Subject subject = subjectService.getSubjectById(subjectId);
//        User student = userService.getUserById(studentId);
        User teacher = userService.getUserBySubjectId(subjectId);

        model.addAttribute("pageTitle", "Teacher evaluation");
        model.addAttribute("student", userService.getUserById(studentId));
        model.addAttribute("studentId", studentId);
        model.addAttribute("subject", subject);
        model.addAttribute("subjectId", subjectId);
        model.addAttribute("teacher", teacher);
        model.addAttribute("teacherName", teacher.getFullName());
        model.addAttribute("teacherEvaluation", new TeacherEvaluation());
        return "teacherevaluation/add-review";
    }


    @PostMapping("/save/{subjectId}/{studentId}/{teacherId}")
    public String processForm(@PathVariable Integer subjectId,
                              @PathVariable Integer studentId,
                              @PathVariable Integer teacherId,
                              @ModelAttribute("teacherEvaluation") TeacherEvaluation teacherEvaluation) {
        System.out.println("+++++++++++++++++++++++++++++++++++++");
        System.out.println(teacherEvaluation);
        teacherEvaluation.setReviewerId(studentId);
        teacherEvaluation.setTeacherId(teacherId);
        teacherEvaluation.setSubjectId(subjectId);
        System.out.println(subjectId + "," + studentId + "," + teacherId);
        teacherEvaluationService.saveEvaluation(teacherEvaluation);
        return "redirect:/teacher-evaluation/form";
    }


}
