package com.sktech.academicportal.controllers.studentandsubject;

import com.sktech.academicportal.entity.StudentResult;
import com.sktech.academicportal.entity.Subject;
import com.sktech.academicportal.entity.User;
import com.sktech.academicportal.service.ResultService;
import com.sktech.academicportal.service.SubjectService;
import com.sktech.academicportal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/student-subject-view")
public class SubjectWithStudent {

    @Autowired
    UserService userService;
    @Autowired
    SubjectService subjectService;
    @Autowired
    ResultService resultService;

    // List of student and assigned subject
    @GetMapping("/list")
    public String subjectHome(Model model) {
        model.addAttribute("pageTitle", "Subject List with user");
        model.addAttribute("user", userService.getAllUserByStudentRole());
        return "studentandsubjectlist/student-subject-list";
    }


    // List of student and assigned subject
    @GetMapping("/mark/{userId}/{subjectId}")
    public String subjectMark(@PathVariable Integer userId, @PathVariable Integer subjectId, Model model) {

        Subject subject = subjectService.getSubjectById(subjectId);
        User user = userService.getUserById(userId);

        model.addAttribute("pageTitle", "Subject Wise result Update");
        model.addAttribute("subject", subject);
        model.addAttribute("user", user);
        model.addAttribute("result", new StudentResult());

        return "studentandsubjectlist/subject-wise-result-add";
    }

    @PostMapping("/update/{userId}/{subjectId}")
    public String processResultForm(@PathVariable Integer userId,
                                    @PathVariable Integer subjectId,
                                    @ModelAttribute("result") StudentResult result) {

        Subject subjectById = subjectService.getSubjectById(subjectId);
        User userById = userService.getUserById(userId);

        String subjectName = subjectById.getSubjectName() + " " + subjectById.getSubjectCode() + " Class- " + subjectById.getSubjectClass();
        String userName = userById.getFirstName() + " " + userById.getLastName() + " Email- " + userById.getEmail();

        result.setSubjectId(result.getSubjectId());
        result.setUserId(result.getUserId());

        result.setSubjectName(subjectName);
        result.setUserName(userName);

        resultService.save(result);
        return "redirect:/student-subject-view/mark-list";
    }


    // List of student and assigned subject
    @GetMapping("/mark-list")
    public String markHome(Model model) {

        model.addAttribute("pageTitle", "Mark List with user and subject");
        model.addAttribute("mark", resultService.getAllResul());

        return "studentandsubjectlist/student-mark-list";
    }


    // Open the Update form for Subject Information updating
    @GetMapping("/edit/{id}")
    public String editUserForm(@PathVariable Integer id, Model model) {

        model.addAttribute("pageTitle", "Edit  Result Information");
        model.addAttribute("result", resultService.getResultById(id).get());

        return "studentandsubjectlist/subject-wise-result-update";
    }


    // Process the updated information after update button clicked.
    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable Integer id, @ModelAttribute("result") StudentResult result) {

        StudentResult existingResult = resultService.getResultById(id).get();

        existingResult.setId(id);
        existingResult.setUserName(result.getUserName());
        existingResult.setSubjectName(result.getSubjectName());
        existingResult.setCt1Mark(result.getCt1Mark());
        existingResult.setCt2Mark(result.getCt2Mark());
        existingResult.setCt3Mark(result.getCt3Mark());
        existingResult.setMidMark(result.getMidMark());
        existingResult.setFinalMark(result.getFinalMark());

        // save updated result object
        resultService.save(existingResult);
        return "redirect:/student-subject-view/mark-list";
    }


    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        resultService.deleteById(id);
        return "redirect:/student-subject-view/mark-list";
    }




}
