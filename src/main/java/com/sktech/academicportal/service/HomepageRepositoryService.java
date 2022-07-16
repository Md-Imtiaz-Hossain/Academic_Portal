package com.sktech.academicportal.service;

import com.sktech.academicportal.entity.HomepageEntity;
import com.sktech.academicportal.repository.HomepageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomepageRepositoryService {
    @Autowired
    HomepageRepo homepageRepo;

    public List<HomepageEntity> getAll(){
        return homepageRepo.findAll();
    }

    public HomepageEntity getSectionData(String s){
        return homepageRepo.findBySection(s);
    }

    public void saveSection(HomepageEntity h){
        homepageRepo.save(h);
    }

    public void saveMultiple(List<HomepageEntity> h){
        homepageRepo.saveAll(h);
    }
    public void clearAll(){
        homepageRepo.deleteAll();
    }

    public void delete(HomepageEntity h){
        homepageRepo.delete(h);
    }

}
