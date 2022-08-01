package com.sktech.academicportal.service;


import com.sktech.academicportal.entity.*;
import com.sktech.academicportal.repositories.ResultRepository;
import com.sktech.academicportal.repositories.RoleRepository;
import com.sktech.academicportal.repositories.TeacherEvaluationRepository;
import com.sktech.academicportal.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class TeacherEvaluationService {

    @Autowired
    TeacherEvaluationRepository teacherEvaluationRepository;

    @Autowired
    UserService userService;


    // Save Users
    public TeacherEvaluation saveEvaluation(TeacherEvaluation evaluation) {
        return teacherEvaluationRepository.save(evaluation);
    }


    public Set<TeacherEvaluation> findAllReviewUsingPrincipal(Principal principal) {

        User userByEmail = userService.getUserByEmail(principal.getName());
        Integer userId = userByEmail.getId();
        List<TeacherEvaluation> evaluations = teacherEvaluationRepository.findAll();
        List<TeacherEvaluation> newEvaluations = new ArrayList<>();


        for (TeacherEvaluation te : evaluations){
            Integer reviewerId = te.getReviewerId();
            if (reviewerId == userId){
                newEvaluations.add(te);
            }
        }
        return new HashSet<>(newEvaluations);
    }

    public List<TeacherEvaluation> findAll() {
        return teacherEvaluationRepository.findAll();
    }

    public void deleteEvaluation(Integer id){
        teacherEvaluationRepository.deleteById(id);
    }

    public TeacherEvaluation findById(Integer reviewId) {
        return teacherEvaluationRepository.findById(reviewId).get();
    }
}