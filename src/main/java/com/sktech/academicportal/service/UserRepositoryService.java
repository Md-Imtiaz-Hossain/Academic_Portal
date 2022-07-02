package com.sktech.academicportal.service;


import com.sktech.academicportal.entity.Role;
import com.sktech.academicportal.entity.User;
import com.sktech.academicportal.repository.RoleRepository;
import com.sktech.academicportal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserRepositoryService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    // Get All Users
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    // Save Users
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    // Get a single User by an id
    public User getUserById(Integer id) {
        return userRepository.findById(id).get();
    }

    // Update User information
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    // delete User by their id
    public void deleteUserById(Integer id) {
        userRepository.deleteById(id);
    }


    public List<Role> listRoles() {
        return (List<Role>) roleRepository.findAll();
    }


    public boolean isEmailUnique(Integer id, String email) {
        User userByEmail = userRepository.getUserByEmail(email);
        if (userByEmail == null) {
            return true;
        }

        if (id == null) {
            return false;
        } else {
            return userByEmail.getId().equals(id);
        }
    }


}