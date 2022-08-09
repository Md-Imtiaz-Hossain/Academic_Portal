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
import java.util.Set;

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


    // Extra data go through model attribute before all other controller run.
    @ModelAttribute("loggedInUser")
    public User extraData(Principal principal) {
        return userService.getUserByEmail(principal.getName());
    }

    @GetMapping("/list")
    public String evaluationList(Model model, Principal principal) {

        Set<TeacherEvaluation> evaluation = teacherEvaluationService.findAllReviewUsingPrincipal(principal);
        Integer reviewerId = null, subjectId = null, teacherId = null;
        for (TeacherEvaluation teacherEvaluation : evaluation) {
            reviewerId = teacherEvaluation.getReviewerId();
            subjectId = teacherEvaluation.getSubjectId();
            teacherId = teacherEvaluation.getTeacherId();
        }
        String reviewer = userService.getUserById(reviewerId).getFullName();
        String subject = subjectService.getSubjectById(subjectId).getSubjectName();
        String teacher = userService.getUserById(teacherId).getFullName();

        model.addAttribute("pageTitle", "Teacher evaluation List");
        model.addAttribute("reviewer", reviewer);
        model.addAttribute("subject", subject);
        model.addAttribute("teacher", teacher);
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

        teacherEvaluation.setReviewerId(studentId);
        teacherEvaluation.setTeacherId(teacherId);
        teacherEvaluation.setSubjectId(subjectId);
        teacherEvaluationService.saveEvaluation(teacherEvaluation);
        return "redirect:/teacher-evaluation/form";
    }


    @GetMapping("/edit/{reviewId}")
    public String editReview(@PathVariable Integer reviewId,
                             Principal principal, Model model) {

        TeacherEvaluation evaluation = teacherEvaluationService.findById(reviewId);
        Integer studentId = evaluation.getReviewerId();
        Integer subjectId = evaluation.getSubjectId();
        Integer teacherId = evaluation.getTeacherId();

        Subject subject = subjectService.getSubjectById(subjectId);
        User teacher = userService.getUserById(teacherId);

        model.addAttribute("pageTitle", "Teacher evaluation Update");
        model.addAttribute("student", userService.getUserById(studentId));
        model.addAttribute("studentId", studentId);
        model.addAttribute("subject", subject);
        model.addAttribute("subjectId", subjectId);
        model.addAttribute("teacher", teacher);
        model.addAttribute("teacherName", teacher.getFullName());
        model.addAttribute("teacherEvaluation", evaluation);
        return "teacherevaluation/update-review";
    }

    @PostMapping("/process-update/{evaluationId}/{subjectId}/{studentId}/{teacherId}")
    public String processUpdateForm(@PathVariable Integer evaluationId,
                                    @PathVariable Integer subjectId,
                                    @PathVariable Integer studentId,
                                    @PathVariable Integer teacherId,
                                    @ModelAttribute("teacherEvaluation") TeacherEvaluation teacherEvaluation) {

        TeacherEvaluation existingEvaluation = teacherEvaluationService.findById(evaluationId);
        existingEvaluation.setId(evaluationId);
        existingEvaluation.setReviewerId(studentId);
        existingEvaluation.setTeacherId(teacherId);
        existingEvaluation.setSubjectId(subjectId);

        existingEvaluation.setQuestionOne(teacherEvaluation.getQuestionOne());
        existingEvaluation.setQuestionTwo(teacherEvaluation.getQuestionTwo());
        existingEvaluation.setQuestionThree(teacherEvaluation.getQuestionThree());
        existingEvaluation.setQuestionFour(teacherEvaluation.getQuestionFour());
        existingEvaluation.setQuestionFive(teacherEvaluation.getQuestionFive());
        existingEvaluation.setQuestionSix(teacherEvaluation.getQuestionSix());
        existingEvaluation.setQuestionSeven(teacherEvaluation.getQuestionSeven());
        existingEvaluation.setQuestionEight(teacherEvaluation.getQuestionEight());
        existingEvaluation.setQuestionNine(teacherEvaluation.getQuestionNine());
        existingEvaluation.setQuestionTen(teacherEvaluation.getQuestionTen());
        existingEvaluation.setComment(teacherEvaluation.getComment());

        teacherEvaluationService.saveEvaluation(teacherEvaluation);
        return "redirect:/teacher-evaluation/list";
    }


    @GetMapping("/delete/{evaluationId}")
    public String deleteEvaluation(@PathVariable Integer evaluationId) {
        teacherEvaluationService.deleteEvaluation(evaluationId);
        return "redirect:/teacher-evaluation/list";
    }


}
