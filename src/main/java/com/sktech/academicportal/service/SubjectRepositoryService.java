package com.sktech.academicportal.service;


import com.sktech.academicportal.entity.Subject;
import com.sktech.academicportal.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class SubjectRepositoryService {

    @Autowired
    SubjectRepository subjectRepository;


    // Get All Subject
    public List<Subject> getAllSubject() {
        return subjectRepository.findAll();
    }

    // Save Subject
    public Subject saveSubject(Subject subject) {
        return subjectRepository.save(subject);
    }

    // Get a single Subject by an id
    public Subject getSubjectById(Integer id) {
        return subjectRepository.findById(id).get();
    }

    // Update Subject information
    public Subject updateSubject(Subject subject) {
        return subjectRepository.save(subject);
    }

    // Delete Subject by their id
    public void deleteSubjectById(Integer id) {
        subjectRepository.deleteById(id);
    }


}