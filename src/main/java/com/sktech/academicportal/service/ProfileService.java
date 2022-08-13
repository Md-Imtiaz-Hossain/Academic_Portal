package com.sktech.academicportal.service;

import com.sktech.academicportal.entity.Profile;
import com.sktech.academicportal.repositories.ProfileRepository;
import com.sktech.academicportal.repositories.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
