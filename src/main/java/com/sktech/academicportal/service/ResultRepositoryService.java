package com.sktech.academicportal.service;

import com.sktech.academicportal.entity.StudentResult;
import com.sktech.academicportal.entity.User;
import com.sktech.academicportal.repository.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResultRepositoryService {

    @Autowired
    ResultRepository resultRepository;

    public List<StudentResult> getAllResul(){
        return resultRepository.findAll();
    }

    public List<StudentResult> getAllResult() {
        return resultRepository.findAll();
    }

}
