package com.sktech.academicportal.controllers.allroles;

import com.sktech.academicportal.entity.Role;
import com.sktech.academicportal.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user-role")
public class RoleList {

    @Autowired
    RoleRepository roleRepository;

    // User role List
    @GetMapping("/list")
    public String roleListHome(Model model) {
        model.addAttribute("pageTitle", "Role List");
        model.addAttribute("role", roleRepository.findAll());
        return "rolelist/role-list";
    }


    // For create a new Role. Open a from for create new Role
    @GetMapping("/new")
    public String addNewRoleForm(Model model) {
        model.addAttribute("pageTitle", "Add Role Form");
        model.addAttribute("role", new Role());
        return "rolelist/role-new-form";
    }

    // Process the fill up form after save button clicked.
    @PostMapping("/save")
    public String processNewUserForm(@ModelAttribute("role") Role role) {
        roleRepository.save(role);
        return "redirect:/user-role/list";
    }


    // Open the Update form for Role Information updating
    @GetMapping("/edit/{id}")
    public String editUserForm(@PathVariable Integer id, Model model) {
        model.addAttribute("pageTitle", "Update  Role Information");
        model.addAttribute("role", roleRepository.findById(id).get());
        return "rolelist/role-update-form";
    }


    // Process the updated information after update button clicked.
    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable Integer id, @ModelAttribute("role") Role role) {
        Role existingRole = roleRepository.findById(id).get();
        existingRole.setId(id);
        existingRole.setName(role.getName());
        existingRole.setDescription(role.getDescription());
        roleRepository.save(existingRole);
        return "redirect:/user-role/list";
    }


    // Delete the Role information and confirm before delete.
    @GetMapping("/delete/{id}")
    public String deleteRole(@PathVariable Integer id) {
        roleRepository.deleteById(id);
        return "redirect:/user-role/list";
    }


}
