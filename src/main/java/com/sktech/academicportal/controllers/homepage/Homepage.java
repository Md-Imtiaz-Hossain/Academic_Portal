package com.sktech.academicportal.controllers.homepage;

import com.sktech.academicportal.entity.HomepageEntity;
import com.sktech.academicportal.enums.PreBuiltSectionName;
import com.sktech.academicportal.service.HomepageRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/homepage")
public class Homepage {


    private static final String history = "";
    private static final String achievement = "<h1>\n" +
            "      Page three\n" +
            "    </h1>";

    @Autowired
    HomepageRepositoryService homepageRepositoryService;

    @GetMapping("/all")
    public List<HomepageEntity> viewAll(){
        return null;
    }


    @GetMapping("/init")
    public String initDefault(){
        List<HomepageEntity> default_ = List.of(new HomepageEntity(PreBuiltSectionName.Carousel.sectionName, null, true,  false),
                new HomepageEntity(PreBuiltSectionName.History.sectionName, "https://www.youtube.com/watch?v=Nrb82_kqjDI", true, false) ,
                new HomepageEntity(PreBuiltSectionName.Notices.sectionName, "null", true, false),
                new HomepageEntity(PreBuiltSectionName.Achievements.sectionName, null, true, false));
//        Clearing all previous data and resetting all to default
        homepageRepositoryService.clearAll();
        homepageRepositoryService.saveMultiple(default_);
        return "redirect:/";
    }

//    @PostMapping("/update")
//    public String updatePage()
}
