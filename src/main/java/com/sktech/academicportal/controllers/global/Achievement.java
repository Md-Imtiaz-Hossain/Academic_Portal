package com.sktech.academicportal.controllers.global;

import com.sktech.academicportal.entity.PublicFiles;
import com.sktech.academicportal.helper.FileUploadUtil;
import com.sktech.academicportal.service.PublicFilesRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/achievementManage")
public class Achievement {
    @Autowired
    PublicFilesRepositoryService publicFilesRepositoryService;

    @GetMapping()
    public String achievement(Model model){
        model.addAttribute("images", publicFilesRepositoryService.getAllAchievements());
        return "achievementManage";
    }

    @PostMapping("/update/{imageId}")
    public  String achievementUpdate(@PathVariable Long imageId, @RequestParam("heading")String heading, @RequestParam("description")String description, @RequestParam(value = "isPublic", required = false)String isPublic){
        PublicFiles currentFile = publicFilesRepositoryService.getById(imageId);
        currentFile.setHeading(heading);
        currentFile.setDescription(description);
        currentFile.setIsPublic(isPublic != null);
        publicFilesRepositoryService.save(currentFile);
        return "redirect:/galleryManage";
    }
    @GetMapping("/delete/{imageId}")
    public  String achievementDelete(@PathVariable Long imageId){
        PublicFiles currentFile = publicFilesRepositoryService.getById(imageId);
        FileUploadUtil.deleteFile(currentFile.path);
        publicFilesRepositoryService.delete(currentFile);
        return "redirect:/galleryManage";
    }
}
