package com.sktech.academicportal.service;


import com.sktech.academicportal.entity.Role;
import com.sktech.academicportal.entity.Subject;
import com.sktech.academicportal.entity.User;
import com.sktech.academicportal.repository.RoleRepository;
import com.sktech.academicportal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Service
public class UserRepositoryService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    // Get All Users
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    // Save Users
    public User saveUser(User user) {
        encodePassword(user);
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

    // This methode will return with user List which contain Role-Student
    public List<User> getAllUserByStudentRole() {
        List<User> userListWithStudentRole = new ArrayList<>();
        List<User> userList = userRepository.findAll();
        for (User u : userList) {
            for (Role r : u.getRoles()) {
                if (Objects.equals(r.getName(), "Student")) {
                    System.out.println(u);
                    userListWithStudentRole.add(u);
                }
            }
        }
        System.out.println(userListWithStudentRole);
        return userListWithStudentRole;
    }

    // This methode will return with user List which contain all Role except Admin and student
    public List<User> getAllUserWithoutAdminAndStudentRole() {
        List<User> getAllUserWithoutAdminRole = new ArrayList<>();
        List<User> userList = userRepository.findAll();
        for (User u : userList) {
            for (Role r : u.getRoles()) {
                if (Objects.equals(r.getName(), "Admin" ) || Objects.equals(r.getName(), "Student" )) {
                    break;
                }else {
                    getAllUserWithoutAdminRole.add(u);
                }
            }
        }
        System.out.println(getAllUserWithoutAdminRole);
        return getAllUserWithoutAdminRole;
    }

    // This methode will return with user List which contain all Role except student
    public List<User> getAllUserWithoutStudentRole() {
        List<User> getAllUserWithoutStudentRole = new ArrayList<>();
        List<User> userList = userRepository.findAll();
        for (User u : userList) {
            for (Role r : u.getRoles()) {
                if (Objects.equals(r.getName(), "Student" )) {
                    break;
                }else {
                    getAllUserWithoutStudentRole.add(u);
                }
            }
        }
        return getAllUserWithoutStudentRole;
    }

    // Method for get assigned subject to a teacher
    public List<Subject> getAllAssignedSubjectToATeacher(String email){
        List<Subject> subjectByEmail = (List<Subject>) userRepository.getSubjectByEmail(email);
        return subjectByEmail;
    }

    // Method of password encoder using User object
    private void encodePassword(User user) {
        String encodePassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodePassword);
    }

    // Method of password encoder using String
    public String encodePasswordUsingString(String password) {
        return passwordEncoder.encode(password);
    }


}