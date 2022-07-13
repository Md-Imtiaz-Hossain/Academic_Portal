package com.sktech.academicportal.allcontroller.subject;

import com.sktech.academicportal.entity.Role;
import com.sktech.academicportal.entity.Subject;
import com.sktech.academicportal.entity.User;
import com.sktech.academicportal.enums.AcademicSection;
import com.sktech.academicportal.service.SubjectRepositoryService;
import com.sktech.academicportal.service.UserRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("subject-assign")
public class SubjectAssign {

    @Autowired
    UserRepositoryService userRepositoryService;

    @Autowired
    SubjectRepositoryService subjectRepositoryService;

    @GetMapping("/assign")
    public String subjectAssign( Model model ){

        User user = new User();
        List<Subject> subjectList = subjectRepositoryService.getAllSubject();

        model.addAttribute("subjectList", subjectList);
        model.addAttribute("userList", userRepositoryService.getAllUser());
        model.addAttribute("user", user);

        return "/SubjectAssign/assign-And-list";
    }

}
