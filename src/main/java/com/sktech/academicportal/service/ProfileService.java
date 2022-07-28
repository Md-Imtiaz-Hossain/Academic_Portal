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

    public Profile getLoggedInUserProfile(Principal principal) {
        User user = userService.getUserByEmail(principal.getName());
        Integer userId = user.getId();
        List<Profile> profile = profileRepository.findAll();
        Profile p2 = null;

        for (Profile p : profile) {
            if (p.getUser().getId().equals(userId)) {
                p2 = new Profile(p.getId(), p.getFollowers(), p.getFollowing(),
                        p.getFriends(), p.getSkills(), p.getNote(), p.getUser());
            }
        }
        return p2;
    }

    public Optional<StudentResult> getResultById(Integer id) {
        return resultRepository.findById(id);
    }

    public void save(Profile s) {
        profileRepository.save(s);
    }


    public void deleteById(Integer id) {
        resultRepository.deleteById(id);
    }

    public List<StudentResult> getResultUsingSubjectId(int assignedSubjectId) {
        List<StudentResult> studentResults = resultRepository.findAll();

        List<StudentResult> studentResultsAll = new ArrayList<>();

        for (StudentResult studentResult : studentResults) {
            if (studentResult.getSubjectId() == assignedSubjectId) {
                studentResultsAll.add(studentResult);
            }
        }
        return studentResultsAll;
    }

    public List<StudentResult> getResultUsingUserEmail(String email) {

        List<Subject> subjectToATeacher = userService.getAllAssignedSubjectToATeacher(email);
        List<StudentResult> studentResults = resultRepository.findAll();

        List<StudentResult> studentResultsAll = new ArrayList<>();

        for (StudentResult studentResult : studentResults) {
            for (Subject subject : subjectToATeacher) {
                if (Objects.equals(studentResult.getSubjectId(), subject.getId())) {
                    studentResultsAll.add(studentResult);
                }
            }
        }
        return studentResultsAll;
    }
}
