package com.sktech.academicportal.controllers;

import com.sktech.academicportal.entity.PublicFiles;
import com.sktech.academicportal.helper.FileUploadUtil;
import com.sktech.academicportal.service.PublicFilesRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/galleryManage")
public class Gallery {
    @Autowired
    PublicFilesRepositoryService publicFilesRepositoryService;

    @GetMapping()
    public String gallery(Model model){
        model.addAttribute("images", publicFilesRepositoryService.getAllImages());
        return "galleryManage";
    }

    @PostMapping("/update/{imageId}")
    public  String galleryUpdate(@PathVariable Long imageId, @RequestParam("heading")String heading, @RequestParam("description")String description, @RequestParam(value = "isPublic", required = false)String isPublic){
        PublicFiles currentFile = publicFilesRepositoryService.getById(imageId);
        currentFile.setHeading(heading);
        currentFile.setDescription(description);
        currentFile.setIsPublic(isPublic != null);
        publicFilesRepositoryService.save(currentFile);
        return "redirect:/galleryManage";
    }
    @GetMapping("/delete/{imageId}")
    public  String galleryDelete(@PathVariable Long imageId){
        PublicFiles currentFile = publicFilesRepositoryService.getById(imageId);
        FileUploadUtil.deleteFile(currentFile.path);
        publicFilesRepositoryService.delete(currentFile);
        return "redirect:/galleryManage";
    }
}
