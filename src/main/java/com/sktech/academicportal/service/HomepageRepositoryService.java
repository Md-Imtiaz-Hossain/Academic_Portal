package com.sktech.academicportal.service;

import com.sktech.academicportal.entity.HomepageEntity;
import com.sktech.academicportal.repositories.HomepageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HomepageRepositoryService {
    @Autowired
    HomepageRepository homepageRepository;

    public List<HomepageEntity> getAll(){
        return homepageRepository.findAll();
    }

    public List<HomepageEntity> getPublicPremade(){
        List<HomepageEntity> publicPremade = new ArrayList<>();
        for (HomepageEntity h:
             homepageRepository.findAll()) {
            if(h.getIsPublic() && !h.getIsCustom()) publicPremade.add(h);
        }
        return publicPremade;
    }

    public List<HomepageEntity> getPublicCustom(){
        List<HomepageEntity> publicCustom = new ArrayList<>();
        for (HomepageEntity h:
                homepageRepository.findAll()) {
            if(h.getIsPublic() && h.getIsCustom()) publicCustom.add(h);
        }
        return publicCustom;
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
