package com.sktech.academicportal.controllers.allusers;


import com.sktech.academicportal.entity.User;
import com.sktech.academicportal.enums.AcademicSection;
import com.sktech.academicportal.enums.Designation;
import com.sktech.academicportal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/teacher")
public class TeacherList {

    @Autowired
    UserService userService;

    // Extra data go through model attribute before all other controller run.
    @ModelAttribute("loggedInUser")
    public User extraData(Principal principal) {
        return userService.getUserByEmail(principal.getName());
    }

    // View All Teacher
    @GetMapping("/list")
    public String teacherList(Model model) {
        model.addAttribute("pageTitle", "Teacher List");
        model.addAttribute("getAllUserWithoutAdminAndStudentRole", userService.getAllUserWithoutRootAdminAndAdminAndStudentRole());
        return "teacherlist/teacher-list";
    }

    // Open the Update form for Teacher Information updating
    @GetMapping("/edit/{id}")
    public String editTeacherForm(@PathVariable Integer id, Model model) {
        model.addAttribute("pageTitle", "Edit  Teacher Information");
        model.addAttribute("designation", Designation.values());
        model.addAttribute("classSection", AcademicSection.values());
        model.addAttribute("user", userService.getUserById(id));
        return "teacherlist/teacher-update-form";
    }

    // Process the updated form
    @PostMapping("/update/{id}")
    public String updateTeacher(@PathVariable Integer id, @ModelAttribute("user") User user) {
        User existingTeacher = userService.getUserById(id);
        existingTeacher.setId(id);
        existingTeacher.setFatherName(user.getFatherName());
        existingTeacher.setMotherName(user.getMotherName());
        existingTeacher.setTeachingStartDate(user.getTeachingStartDate());
        existingTeacher.setTeachingEndDate(user.getTeachingEndDate());
        existingTeacher.setSubjectSpeciality(user.getSubjectSpeciality());
        existingTeacher.setDesignation(user.getDesignation());
        existingTeacher.setAcademicID(user.getAcademicID());
        existingTeacher.setContactNo(user.getContactNo());
        existingTeacher.setAddress(user.getAddress());
        userService.updateUser(existingTeacher);
        return "redirect:/teacher/list";
    }

}
