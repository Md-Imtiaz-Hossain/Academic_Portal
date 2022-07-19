package com.sktech.academicportal.service;

import com.sktech.academicportal.entity.HomepageEntity;
import com.sktech.academicportal.repository.HomepageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomepageRepositoryService {
    @Autowired
    HomepageRepository homepageRepository;

    public List<HomepageEntity> getAll(){
        return homepageRepository.findAll();
    }

    public HomepageEntity getSectionData(String s){
        return homepageRepository.findBySection(s);
    }

    public void saveSection(HomepageEntity h){
        homepageRepository.save(h);
    }

    public void saveMultiple(List<HomepageEntity> h){
        homepageRepository.saveAll(h);
    }
    public void clearAll(){
        homepageRepository.deleteAll();
    }

    public void delete(HomepageEntity h){
        homepageRepository.delete(h);
    }

}
