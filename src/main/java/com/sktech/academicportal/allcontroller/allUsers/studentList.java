package com.sktech.academicportal.allcontroller.allUsers;


import com.sktech.academicportal.entity.Role;
import com.sktech.academicportal.entity.StudentEntity;
import com.sktech.academicportal.entity.User;
import com.sktech.academicportal.enums.AcademicClass;
import com.sktech.academicportal.enums.AcademicSection;
import com.sktech.academicportal.service.StudentRepositoryService;
import com.sktech.academicportal.service.UserRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/student")
public class studentList {

    @Autowired
    StudentRepositoryService studentRepositoryService;
    @Autowired
    UserRepositoryService userRepositoryService;

    // View All Student store in DB with Datatable
    @GetMapping("/list")
    public String viewLoginPage(Model model) {

        model.addAttribute("roleType", userRepositoryService.listRoles());

        model.addAttribute("user", userRepositoryService.getAllUser());
        model.addAttribute("userListWithStudentRole", userRepositoryService.getAllUserByStudentRole());

        model.addAttribute("student", studentRepositoryService.getAllStudent());

        return "/StudentList/student-list";
    }

    // For create a new Student. Open a from for create new Student
    @GetMapping("/new")
    public String addNewStudentForm(Model model) {
        StudentEntity student = new StudentEntity();
        model.addAttribute("currentClass", AcademicClass.values());
        model.addAttribute("classSection", AcademicSection.values());
        model.addAttribute("student", student);
        return "/StudentList/student-new-form";
    }

    // Process the fill up form after save button clicked.
    @PostMapping("/save")
    public String processNewStudentForm(@ModelAttribute("student") StudentEntity studentEntity) {
        studentRepositoryService.saveStudent(studentEntity);
        return "redirect:/student/list";
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
    public String updateStudent(@PathVariable Integer id, @ModelAttribute("student") StudentEntity student, Model model) {

        User existingStudent = userRepositoryService.getUserById(id);
        existingStudent.setId(id);
        existingStudent.setFatherName(student.getFatherName());
        existingStudent.setMotherName(student.getMotherName());
        existingStudent.setAdmissionDate(student.getAdmissionDate());
        existingStudent.setBirthDate(student.getBirthDate());

        existingStudent.setClassRoll(student.getClassRoll());
        existingStudent.setCurrentClass(student.getCurrentClass());
        existingStudent.setClassSection(student.getClassSection());
        existingStudent.setAcademicID(student.getAcademicID());
        existingStudent.setContactNo(student.getContactNo());
        existingStudent.setAddress(student.getAddress());


        // save updated student object
        userRepositoryService.updateUser(existingStudent);
        return "redirect:/student/list";
    }


    // Delete the person information and confirm before delete.
    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Integer id) {
        studentRepositoryService.deleteStudentById(id);
        return "redirect:/student/list";
    }


}


// View all student-  http://localhost:8082/student/list
// Add new student-  http://localhost:8082/student/new
// Edit student info-  http://localhost:8082/student/edit/3
// Delete student-  http://localhost:8082/student/delete/3