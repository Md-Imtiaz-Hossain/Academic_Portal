package com.sktech.academicportal.controllers.allusers;

import com.sktech.academicportal.entity.Role;
import com.sktech.academicportal.entity.User;
import com.sktech.academicportal.helper.FileUploadUtil;
import com.sktech.academicportal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UsersList {

    @Autowired
    UserService userService;


    // View All User store in DB with Datatable
    @GetMapping("/list")
    public String viewUserListPage(Model model) {

        model.addAttribute("pageTitle", "User List");
        model.addAttribute("user", userService.getAllUser());

        return "/backenduserslist/user-list";
    }

    // For create a new User. Open a from for create new Student
    @GetMapping("/new")
    public String addNewUserForm(Model model) {

        User user = new User();
        List<Role> listRoles = userService.listRoles();

        model.addAttribute("pageTitle", "User Registration");
        model.addAttribute("listRoles", listRoles);
        model.addAttribute("user", user);

        return "/backenduserslist/user-new-form";
    }


    // Process the fill up form after save button clicked.
    @PostMapping("/save")
    public String processNewUserForm(@ModelAttribute("user") User user,
                                     @RequestParam("image") MultipartFile multipartFile) throws IOException {

        if (!multipartFile.isEmpty()) {
            String fileNameSelected = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            user.setPhotos(fileNameSelected);
            Integer userId = userService.saveUser(user).getId();

            String uploadDir = "user-photos/" + userId;
            FileUploadUtil.cleanDirectory(uploadDir);
            FileUploadUtil.saveFile(uploadDir, fileNameSelected, multipartFile);
        } else {
            if (user.getPhotos().isEmpty()) {
                user.setPhotos(null);
            }
            userService.saveUser(user);
        }

        //userRepositoryService.saveUser(user);
        return "redirect:/user/list";
    }


    // Open the Update form for person Information updating
    @GetMapping("/edit/{id}")
    public String editUserForm(@PathVariable Integer id, Model model) {

        List<Role> listRoles = userService.listRoles();

        model.addAttribute("pageTitle", "Edit  User Information");
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("listRoles", listRoles);

        return "/backenduserslist/user-update-form";
    }


    // Process the updated information after update button clicked.
    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable Integer id, @ModelAttribute("user") User user, Model model) {

        User existingUser = userService.getUserById(id);
        existingUser.setId(id);
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        existingUser.setRoles(user.getRoles());

        String encodedPassword = userService.encodePasswordUsingString(user.getPassword());
        existingUser.setPassword(encodedPassword);

        // save updated student object
        userService.updateUser(existingUser);
        return "redirect:/user/list";
    }


    // Delete the person information and confirm before delete.
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Integer id) {
        userService.deleteUserById(id);
        return "redirect:/user/list";
    }


}
