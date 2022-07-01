package com.sktech.academicportal.allcontroller.student;


import com.sktech.academicportal.entity.StudentEntity;
import com.sktech.academicportal.service.StudentRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/student")
public class studentList {

    // http://localhost:8082/student/list

    @Autowired
    StudentRepositoryService studentRepositoryService;

    // View All Student store in DB with Datatable
    @GetMapping("/list")
    public String viewLoginPage(Model model) {
        model.addAttribute("student", studentRepositoryService.getAllStudent());
        return "/StudentList/student-list";
    }

    // For create a new Student. Open a from for create new Student
    @GetMapping("/new")
    public String addNewStudentForm(Model model) {
        StudentEntity student = new StudentEntity();
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
    public String editStudentForm(@PathVariable Long id, Model model) {
        model.addAttribute("student", studentRepositoryService.getStudentById(id));
        return "/StudentList/student-update-form";
    }


    // Process the updated information after update button clicked.
    @PostMapping("/update/{id}")
    public String updateStudent(@PathVariable Long id,
                                @ModelAttribute("student") StudentEntity student,
                                Model model) {

        StudentEntity existingStudent = studentRepositoryService.getStudentById(id);
        existingStudent.setId(id);
        existingStudent.setFirstName(student.getFirstName());
        existingStudent.setLastName(student.getLastName());
        existingStudent.setEmail(student.getEmail());

        // save updated student object
        studentRepositoryService.updateStudent(existingStudent);
        return "redirect:/student/list";
    }


    // Delete the person information and confirm before delete.
    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentRepositoryService.deleteStudentById(id);
        return "redirect:/student/list";
    }


}
