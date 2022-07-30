package com.sktech.academicportal.service;

import com.sktech.academicportal.entity.Profile;
import com.sktech.academicportal.entity.StudentResult;
import com.sktech.academicportal.entity.Subject;
import com.sktech.academicportal.entity.User;
import com.sktech.academicportal.repositories.ProfileRepository;
import com.sktech.academicportal.repositories.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProfileService {

    @Autowired
    ResultRepository resultRepository;

    @Autowired
    UserService userService;

    @Autowired
    ProfileRepository profileRepository;

    public List<Profile> getAllProfile() {
        return profileRepository.findAll();
    }


    public void save(Profile s) {
        profileRepository.save(s);
    }


    public void deleteById(Integer id) {
        profileRepository.deleteById(id);
    }

}
