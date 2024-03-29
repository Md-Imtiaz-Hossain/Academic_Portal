package com.sktech.academicportal.controllers.allusers;

import com.sktech.academicportal.entity.Role;
import com.sktech.academicportal.entity.User;
import com.sktech.academicportal.helper.FileUploadUtil;
import com.sktech.academicportal.repositories.UserRepository;
import com.sktech.academicportal.service.ProfileService;
import com.sktech.academicportal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UsersList {

    @Autowired
    UserService userService;

    @Autowired
    ProfileService profileService;
    @Autowired
    UserRepository userRepository;


    // Extra data go through model attribute before all other controller run.
    @ModelAttribute("loggedInUser")
    public User extraData(Principal principal) {
        return userService.getUserByEmail(principal.getName());
    }

    // User List
    @GetMapping("/list")
    public String viewUserListPage(Model model) {
        model.addAttribute("pageTitle", "User List");
        model.addAttribute("user", userService.getAllUser());
        return "backenduserslist/user-list";
    }

    // Create a new User
    @GetMapping("/new")
    public String addNewUserForm(Model model) {
        User user = new User();
        List<Role> listRoles = userService.listRoles();
        model.addAttribute("pageTitle", "User Registration");
        model.addAttribute("listRoles", listRoles);
        model.addAttribute("user", user);
        return "backenduserslist/user-new-form";
    }

    // Process save user
    @PostMapping("/save")
    public String processNewUserForm(@Valid @ModelAttribute("user") User user,
                                     @RequestParam("image") MultipartFile multipartFile,
                                     BindingResult bindingResult, Model model) throws IOException {

        if (this.userRepository.getUserByEmail(user.getEmail()) != null) {
            bindingResult.rejectValue("email", "error.user", "An account already exists for this email.");
            model.addAttribute("listRoles", userService.listRoles());
        } else if (user.getPassword().length() < 8) {
            bindingResult.rejectValue("password", "error.password", "Password must Min 8 character.");
            model.addAttribute("listRoles", userService.listRoles());
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("pageTitle", "User Registration");
            model.addAttribute("listRoles", userService.listRoles());
            return "backenduserslist/user-new-form";
        }

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
            model.addAttribute("successMsg", "successfully signed up. Please Login!");
            model.addAttribute("successMsgType", "alert-success");
            userService.saveUser(user);
        }
        return "redirect:/user/list";
    }

    // Update user form
    @GetMapping("/edit/{id}")
    public String editUserForm(@PathVariable Integer id, Model model) {
        List<Role> listRoles = userService.listRoles();
        model.addAttribute("pageTitle", "Edit  User Information");
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("listRoles", listRoles);
        return "backenduserslist/user-update-form";
    }

    // Process user update
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
        userService.updateUser(existingUser);
        return "redirect:/user/list";
    }

    // Delete user
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Integer id) {
        userService.deleteUserById(id);
        return "redirect:/user/list";
    }

}