package com.sktech.academicportal.controllers.studentportal;

import com.sktech.academicportal.entity.PaymentInformation;
import com.sktech.academicportal.entity.StudentResult;
import com.sktech.academicportal.entity.User;
import com.sktech.academicportal.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/student-portal")
public class StudentPortal {

    @Autowired
    UserService userService;

    @Autowired
    RoutineService routineService;

    @Autowired
    SubjectService subjectService;

    @Autowired
    ResultService resultService;

    @Autowired
    PaymentSchemeService paymentSchemeService;

    // Extra data go through model attribute before all other controller run.
    @ModelAttribute("loggedInUser")
    public User extraData(Principal principal) {
        return userService.getUserByEmail(principal.getName());
    }


    // Logged in Student all subject class routine
    @GetMapping("/my-routine")
    public String myRoutine(Model model, Principal principal) {
        String userEmail = principal.getName();
        User user = userService.getUserByEmail(userEmail);
        String loggedUserClass = user.getCurrentClass();

        model.addAttribute("pageTitle", "My class routine");
        model.addAttribute("routineList", routineService.getRoutineByClass(loggedUserClass));
        model.addAttribute("className", loggedUserClass);

        return "studentportal/my-routine";
    }

    // Logged in Student all subject result
    @GetMapping("/my-result")
    public String myResult(Model model, Principal principal) {

        String userEmail = principal.getName();
        User user = userService.getUserByEmail(userEmail);

        Set<StudentResult> results = new HashSet<>();
        List<StudentResult> resultList = resultService.getAllResul();
        System.out.println(resultList);
        for (StudentResult studentResult : resultList) {
            if (studentResult.getUserId().equals(user.getId())) {
                results.add(studentResult);
            }
        }

        model.addAttribute("pageTitle", "Mark List with Username and Subject name");
        model.addAttribute("mark", results);

        return "studentportal/my-result";
    }


    // Logged in Student all subject class routine
    @GetMapping("/my-payment")
    public String myPayment(@ModelAttribute PaymentInformation paymentInformation, Model model, Principal principal) {
        String userEmail = principal.getName();
        User user = userService.getUserByEmail(userEmail);
        String loggedUserClass = user.getCurrentClass();

        model.addAttribute("pageTitle", "My Scheme list");
        model.addAttribute("schemeList", paymentSchemeService.getSchemeByClass(loggedUserClass));
        model.addAttribute("schemeTotal", paymentSchemeService.getSchemeTotal(loggedUserClass));
        model.addAttribute("className", loggedUserClass);
        model.addAttribute("paymentInformation", paymentInformation);


        return "studentportal/my-payment";
    }



}
