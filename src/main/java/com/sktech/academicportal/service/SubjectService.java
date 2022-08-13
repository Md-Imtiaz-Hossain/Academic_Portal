package com.sktech.academicportal.service;

import com.sktech.academicportal.entity.Subject;
import com.sktech.academicportal.repositories.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class SubjectService {

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

    // Get a list of subject using class name
    public List<Subject> getAllSubjectByClass(String currentClass) {
        List<Subject> subjectList = subjectRepository.findAll();
        List<Subject> subjectListNew = new ArrayList<>();
        for (Subject s : subjectList) {
            if (s.getSubjectClass().equals(currentClass)) {
                subjectListNew.add(s);
            }
        }
        return subjectListNew;
    }
}