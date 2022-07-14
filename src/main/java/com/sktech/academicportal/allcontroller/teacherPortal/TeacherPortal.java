package com.sktech.academicportal.allcontroller.teacherPortal;

import com.sktech.academicportal.entity.Subject;
import com.sktech.academicportal.entity.User;
import com.sktech.academicportal.service.UserRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/teacher-portal")
public class TeacherPortal {

    @Autowired
    UserRepositoryService userRepositoryService;

    @GetMapping("/mySubject")
    public String teacherPortalHome(Model model, Principal principal){

        String principalName = principal.getName();
        List<Subject> allAssignedSubjectToATeacher = userRepositoryService.getAllAssignedSubjectToATeacher(principalName);
        System.out.println("=====================================");
        System.out.println(allAssignedSubjectToATeacher);
        System.out.println("=====================================");

        model.addAttribute("principalName", principalName);
        model.addAttribute("user", userRepositoryService.getAllUser());
        model.addAttribute("allAssignedSubjectToATeacher", allAssignedSubjectToATeacher);
        return "TeacherPortal/taking-subject-of-mine";
    }


    // Open the Update form for Student Result Information
    @GetMapping("/edit/{id}")
    public String updateResultForm(@PathVariable Integer id, Model model) {

//        List<Subject> subjectList = subjectRepositoryService.getAllSubject();
//
//        model.addAttribute("user", userRepositoryService.getUserById(id));
//        model.addAttribute("subjectList", subjectList);

        return "/SubjectAssign/subject-Assign-update-form";
    }


    // Process the updated information after update button clicked.
    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable Integer id, @ModelAttribute("user") User user) {

        User existingUser = userRepositoryService.getUserById(id);
        existingUser.setId(id);
        existingUser.setSubjects(user.getSubjects());

        // save updated student object
        userRepositoryService.updateUser(existingUser);
        return "redirect:/subject-assign/assign";
    }


}
