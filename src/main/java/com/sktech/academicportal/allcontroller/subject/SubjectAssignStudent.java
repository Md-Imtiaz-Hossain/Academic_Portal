package com.sktech.academicportal.allcontroller.subject;

import com.sktech.academicportal.entity.Subject;
import com.sktech.academicportal.entity.User;
import com.sktech.academicportal.service.SubjectRepositoryService;
import com.sktech.academicportal.service.UserRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("subject-assign-s")
public class SubjectAssignStudent {

    @Autowired
    UserRepositoryService userRepositoryService;

    @Autowired
    SubjectRepositoryService subjectRepositoryService;

    @GetMapping("/assignStudent")
    public String subjectAssign( Model model ){

        model.addAttribute("user", userRepositoryService.getAllUserByStudentRole());

        return "/SubjectAssign/assign-And-list-student";
    }



    // Open the Update form for person Information updating
    @GetMapping("/edit/{id}")
    public String editUserForm(@PathVariable Integer id, Model model) {

        User user = userRepositoryService.getUserById(id);
        String currentClass = user.getCurrentClass();
        List<Subject> allSubjectByClass = subjectRepositoryService.getAllSubjectByClass(currentClass);

        model.addAttribute("user", user);
        model.addAttribute("subjectList", allSubjectByClass);

        return "/SubjectAssign/subject-Assign-update-form-student";
    }


    // Process the updated information after update button clicked.
    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable Integer id, @ModelAttribute("user") User user) {

        User existingUser = userRepositoryService.getUserById(id);
        existingUser.setId(id);
        existingUser.setSubjects(user.getSubjects());

        // save updated student object
        userRepositoryService.updateUser(existingUser);
        return "redirect:/subject-assign-s/assignStudent";
    }

}
