package com.sktech.academicportal.service;


import com.sktech.academicportal.entity.*;
import com.sktech.academicportal.repositories.ResultRepository;
import com.sktech.academicportal.repositories.RoleRepository;
import com.sktech.academicportal.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    ResultRepository resultRepository;

    @Autowired
    RoutineService routineService;

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

    public User getUserByEmail(String s) {
        return userRepository.getUserByEmail(s);
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

    public List<Role> listRolesWithoutAdminAndRootAdmin() {
        List<Role> roleList = roleRepository.findAll();
        List<Role> tmpR = roleList.stream()
                .filter(role -> (!role.getName().equals("Admin")))
                .filter(role -> (!role.getName().equals("RootAdmin")))
                .collect(Collectors.toList());
        return tmpR;
    }

    public List<StudentResult> studentResults() {
        return resultRepository.findAll();
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
                    userListWithStudentRole.add(u);
                }
            }
        }
        return userListWithStudentRole;
    }


    // This methode will return with user List which contain Role-Student
    public List<User> getAllUserBySubjectId(Integer subjectId) {
        List<User> getAllUserBySubjectId = new ArrayList<>();
        List<User> userList = getAllUserByStudentRole();
        for (User u : userList) {
            for (Subject r : u.getSubjects()) {
                if (Objects.equals(r.getId(), subjectId)) {
                    System.out.println(u);
                    getAllUserBySubjectId.add(u);
                }
            }
        }
        System.out.println(getAllUserBySubjectId);
        return getAllUserBySubjectId;
    }

    // This methode will return with user List which contain all Role except Admin and student
    public List<User> getAllUserWithoutRootAdminAndAdminAndStudentRole() {
        List<User> getAllUserWithoutAdminRole = new ArrayList<>();
        List<User> userList = userRepository.findAll();
        for (User u : userList) {
            for (Role r : u.getRoles()) {
                if (Objects.equals(r.getName(), "Admin") || Objects.equals(r.getName(), "Student")|| Objects.equals(r.getName(), "RootAdmin")) {
                    break;
                } else {
                    getAllUserWithoutAdminRole.add(u);
                }
            }
        }
        return getAllUserWithoutAdminRole;
    }

    // This methode will return with user List which contain all Role except  Root Admin
    public Set<User> getAllUserWithoutRootAdminAndAdminRole() {
        Set<User> getAllUserWithoutAdminRole = new HashSet<>();
        List<User> userList = userRepository.findAll();
        for (User u : userList) {
            for (Role r : u.getRoles()) {
                if (Objects.equals(r.getName(), "RootAdmin") || Objects.equals(r.getName(), "Admin") ) {
                    break;
                } else {
                    getAllUserWithoutAdminRole.add(u);
                }
            }
        }
        return getAllUserWithoutAdminRole;
    }


    // This methode will return with user List which contain all Role except student
    public List<User> getAllUserWithoutStudentRole() {
        List<User> getAllUserWithoutStudentRole = new ArrayList<>();
        List<User> userList = userRepository.findAll();
        for (User u : userList) {
            for (Role r : u.getRoles()) {
                if (Objects.equals(r.getName(), "Student")) {
                    break;
                } else {
                    getAllUserWithoutStudentRole.add(u);
                }
            }
        }
        return getAllUserWithoutStudentRole;
    }

    // Method for get assigned subject to a teacher
    public List<Subject> getAllAssignedSubjectToATeacher(String email) {
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

    public List<ClassRoutine> getAllClassRoutineByAssignSubject(List<Subject> allAssignedSubjectToATeacher) {
        List<ClassRoutine> routines = new ArrayList<>();
        List<ClassRoutine> routineList = routineService.getAllClassRoutine();
        for (Subject subject : allAssignedSubjectToATeacher) {
            for (ClassRoutine classRoutine : routineList) {
                if (subject.getSubjectClass().equals(classRoutine.getSubjectClass())) {
                    routines.add(classRoutine);
                }
            }
        }
        return routines;
    }

    public Set<String> getAllClassNameByAssignSubject(List<Subject> allAssignedSubjectToATeacher) {
        List<String> classList = new ArrayList<>();
        List<ClassRoutine> routineList = routineService.getAllClassRoutine();
        for (Subject subject : allAssignedSubjectToATeacher) {
            for (ClassRoutine classRoutine : routineList) {
                if (subject.getSubjectClass().equals(classRoutine.getSubjectClass())) {
                    classList.add(subject.getSubjectClass());
                }
            }
        }
        // Clear the duplication
        Set<String> set = new HashSet<>(classList);
        classList.addAll(set);
        return set;
    }

    public User getStudentByLoggedInformation(String name) {
        User user = userRepository.getUserByEmail(name);
        return user;
    }

    public User getUserBySubjectId(Integer subjectId) {
        List<User> allUser = getAllUserWithoutStudentRole();
        for(User u : allUser){
            for (Subject s : u.getSubjects()){
                if (s.getId().equals(subjectId)){
                    return u;
                }
            }
        }
        return allUser.get(0);
    }
}