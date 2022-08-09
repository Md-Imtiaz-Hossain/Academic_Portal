package com.sktech.academicportal.controllers.subject;

import com.sktech.academicportal.entity.Subject;
import com.sktech.academicportal.entity.User;
import com.sktech.academicportal.service.SubjectService;
import com.sktech.academicportal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("subject-assign-t")
public class SubjectAssignTeacher {

    @Autowired
    UserService userService;

    @Autowired
    SubjectService subjectService;

    // Extra data go through model attribute before all other controller run.
    @ModelAttribute("loggedInUser")
    public User extraData(Principal principal) {
        return userService.getUserByEmail(principal.getName());
    }

    @GetMapping("/assign-teacher")
    public String subjectAssign( Model model ){

        model.addAttribute("pageTitle", "Teacher and Assigned Subjects");
        model.addAttribute("user", userService.getAllUserWithoutRootAdminAndAdminAndStudentRole());

        return "subjectassign/assign-And-list-teacher";
    }



    // Open the Update form for person Information updating
    @GetMapping("/edit/{id}")
    public String editUserForm(@PathVariable Integer id, Model model) {

        List<Subject> subjectList = subjectService.getAllSubject();

        model.addAttribute("pageTitle", "Update Assigned Subject Information");
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("subjectList", subjectList);

        return "subjectassign/subject-Assign-update-form";
    }


    // Process the updated information after update button clicked.
    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable Integer id, @ModelAttribute("user") User user) {

        User existingUser = userService.getUserById(id);
        existingUser.setId(id);
        existingUser.setSubjects(user.getSubjects());

        // save updated student object
        userService.updateUser(existingUser);
        return "redirect:/subject-assign-t/assign-teacher";
    }

}
