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
        return  publicFilesRepository.findAllByType(FileTypes.GalleryImage.type);
    }

    public List<PublicFiles> getAllPdfNotice(){
        return  publicFilesRepository.findAllByType(FileTypes.NoticePDF.type);
    }

    public List<PublicFiles> getAllWrittenNotice(){
        return  publicFilesRepository.findAllByType(FileTypes.NoticeWritten.type);
    }

    public List<PublicFiles> getAllNotices(){
        List<PublicFiles> allNotice = new ArrayList<>();
        allNotice.addAll(publicFilesRepository.findAllByType(FileTypes.NoticePDF.type));
        allNotice.addAll(publicFilesRepository.findAllByType(FileTypes.NoticeWritten.type));
        return  allNotice;
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
