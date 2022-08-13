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
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/normal-user")
public class NormalUserInfo {

    @Autowired
    UserService userService;

    // Extra data go through model attribute before all other controller run.
    @ModelAttribute("loggedInUser")
    public User extraData(Principal principal) {
        return userService.getUserByEmail(principal.getName());
    }

    //   Users list
    @GetMapping("/list")
    public String userList(Model model) {
        model.addAttribute("pageTitle", "Normal User List");
        model.addAttribute("user", userService.getAllUserWithoutRootAdminAndAdminRole());
        return "normaluserlist/user-list";
    }

    // Form for new user
    @GetMapping("/new")
    public String newUserForm(Model model) {
        User user = new User();
        model.addAttribute("pageTitle", "User Registration");
        model.addAttribute("listRoles", userService.listRolesWithoutAdminAndRootAdmin());
        model.addAttribute("user", user);
        return "normaluserlist/user-new-form";
    }


    // Save new user
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
        return "redirect:/normal-user/list";
    }

    // Form of update user
    @GetMapping("/edit/{id}")
    public String editUserForm(@PathVariable Integer id, Model model) {
        List<Role> listRoles = userService.listRoles();
        model.addAttribute("pageTitle", "Edit  User Information");
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("listRoles", listRoles);
        return "normaluserlist/user-update-form";
    }

    // Process update user form
    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable Integer id, @ModelAttribute("user") User user) {
        User existingUser = userService.getUserById(id);
        existingUser.setId(id);
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        existingUser.setRoles(user.getRoles());
        String encodedPassword = userService.encodePasswordUsingString(user.getPassword());
        existingUser.setPassword(encodedPassword);
        userService.updateUser(existingUser);
        return "redirect:/normal-user/list";
    }

    // Delete user
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Integer id) {
        userService.deleteUserById(id);
        return "redirect:/normal-user/list";
    }

}
