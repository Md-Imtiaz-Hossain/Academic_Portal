package com.sktech.academicportal.controllers.global;

import com.sktech.academicportal.entity.PublicFiles;
import com.sktech.academicportal.enums.FileTypes;
import com.sktech.academicportal.helper.FileUploadUtil;
import com.sktech.academicportal.service.PublicFilesRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/upload")
public class FileUpload {
    @Autowired
    PublicFilesRepositoryService publicFilesRepositoryService;

    @GetMapping("/gallery")
    public String uploadImagePage(){
        return "uploadtogallery";
    }

    @GetMapping("/notice")
    public String uploadNoticePage(){
        return "uploadtonotice";
    }

    @GetMapping("/achievement")
    public String uploadAchivementPage(){
        return "uploadtoachievement";
    }

    @PostMapping("/galleryManage/save")
    public String uploadImage(@RequestParam("image")MultipartFile multipartFile,
                              @RequestParam("heading")String heading,
                              @RequestParam("description")String description,
                              @RequestParam(value = "isPublic", required = false)String isPublic) throws IOException{
        if(!multipartFile.isEmpty()){
            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            PublicFiles files = new PublicFiles();
//          ========Upload========
            String uploadDir = "images/gallery/";
            String updatedFileName = FileUploadUtil.saveFile(uploadDir, fileName, multipartFile, false);
//          ========Save to Database=======
            files.setPath(uploadDir+updatedFileName);
            files.setHeading(heading);
            files.setDescription(description);
            files.setType(FileTypes.GalleryImage.type);
            files.setIsPublic(isPublic != null);
            publicFilesRepositoryService.save(files);
        }else {

        }

        return "redirect:/galleryManage";
    }

    @PostMapping("/notice_PDF/save")
    public String uploadNotice(@RequestParam("file")MultipartFile multipartFile,
                              @RequestParam("heading")String heading,
                               @RequestParam(value = "isPublic", required = false)String isPublic) throws IOException{
        if(!multipartFile.isEmpty()){
            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            PublicFiles files = new PublicFiles();
//          ========Upload========
            String uploadDir = "files/notice/";
            String updatedFileName = FileUploadUtil.saveFile(uploadDir, fileName, multipartFile, false);
//          ========Save to Database=======
            files.setPath(uploadDir+updatedFileName);
            files.setHeading(heading);
            files.setDescription("");
            files.setType(FileTypes.NoticePDF.type);
            files.setIsPublic(isPublic != null);
            publicFilesRepositoryService.save(files);
        }else {

        }

        return "redirect:/noticeManage";
    }

    @PostMapping("/notice_written/save")
    public String uploadWrittenNotice(@RequestParam("heading")String heading,
                                      @RequestParam("description")String description,
                                      @RequestParam(value = "isPublic", required = false)String isPublic) throws IOException{


            PublicFiles files = new PublicFiles();
//          ========Upload========

//          ========Save to Database=======
            files.setHeading(heading);
            files.setDescription(description);
            files.setType(FileTypes.NoticeWritten.type);
            files.setIsPublic(isPublic != null);
            publicFilesRepositoryService.save(files);
            files.path = "/notices/view/" + files.id;
            publicFilesRepositoryService.save(files);

        return "redirect:/noticeManage";
    }

    @PostMapping("/achievement/save")
    public String uploadAchievement(@RequestParam("image")MultipartFile multipartFile,
                                    @RequestParam("heading")String heading,
                                    @RequestParam("description")String description,
                                    @RequestParam(value = "isPublic", required = false)String isPublic) throws IOException{
        if(!multipartFile.isEmpty()){
            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            PublicFiles files = new PublicFiles();
//          ========Upload========
            String uploadDir = "images/achievement/";
            String updatedFileName = FileUploadUtil.saveFile(uploadDir, fileName, multipartFile, false);
//          ========Save to Database=======
            files.setPath(uploadDir+updatedFileName);
            files.setHeading(heading);
            files.setDescription(description);
            files.setType(FileTypes.Achievement.type);
            files.setIsPublic(isPublic != null);
            publicFilesRepositoryService.save(files);
        }else {

        }


        return "redirect:/achievementManage";
    }
}
