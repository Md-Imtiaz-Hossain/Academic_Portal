package com.sktech.academicportal.allcontroller.endUsers;

import com.sktech.academicportal.entity.Role;
import com.sktech.academicportal.entity.StudentEntity;
import com.sktech.academicportal.entity.User;
import com.sktech.academicportal.enums.AcademicClass;
import com.sktech.academicportal.enums.AcademicSection;
import com.sktech.academicportal.service.UserRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/user")
public class usersList {

    @Autowired
    UserRepositoryService userRepositoryService;

    // View All User store in DB with Datatable
    @GetMapping("/list")
    public String viewUserListPage(Model model) {
        model.addAttribute("user", userRepositoryService.getAllUser());
        return "/BackEndUsersList/user-list";
    }

    // For create a new User. Open a from for create new Student
    @GetMapping("/new")
    public String addNewUserForm(Model model) {
        User user = new User();
        List<Role> listRoles = userRepositoryService.listRoles();
        model.addAttribute("classSection", AcademicSection.values());

        model.addAttribute("listRoles", listRoles);
        model.addAttribute("user", user);
        return "/BackEndUsersList/user-new-form";
    }


    // Process the fill up form after save button clicked.
    @PostMapping("/save")
    public String processNewUserForm(@ModelAttribute("user") User user) {
        userRepositoryService.saveUser(user);
        return "redirect:/user/list";
    }



}
