package com.sktech.academicportal.allcontroller.subject;

import com.sktech.academicportal.entity.Role;
import com.sktech.academicportal.entity.Subject;
import com.sktech.academicportal.entity.User;
import com.sktech.academicportal.enums.AcademicSection;
import com.sktech.academicportal.service.SubjectRepositoryService;
import com.sktech.academicportal.service.UserRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("subject-assign")
public class SubjectAssign {

    @Autowired
    UserRepositoryService userRepositoryService;

    @Autowired
    SubjectRepositoryService subjectRepositoryService;

    @GetMapping("/assign")
    public String subjectAssign( Model model ){

        model.addAttribute("user", userRepositoryService.getAllUserWithoutStudentRole());

        return "/SubjectAssign/assign-And-list";
    }



    // Open the Update form for person Information updating
    @GetMapping("/edit/{id}")
    public String editUserForm(@PathVariable Integer id, Model model) {

        List<Subject> subjectList = subjectRepositoryService.getAllSubject();

        model.addAttribute("user", userRepositoryService.getUserById(id));
        model.addAttribute("subjectList", subjectList);

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
