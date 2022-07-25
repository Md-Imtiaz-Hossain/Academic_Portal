package com.sktech.academicportal.controllers.subject;

import com.sktech.academicportal.entity.Subject;
import com.sktech.academicportal.enums.AcademicClass;
import com.sktech.academicportal.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/subject")
public class SubjectList {

    @Autowired
    SubjectService subjectService;


    // List of all subject
    @GetMapping("/list")
    public String subjectHome(Model model){

        model.addAttribute("pageTitle", "Subject List");
        model.addAttribute("subjectList", subjectService.getAllSubject());

        return "subjectlist/subject-list";
    }


    // For add a new Subject. Open a from for create new Subject
    @GetMapping("/new")
    public String addNewSubjectForm(Model model) {

        Subject subject = new Subject();

        model.addAttribute("pageTitle", "Add Subject");
        model.addAttribute("class", AcademicClass.values());
        model.addAttribute("subject", subject);

        return "subjectlist/subject-new-form";
    }


    // Process the fill up form after save button clicked.
    @PostMapping("/save")
    public String processSubjectForm(@ModelAttribute("subject") Subject subject){
        subjectService.saveSubject(subject);
        return "redirect:/subject/list";
    }


    // Open the Update form for Subject Information updating
    @GetMapping("/edit/{id}")
    public String editSubjectForm(@PathVariable Integer id, Model model) {
        model.addAttribute("pageTitle", "Edit  Subject Information");
        model.addAttribute("class", AcademicClass.values());
        model.addAttribute("subject", subjectService.getSubjectById(id));
        return "subjectlist/subject-update-form";
    }


    // Process the updated information after update button clicked.
    @PostMapping("/update/{id}")
    public String updateSubject(@PathVariable Integer id, @ModelAttribute("subject") Subject subject) {
        Subject existingSubject = subjectService.getSubjectById(id);
        existingSubject.setId(id);
        existingSubject.setSubjectName(subject.getSubjectName());
        existingSubject.setSubjectCode(subject.getSubjectCode());
        existingSubject.setSubjectClass(subject.getSubjectClass());
        existingSubject.setSubjectType(subject.getSubjectType());
        subjectService.updateSubject(existingSubject);
        return "redirect:/subject/list";
    }


    // Delete the Subject information and confirm before delete.
    @GetMapping("/delete/{id}")
    public String deleteSubject(@PathVariable Integer id) {
        subjectService.deleteSubjectById(id);
        return "redirect:/subject/list";
    }

}
