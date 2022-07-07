package com.sktech.academicportal.allcontroller.allUsers;


import com.sktech.academicportal.entity.StudentEntity;
import com.sktech.academicportal.entity.User;
import com.sktech.academicportal.enums.AcademicClass;
import com.sktech.academicportal.enums.AcademicSection;
import com.sktech.academicportal.enums.Designation;
import com.sktech.academicportal.service.StudentRepositoryService;
import com.sktech.academicportal.service.UserRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/teacher")
public class TeacherList {

    @Autowired
    UserRepositoryService userRepositoryService;

    // View All Student store in DB with Datatable
    @GetMapping("/list")
    public String viewLoginPage(Model model) {

        model.addAttribute("roleType", userRepositoryService.listRoles());

        model.addAttribute("user", userRepositoryService.getAllUser());
        model.addAttribute("getAllUserWithoutAdminAndStudentRole", userRepositoryService.getAllUserWithoutAdminAndStudentRole());


        return "/TeacherList/teacher-list";
    }


    // Open the Update form for person Information updating
    @GetMapping("/edit/{id}")
    public String editStudentForm(@PathVariable Integer id, Model model) {

        model.addAttribute("designation", Designation.values());
        model.addAttribute("classSection", AcademicSection.values());
        model.addAttribute("user", userRepositoryService.getUserById(id));

        return "/TeacherList/teacher-update-form";
    }


    // Process the updated information after update button clicked.
    @PostMapping("/update/{id}")
    public String updateStudent(@PathVariable Integer id, @ModelAttribute("user") User user, Model model) {

        User existingTeacher = userRepositoryService.getUserById(id);
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


        // save updated student object
        userRepositoryService.updateUser(existingTeacher);
        return "redirect:/teacher/list";
    }


}


// View all student-  http://localhost:8082/student/list
// Add new student-  http://localhost:8082/student/new
// Edit student info-  http://localhost:8082/student/edit/3
// Delete student-  http://localhost:8082/student/delete/3