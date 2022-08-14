package com.sktech.academicportal.controllers.global;

import com.sktech.academicportal.entity.PublicFiles;
import com.sktech.academicportal.helper.FileUploadUtil;
import com.sktech.academicportal.service.PublicFilesRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/noticeManage")
public class Notice {
    @Autowired
    PublicFilesRepositoryService publicFilesRepositoryService;
    @GetMapping()
    public String notices(Model model){
        model.addAttribute("notices", publicFilesRepositoryService.getAllNotices());
        return "noticesManage";
    }

    @PostMapping("/update/pdf/{id}")
    public String updatePDFTitle(@PathVariable Long id, @RequestParam("heading")String heading, @RequestParam(value = "isPublic", required = false)String isPublic ){
        PublicFiles currentfile = publicFilesRepositoryService.getById(id);
        currentfile.heading = heading;
        currentfile.setIsPublic(isPublic != null);
        publicFilesRepositoryService.save(currentfile);
        return "redirect:/noticeManage";
    }

    @PostMapping("/update/written{id}")
    public String updateWrittenNotice(@PathVariable Long id, @RequestParam("heading")String heading, @RequestParam("description")String description, @RequestParam(value = "isPublic", required = false)String isPublic){
        PublicFiles currentfile = publicFilesRepositoryService.getById(id);
        currentfile.heading = heading;
        currentfile.description =description;
        currentfile.setIsPublic(isPublic != null);
        publicFilesRepositoryService.save(currentfile);
        return "redirect:/noticeManage";
    }

    @GetMapping("/delete/{id}")
    public String deleteFile(@PathVariable Long id){
        PublicFiles currentFile = publicFilesRepositoryService.getById(id);
        FileUploadUtil.deleteFile(currentFile.path);
        publicFilesRepositoryService.delete(currentFile);
        return "redirect:/noticeManage";
    }
}
