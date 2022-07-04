package com.sktech.academicportal.allcontroller.homepage;

import com.sktech.academicportal.entity.HomepageEntity;
import com.sktech.academicportal.service.HomepageRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/homepage")
public class Homepage {

    @Autowired
    HomepageRepositoryService homepageRepositoryService;

    @GetMapping("/all")
    public List<HomepageEntity> viewAll(){
        return null;
//                homepageRepositoryService.getAll();
    }

    @GetMapping("/test")
    public String viewPage(Model model){
        HomepageEntity s = homepageRepositoryService.getSectionData("Contact");
//        System.out.println(homepageRepositoryService.getSectionData("Contact"));
        return null;
    }

//    @PostMapping("/update")
//    public String updatePage()
}
