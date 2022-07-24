package com.sktech.academicportal.service;

import com.sktech.academicportal.entity.PublicFiles;
import com.sktech.academicportal.enums.FileTypes;
import com.sktech.academicportal.repository.PublicFilesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PublicFilesRepositoryService {

    @Autowired
    PublicFilesRepository publicFilesRepository;

    public List<PublicFiles> getAll(){
        return publicFilesRepository.findAll();
    }

    public List<PublicFiles> getAllImages(){
        List <PublicFiles> images = publicFilesRepository.findAllByType(FileTypes.GalleryImage.type);
        //Reverse order to maintain upload order
//        Collections.reverse(images); //Not working as intended
        return  images;
    }

    public List<PublicFiles> getAllPublicImages(){
        List <PublicFiles> images = new ArrayList<>();
        for (PublicFiles p: publicFilesRepository.findAllByType(FileTypes.GalleryImage.type)) {
            if(p.isPublic) images.add(p);
        }

        return  images;
    }

    public PublicFiles getById(Long id){
        return publicFilesRepository.findById(id).get();
    }

    public List<PublicFiles> getAllPdfNotice(){
        return  publicFilesRepository.findAllByType(FileTypes.NoticePDF.type);
    }

    public List<PublicFiles> getAllWrittenNotice(){
        return  publicFilesRepository.findAllByType(FileTypes.NoticeWritten.type);
    }

    public List<PublicFiles> getAllNotices(){
        List<PublicFiles> allNotice = new ArrayList<>();
        for (PublicFiles p: publicFilesRepository.findAll()) {
            if(!p.type.equals(FileTypes.GalleryImage.type)) allNotice.add(p);
        }
        //Reverse order to maintain upload order
//        Collections.reverse(allNotice); //Not working as intended
        return allNotice;
    }

    public List<PublicFiles> getAllPublicNotices(){
        List <PublicFiles> notices = new ArrayList<>();
        for (PublicFiles p: getAllNotices()) {
            if(p.isPublic) notices.add(p);
        }
        return  notices;
    }

    public void save(PublicFiles p){
        publicFilesRepository.save(p);
    }

    public void saveMultiple(List<PublicFiles> p){
        publicFilesRepository.saveAll(p);
    }

    public void delete(PublicFiles p){
        publicFilesRepository.delete(p);
    }

    public void deleteMultiple(List<PublicFiles> p){
        publicFilesRepository.deleteAll(p);
    }
}