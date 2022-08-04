package com.sktech.academicportal.service;


import com.sktech.academicportal.entity.AllApplication;
import com.sktech.academicportal.repositories.ApplicationRepository;
import com.sktech.academicportal.repositories.TeacherEvaluationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;


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

    public void deleteEvaluation(Integer id) {
        teacherEvaluationRepository.deleteById(id);
    }

    public AllApplication findById(Integer applicationId) {
        return applicationRepository.findById(applicationId).get();
    }


    public Integer rowCount_findAllApplicationWithoutSaveStatus = 0;
    public List<AllApplication> findAllApplicationWithoutSaveStatus() {
        List<AllApplication> allApplications = applicationRepository.findAll();
        List<AllApplication> applications = new ArrayList<>();
        for (AllApplication application : allApplications) {
            if (application.getApplicationStatus().equals("Send")) {
                applications.add(application);
                rowCount_findAllApplicationWithoutSaveStatus++;
            }
        }
        return applications;
    }

    public Integer rowCount_findAllApplicationWithSendFromMe = 0;
    public List<AllApplication> findAllApplicationWithSendFromMe(Principal principal, String send) {
        List<AllApplication> allApplications = applicationRepository.findAll();
        List<AllApplication> applications = new ArrayList<>();
        for (AllApplication application : allApplications) {
            if (application.getApplicationStatus().equals(send) && application.getSendFrom().equals(principal.getName())) {
                applications.add(application);
                rowCount_findAllApplicationWithSendFromMe++;
            }
        }
        return applications;
    }


    public Integer rowCount_findAllApplicationReceived = 0;
    public List<AllApplication> findAllApplicationReceived(Principal principal, String send) {
        List<AllApplication> allApplications = applicationRepository.findAll();
        List<AllApplication> applications = new ArrayList<>();
        for (AllApplication application : allApplications) {
            if (application.getApplicationStatus().equals(send) && application.getSendTo().equals(principal.getName())) {
                applications.add(application);
                rowCount_findAllApplicationReceived++;
            }
        }
        return applications;
    }

    public void deleteApplicationById(Integer applicationId) {
        applicationRepository.deleteById(applicationId);
    }
}