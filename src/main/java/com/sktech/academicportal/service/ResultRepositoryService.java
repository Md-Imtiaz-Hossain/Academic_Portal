package com.sktech.academicportal.service;

import com.sktech.academicportal.entity.StudentResult;
import com.sktech.academicportal.repository.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Optional<StudentResult> getResultById(Integer id) {
        return resultRepository.findById(id);
    }

    public Integer save(StudentResult s){
        StudentResult save = resultRepository.save(s);
        return save.getId();
    }


}
