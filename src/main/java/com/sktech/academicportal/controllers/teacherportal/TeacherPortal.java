package com.sktech.academicportal.controllers.teacherportal;

import com.sktech.academicportal.entity.ClassRoutine;
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

import java.security.Principal;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/teacher-portal")
public class TeacherPortal {

    @Autowired
    UserService userService;

    @Autowired
    ResultService resultService;

    @Autowired
    SubjectService subjectService;

    // List of subject assign to me (logged in teacher).
    @GetMapping("/my-subject")
    public String teacherPortalHome(Model model, Principal principal) {

        String loggedInUserName = principal.getName();
        List<Subject> allAssignedSubjectToATeacher = userService.getAllAssignedSubjectToATeacher(loggedInUserName);

        model.addAttribute("pageTitle", " Logged-in User's Assigned Subject");
        model.addAttribute("principalName", loggedInUserName);
        model.addAttribute("user", userService.getAllUser());
        model.addAttribute("allAssignedSubjectToATeacher", allAssignedSubjectToATeacher);

        return "teacherportal/taking-subject-of-mine";
    }

    @GetMapping("/my-class-routine")
    public String teacherClassRoutine(Model model, Principal principal) {

        String loggedInUserName = principal.getName();
        List<Subject> allAssignedSubjectToATeacher = userService.getAllAssignedSubjectToATeacher(loggedInUserName);
        List<ClassRoutine> classRoutines = userService.getAllClassRoutineByAssignSubject(allAssignedSubjectToATeacher);
        Set<String> assignedSubjectClassList = userService.getAllClassNameByAssignSubject(allAssignedSubjectToATeacher);

        model.addAttribute("pageTitle", " Logged-in User's Class routine");
        model.addAttribute("principalName", loggedInUserName);
        model.addAttribute("classRoutines", classRoutines);
        model.addAttribute("assignedSubjectClassList", assignedSubjectClassList);

        return "teacherportal/my-subject-class-routine";
    }

    // Mark list of Teacher(logged-in) assigned all subject's all student.
    @GetMapping("/mark-list")
    public String markHome(Model model, Principal principal) {

        String userEmail = principal.getName();
        List<StudentResult> resultUsingUserId = resultService.getResultUsingUserEmail(userEmail);

        model.addAttribute("pageTitle", "Mark List with Username and Subject name");
        model.addAttribute("mark", resultUsingUserId);

        return "teacherportal/student-mark-list";
    }


    // Get all student Using subject id for result creation.
    @GetMapping("/result/{subjectId}")
    public String subjectHome(@PathVariable Integer subjectId, Model model) {

        model.addAttribute("pageTitle", "Result Add link in here");
        model.addAttribute("user", userService.getAllUserBySubjectId(subjectId));

        return "teacherportal/student-subject-list-using-subjectid";
    }


    // List of student and assigned subject
    @GetMapping("/mark/{userId}/{subjectId}")
    public String subjectMark(@PathVariable Integer userId,
                              @PathVariable Integer subjectId,
                              Model model) {

        Subject getSubjectUsingSubjectId = subjectService.getSubjectById(subjectId);
        User getUserByUsingUserId = userService.getUserById(userId);

        model.addAttribute("pageTitle", "Result add");
        model.addAttribute("subject", getSubjectUsingSubjectId);
        model.addAttribute("user", getUserByUsingUserId);
        model.addAttribute("result", new StudentResult());

        return "teacherportal/subject-wise-result-add";
    }


    // Save result
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

        return "redirect:/teacher-portal/mark-list";
    }


    // Open the Update form for result
    @GetMapping("/edit/{id}")
    public String editUserForm(@PathVariable Integer id, Model model) {

        model.addAttribute("pageTitle", "Result Update");
        model.addAttribute("result", resultService.getResultById(id).get());

        return "teacherportal/subject-wise-result-update";
    }


    // Update the result
    @PostMapping("/update-result/{id}")
    public String updateUser(@PathVariable Integer id,
                             @ModelAttribute("result") StudentResult result) {

        StudentResult existingResult = resultService.getResultById(id).get();

        existingResult.setId(id);
        existingResult.setUserName(result.getUserName());
        existingResult.setSubjectName(result.getSubjectName());
        existingResult.setCt1Mark(result.getCt1Mark());
        existingResult.setCt2Mark(result.getCt2Mark());
        existingResult.setCt3Mark(result.getCt3Mark());
        existingResult.setMidMark(result.getMidMark());
        existingResult.setFinalMark(result.getFinalMark());

        resultService.save(existingResult);

        return "redirect:/teacher-portal/mark-list";
    }


    // Delete result
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        resultService.deleteById(id);
        return "redirect:/teacher-portal/mark-list";
    }


}

