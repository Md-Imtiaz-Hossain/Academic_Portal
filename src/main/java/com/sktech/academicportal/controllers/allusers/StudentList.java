package com.sktech.academicportal.controllers.allusers;


import com.sktech.academicportal.entity.User;
import com.sktech.academicportal.enums.AcademicClass;
import com.sktech.academicportal.enums.AcademicSection;
import com.sktech.academicportal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/student")
public class StudentList {

    @Autowired
    UserService userService;

    // Extra data go through model attribute before all other controller run.
    @ModelAttribute("loggedInUser")
    public User extraData(Principal principal) {
        return userService.getUserByEmail(principal.getName());
    }


    // View All Student store in DB with Datatable
    @GetMapping("/list")
    public String viewLoginPage(Model model) {
        model.addAttribute("pageTitle", "Student List");
        model.addAttribute("userListWithStudentRole", userService.getAllUserByStudentRole());
        return "studentlist/student-list";
    }


    // Open the Update form for person Information updating
    @GetMapping("/edit/{id}")
    public String editStudentForm(@PathVariable Integer id, Model model) {

        model.addAttribute("pageTitle", "Edit  Student Information");
        model.addAttribute("currentClass", AcademicClass.values());
        model.addAttribute("classSection", AcademicSection.values());
        model.addAttribute("user", userService.getUserById(id));

        return "studentlist/student-update-form";
    }


    // Process the updated information after update button clicked.
    @PostMapping("/update/{id}")
    public String updateStudent(@PathVariable Integer id,  @ModelAttribute("user") User user, Model model) {

        User existingStudent = userService.getUserById(id);
        existingStudent.setId(id);
        existingStudent.setFatherName(user.getFatherName());
        existingStudent.setMotherName(user.getMotherName());
        existingStudent.setAdmissionDate(user.getAdmissionDate());
        existingStudent.setBirthDate(user.getBirthDate());

        existingStudent.setClassRoll(user.getClassRoll());
        existingStudent.setCurrentClass(user.getCurrentClass());
        existingStudent.setClassSection(user.getClassSection());
        existingStudent.setAcademicID(user.getAcademicID());
        existingStudent.setContactNo(user.getContactNo());
        existingStudent.setAddress(user.getAddress());


        // save updated student object
        userService.updateUser(existingStudent);
        return "redirect:/student/list";
    }

}

