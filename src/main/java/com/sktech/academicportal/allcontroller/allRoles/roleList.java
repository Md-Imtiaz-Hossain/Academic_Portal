package com.sktech.academicportal.allcontroller.allRoles;

import com.sktech.academicportal.entity.Role;
import com.sktech.academicportal.entity.User;
import com.sktech.academicportal.helper.FileUploadUtil;
import com.sktech.academicportal.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/user-role")
public class roleList {

    @Autowired
    RoleRepository roleRepository;

    @GetMapping("/list")
    public String roleListHome(Model model){
        model.addAttribute("pageTitle", "Role List");
        model.addAttribute("role", roleRepository.findAll());
        return "/RoleList/role-list";
    }


    // For create a new Role. Open a from for create new Role
    @GetMapping("/new")
    public String addNewRoleForm(Model model) {
        model.addAttribute("pageTitle", "Add Role Form");
        model.addAttribute("role", new Role());
        return "/RoleList/role-new-form";
    }


    // Process the fill up form after save button clicked.
    @PostMapping("/save")
    public String processNewUserForm(@ModelAttribute("role") Role role) {
        roleRepository.save(role);
        return "redirect:/user-role/list";
    }


//
//
//    // Open the Update form for person Information updating
//    @GetMapping("/edit/{id}")
//    public String editUserForm(@PathVariable Integer id, Model model) {
//
//        List<Role> listRoles = userRepositoryService.listRoles();
//
//        model.addAttribute("pageTitle", "Edit  User Information");
//        model.addAttribute("user", userRepositoryService.getUserById(id));
//        model.addAttribute("listRoles", listRoles);
//
//        return "/BackEndUsersList/user-update-form";
//    }
//
//
//    // Process the updated information after update button clicked.
//    @PostMapping("/update/{id}")
//    public String updateUser(@PathVariable Integer id, @ModelAttribute("user") User user, Model model) {
//
//        User existingUser = userRepositoryService.getUserById(id);
//        existingUser.setId(id);
//        existingUser.setFirstName(user.getFirstName());
//        existingUser.setLastName(user.getLastName());
//        existingUser.setEmail(user.getEmail());
//        existingUser.setRoles(user.getRoles());
//
//        String encodedPassword = userRepositoryService.encodePasswordUsingString(user.getPassword());
//        existingUser.setPassword(encodedPassword);
//
//        // save updated student object
//        userRepositoryService.updateUser(existingUser);
//        return "redirect:/user/list";
//    }


    // Delete the Role information and confirm before delete.
    @GetMapping("/delete/{id}")
    public String deleteRole(@PathVariable Integer id) {
        roleRepository.deleteById(id);
        return "redirect:/user-role/list";
    }


}
