package com.sktech.academicportal.service;

import com.sktech.academicportal.entity.StudentResult;
import com.sktech.academicportal.entity.Subject;
import com.sktech.academicportal.repositories.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ResultService {

    @Autowired
    ResultRepository resultRepository;

    @Autowired
    UserService userService;

    public List<StudentResult> getAllResul(){
        return resultRepository.findAll();
    }

    public Optional<StudentResult> getResultById(Integer id) {
        return resultRepository.findById(id);
    }

    public Integer save(StudentResult s){
        StudentResult save = resultRepository.save(s);
        return save.getId();
    }


    public void deleteById(Integer id) {
        resultRepository.deleteById(id);
    }

    public List<StudentResult> getResultUsingSubjectId(int assignedSubjectId) {
        List<StudentResult> studentResults = resultRepository.findAll();

        List<StudentResult> studentResultsAll = new ArrayList<>();

        for (StudentResult studentResult : studentResults){
            if (studentResult.getSubjectId()==assignedSubjectId){
                studentResultsAll.add(studentResult);
            }
        }
        return studentResultsAll;
    }

    public List<StudentResult> getResultUsingUserEmail(String email) {

        List<Subject> subjectToATeacher = userService.getAllAssignedSubjectToATeacher(email);
        List<StudentResult> studentResults = resultRepository.findAll();

        List<StudentResult> studentResultsAll = new ArrayList<>();

        for (StudentResult studentResult : studentResults){
            for (Subject subject: subjectToATeacher){
                if (Objects.equals(studentResult.getSubjectId(), subject.getId())){
                    studentResultsAll.add(studentResult);
                }
            }
        }
        return studentResultsAll;
    }
}
