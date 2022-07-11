package com.sktech.academicportal.allcontroller.subject;

import com.sktech.academicportal.enums.AcademicClass;
import com.sktech.academicportal.service.SubjectRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/subject")
public class Subject {

    @Autowired
    SubjectRepositoryService subjectRepositoryService;

    @GetMapping("/list")
    public String home(Model model){
        model.addAttribute("subjectList", subjectRepositoryService.getAllSubject());
        return "/Subject/subject-list";
    }


    // For create a new User. Open a from for create new Student
    @GetMapping("/new")
    public String addNewSubjectForm(Model model) {

        model.addAttribute("class", AcademicClass.values());
        model.addAttribute("subject", new Subject());

        return "/Subject/subject-new-form";
    }


    // Process the fill up form after save button clicked.
    @PostMapping("/save")
    public String processSubjectForm(@ModelAttribute("subject") com.sktech.academicportal.entity.Subject subject){

        subjectRepositoryService.saveSubject(subject);
        return "redirect:/subject/subject-list";
    }

//
//    // Open the Update form for person Information updating
//    @GetMapping("/edit/{id}")
//    public String editUserForm(@PathVariable Integer id, Model model) {
//
//        List<Role> listRoles = userRepositoryService.listRoles();
//
//        model.addAttribute("user", userRepositoryService.getUserById(id));
//        model.addAttribute("listRoles", listRoles);
//
//        return "/BackEndUsersList/user-update-form";
//    }
//
//
//    // Process the updated information after update button clicked.
//    @PostMapping("/update/{id}")
//    public String updateUser(@PathVariable Integer id, @ModelAttribute("user") User user, Model model) {
//
//        User existingUser = userRepositoryService.getUserById(id);
//        existingUser.setId(id);
//        existingUser.setFirstName(user.getFirstName());
//        existingUser.setLastName(user.getLastName());
//        existingUser.setEmail(user.getEmail());
//        existingUser.setRoles(user.getRoles());
//
//        String encodedPassword = userRepositoryService.encodePasswordUsingString(user.getPassword());
//        existingUser.setPassword(encodedPassword);
//
//        // save updated student object
//        userRepositoryService.updateUser(existingUser);
//        return "redirect:/user/list";
//    }
//
//
//    // Delete the person information and confirm before delete.
//    @GetMapping("/delete/{id}")
//    public String deleteUser(@PathVariable Integer id) {
//        userRepositoryService.deleteUserById(id);
//        return "redirect:/user/list";
//    }

}
