package com.sktech.academicportal.allcontroller.allUsers;


import com.sktech.academicportal.entity.User;
import com.sktech.academicportal.enums.AcademicClass;
import com.sktech.academicportal.enums.AcademicSection;
import com.sktech.academicportal.service.UserRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/student")
public class studentList {

    @Autowired
    UserRepositoryService userRepositoryService;

    // View All Student store in DB with Datatable
    @GetMapping("/list")
    public String viewLoginPage(Model model) {
        model.addAttribute("userListWithStudentRole", userRepositoryService.getAllUserByStudentRole());
        return "/StudentList/student-list";
    }


    // Open the Update form for person Information updating
    @GetMapping("/edit/{id}")
    public String editStudentForm(@PathVariable Integer id, Model model) {

        model.addAttribute("currentClass", AcademicClass.values());
        model.addAttribute("classSection", AcademicSection.values());
        model.addAttribute("user", userRepositoryService.getUserById(id));

        return "/StudentList/student-update-form";
    }


    // Process the updated information after update button clicked.
    @PostMapping("/update/{id}")
    public String updateStudent(@PathVariable Integer id,  @ModelAttribute("user") User user, Model model) {

        User existingStudent = userRepositoryService.getUserById(id);
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
        userRepositoryService.updateUser(existingStudent);
        return "redirect:/student/list";
    }

}


// View all student-  http://localhost:8082/student/list
// Edit student info-  http://localhost:8082/student/edit/3
