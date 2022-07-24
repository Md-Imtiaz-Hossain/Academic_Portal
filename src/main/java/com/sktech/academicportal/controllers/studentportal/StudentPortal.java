package com.sktech.academicportal.controllers.studentportal;

import com.sktech.academicportal.entity.User;
import com.sktech.academicportal.service.RoutineService;
import com.sktech.academicportal.service.SubjectService;
import com.sktech.academicportal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/student-portal")
public class StudentPortal {

    @Autowired
    UserService userService;

    @Autowired
    RoutineService routineService;

    @Autowired
    SubjectService subjectService;


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

}
