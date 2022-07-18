package com.sktech.academicportal.controllers.teacherportal;

import com.sktech.academicportal.entity.Subject;
import com.sktech.academicportal.service.ResultService;
import com.sktech.academicportal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/teacher-portal")
public class TeacherPortal {

    @Autowired
    UserService userService;

    @Autowired
    ResultService resultService;

    // List of subject assign to me.
    @GetMapping("/my-subject")
    public String teacherPortalHome(Model model, Principal principal){

        // Get the logged-in user email
        String principalName = principal.getName();
        // Get the list of Subject using logged-in user email
        List<Subject> allAssignedSubjectToATeacher = userService.getAllAssignedSubjectToATeacher(principalName);

        model.addAttribute("pageTitle", "Assigned Subject List of Logged-in User");
        model.addAttribute("principalName", principalName);
        model.addAttribute("user", userService.getAllUser());
        model.addAttribute("allAssignedSubjectToATeacher", allAssignedSubjectToATeacher);

        return "teacherportal/taking-subject-of-mine";
    }

    // Get all student Using subject id for result creation.
    @GetMapping("/result/{subjectId}")
    public String subjectHome(@PathVariable Integer subjectId,  Model model) {
        model.addAttribute("pageTitle", "Subject List with user");
        model.addAttribute("user", userService.getAllUserBySubjectId(subjectId));
        return "/teacherportal/student-subject-list-using-subjectid";
    }


//
//
//
//    @GetMapping("/resultList")
//    public String resultList(Model model){
//        model.addAttribute("allResult", userRepositoryService.studentResults());
//        model.addAttribute("user", userRepositoryService.getAllUserByStudentRole());
//        return "/TeacherPortal/my-subject-result-list";
//    }
//
//
//    // Open the Update form for Student Result Information
//    @GetMapping("/edit/{id}")
//    public String updateResultForm(@PathVariable Integer id, Model model) {
//
//        User user = userRepositoryService.getUserById(id);
//        //List<StudentResult> allResult = user.getStudentResults();
//        //model.addAttribute("allResult", userRepositoryService.studentResults());
//        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
//        System.out.println(user);
//
//        Set<StudentResult> studentResults = user.getStudentResults();
//        System.out.println(studentResults);
//
//        List<StudentResult> allResult = new ArrayList<>(studentResults);
//
//        model.addAttribute("allResult", allResult);
//
//        model.addAttribute("user", userRepositoryService.getUserById(id));
//
//
//        return "/TeacherPortal/my-subject-result-update";
//    }
//
//
//
//    /**
//     * Process the updated information after update button clicked.
//     * Find existing user use id
//     * Set the existing id and replace with new all attribute value with old
//     * Finally save the user
//     */
//    @PostMapping("/update/{id}")
//    public String updateUser(@PathVariable Integer id, @ModelAttribute("user") User user) {
//
//        User existingUser = userRepositoryService.getUserById(id);
//        existingUser.setId(id);
//
//        existingUser.setStudentResults(user.getStudentResults());
//        userRepositoryService.updateUser(existingUser);
//
//        return "redirect:/teacher-portal/resultList";
//    }


}

