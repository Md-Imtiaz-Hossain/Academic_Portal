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
@RequestMapping("subject-assign-s")
public class SubjectAssignStudent {

    @Autowired
    UserService userService;
    @Autowired
    SubjectService subjectService;

    // Extra data go through model attribute before all other controller run.
    @ModelAttribute("loggedInUser")
    public User extraData(Principal principal) {
        return userService.getUserByEmail(principal.getName());
    }

    // List of Student and assigned subject to them.
    @GetMapping("/assign-student")
    public String subjectAssign(Model model) {
        model.addAttribute("pageTitle", "Student and Assigned Subjects");
        model.addAttribute("user", userService.getAllUserByStudentRole());
        return "subjectassign/assign-And-list-student";
    }

    // Open the Update form for assign subject
    @GetMapping("/edit/{id}")
    public String updateForm(@PathVariable Integer id, Model model) {
        User user = userService.getUserById(id);
        String currentClass = user.getCurrentClass();
        List<Subject> allSubjectByClass = subjectService.getAllSubjectByClass(currentClass);
        model.addAttribute("pageTitle", "Update Assigned Subject Information");
        model.addAttribute("user", user);
        model.addAttribute("subjectList", allSubjectByClass);
        return "subjectassign/subject-Assign-update-form-student";
    }

    // Process the subject assigned form.
    @PostMapping("/update/{id}")
    public String updateProcess(@PathVariable Integer id, @ModelAttribute("user") User user) {
        User existingUser = userService.getUserById(id);
        existingUser.setId(id);
        existingUser.setSubjects(user.getSubjects());
        userService.updateUser(existingUser);
        return "redirect:/subject-assign-s/assign-student";
    }

}
