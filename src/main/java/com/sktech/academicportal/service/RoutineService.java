package com.sktech.academicportal.service;

import com.sktech.academicportal.entity.ClassRoutine;
import com.sktech.academicportal.entity.Subject;
import com.sktech.academicportal.repositories.RoutineRepository;
import com.sktech.academicportal.repositories.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class RoutineService {

    @Autowired
    SubjectRepository subjectRepository;

    @Autowired
    RoutineRepository routineRepository;


    // Get All Subject
    public List<ClassRoutine> getAllClassRoutine() {
        return routineRepository.findAll();
    }


    public List<ClassRoutine> getRoutineByClass(String classOf) throws NullPointerException{

        List<ClassRoutine> routineRepositoryAll = routineRepository.findAll();
        List<ClassRoutine> routineRepositoryAllNew = new ArrayList<>();

        for (ClassRoutine cr : routineRepositoryAll){
            String subjectClass = String.valueOf(cr.getSubjectClass());
            if (subjectClass.equals(classOf)){
                routineRepositoryAllNew.add(cr);
            }
        }
        return routineRepositoryAllNew;
    }

    public void save(ClassRoutine classRoutine) {
        routineRepository.save(classRoutine);
    }


    public void deleteRoutineById(Integer id) {
        routineRepository.deleteById(id);
    }


    public ClassRoutine getRoutineById(Integer id) {
        return routineRepository.findById(id).get();
    }

    public void updateRoutine(ClassRoutine existingRoutine) {
        routineRepository.save(existingRoutine);
    }
}