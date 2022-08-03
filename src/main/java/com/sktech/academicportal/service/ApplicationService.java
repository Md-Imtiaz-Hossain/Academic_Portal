package com.sktech.academicportal.service;


import com.sktech.academicportal.entity.AllApplication;
import com.sktech.academicportal.entity.TeacherEvaluation;
import com.sktech.academicportal.entity.User;
import com.sktech.academicportal.repositories.ApplicationRepository;
import com.sktech.academicportal.repositories.TeacherEvaluationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class ApplicationService {

    @Autowired
    TeacherEvaluationRepository teacherEvaluationRepository;

    @Autowired
    UserService userService;

    @Autowired
    ApplicationRepository applicationRepository;


    // Save Application
    public AllApplication saveApplication(AllApplication allApplication) {
        return applicationRepository.save(allApplication);
    }


    public List<AllApplication> findAll() {
        return applicationRepository.findAll();
    }

    public void deleteEvaluation(Integer id){
        teacherEvaluationRepository.deleteById(id);
    }

    public AllApplication findById(Integer applicationId) {
        return applicationRepository.findById(applicationId).get();
    }
}