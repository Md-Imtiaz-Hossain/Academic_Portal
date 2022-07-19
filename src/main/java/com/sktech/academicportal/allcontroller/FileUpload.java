package com.sktech.academicportal.allcontroller;

import com.sktech.academicportal.entity.PublicFiles;
import com.sktech.academicportal.enums.FileTypes;
import com.sktech.academicportal.helper.FileUploadUtil;
import com.sktech.academicportal.repository.PublicFilesRepository;
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
    PublicFilesRepository publicFilesRepository;

    @GetMapping("/gallery")
    public String uploadImagePage(){
        return "test";
    }

    @GetMapping("/notice")
    public String uploadNoticePage(){
        return "";
    }

    @PostMapping("/gallery/save")
    public String uploadImage(@RequestParam("image")MultipartFile multipartFile,
                              @RequestParam("heading")String heading,
                              @RequestParam("description")String description) throws IOException{
        if(!multipartFile.isEmpty()){
            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            PublicFiles files = new PublicFiles();
//          ========Upload========
            String uploadDir = "images/gallery/";
            FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
//          ========Save to Database=======
            files.setPath(uploadDir+fileName);
            files.setHeading(heading);
            files.setDescription(description);
            files.setType(FileTypes.GalleryImage.type);
            publicFilesRepository.save(files);
        }else {

        }

        return "redirect:/gallery";
    }

    @PostMapping("/notice_PDF/save")
    public String uploadNotice(@RequestParam("file")MultipartFile multipartFile,
                              @RequestParam("heading")String heading) throws IOException{
        if(!multipartFile.isEmpty()){
            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
            PublicFiles files = new PublicFiles();
//          ========Upload========
            String uploadDir = "files/notice/";
            FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
//          ========Save to Database=======
            files.setPath(uploadDir+fileName);
            files.setHeading(heading);
            files.setDescription("");
            files.setType(FileTypes.NoticePDF.type);
            publicFilesRepository.save(files);
        }else {

        }

        return "redirect:/notice";
    }

    @PostMapping("/notice_written/save")
    public String uploadWrittenNotice(@RequestParam("heading")String heading,
                                      @RequestParam("description")String description) throws IOException{


            PublicFiles files = new PublicFiles();
//          ========Upload========

//          ========Save to Database=======
            files.setPath("/notice/"+files.id);//Need to check for initial value
            files.setHeading(heading);
            files.setDescription(description);
            files.setType(FileTypes.GalleryImage.type);
            publicFilesRepository.save(files);


        return "redirect:/gallery";
    }
}
