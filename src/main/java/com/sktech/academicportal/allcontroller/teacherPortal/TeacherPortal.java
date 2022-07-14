package com.sktech.academicportal.allcontroller.teacherPortal;

import com.sktech.academicportal.entity.Subject;
import com.sktech.academicportal.service.UserRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/teacher-portal")
public class TeacherPortal {

    @Autowired
    UserRepositoryService userRepositoryService;

    @GetMapping("/mySubject")
    public String teacherPortalHome(Model model, Principal principal){

        String principalName = principal.getName();
        List<Subject> allAssignedSubjectToATeacher = userRepositoryService.getAllAssignedSubjectToATeacher(principalName);
        System.out.println("=====================================");
        System.out.println(allAssignedSubjectToATeacher);
        System.out.println("=====================================");

        model.addAttribute("principalName", principalName);
        model.addAttribute("user", userRepositoryService.getAllUser());
        model.addAttribute("allAssignedSubjectToATeacher", allAssignedSubjectToATeacher);
        return "TeacherPortal/taking-subject-of-mine";
    }


}
