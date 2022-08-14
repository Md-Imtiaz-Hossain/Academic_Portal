package com.sktech.academicportal.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.sktech.academicportal.entity.HomepageEntity;
import com.sktech.academicportal.entity.PublicFiles;
import com.sktech.academicportal.enums.PreBuiltSectionName;
import com.sktech.academicportal.helper.StringToListConverter;
import com.sktech.academicportal.helper.misc.Contact;
import com.sktech.academicportal.service.HomepageRepositoryService;
import com.sktech.academicportal.service.PublicFilesRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {
    @Autowired
    HomepageRepositoryService hrs;
    @Autowired
    PublicFilesRepositoryService publicFilesRepositoryService;
    

    @GetMapping("/")
    public String viewHomePage(Model model) throws JsonProcessingException {
        if(hrs.getAll().isEmpty()) return "redirect:/homepage/init";
        hrs.refreshProcessedData();
        model.addAttribute("navbar", hrs.getSectionData(PreBuiltSectionName.NavBar.sectionName));
        model.addAttribute("sections", hrs.getAllPublicExceptNav());
        return "index";
    }

    @GetMapping("/login")
    public String viewLoginPage() {
        return "login";
    }

    @GetMapping("/after-login-dashboard")
    public String afterLogin(Model model) {
        return "/after-login-dashboard";
    }

    @GetMapping("/edit/carousel")
    public String carouselEdit(Model model) throws JsonProcessingException {
        List<Long> imageids = new StringToListConverter().convertToLongList(hrs.getSectionData(PreBuiltSectionName.Carousel.sectionName).getRawData());
        List<PublicFiles> publicFiles = publicFilesRepositoryService.getMultipleById(imageids);
        String data = "";
        for (int i = 0; i < imageids.size(); i++) {
            Double d = Math.random()*10000;
            String divId = "image" + publicFiles.get(i).id.toString()+d.longValue();
            data += "<div id=\""+divId+"\" onclick=\"removeImage("+divId+")\"><div class=\"customImageCard\" style=\"position: relative\"><img src=\"../"+publicFiles.get(i).path+"\" class=\"rounded-circle p-2 image\" style=\"width: auto; height: 150px\">\n" +
                    "                          <div class=\"middle\">\n" +
                    "                                    <div class=\"text\" style=\"background: none\">Remove</div>\n" +
                    "                          </div>\n" +
                    "\n" +
                    "                      <input value=\""+publicFiles.get(i).id+"\" name=\"imageList\" hidden></div></div>";
        }
        model.addAttribute("alreadySelectedSection", data);
        model.addAttribute("savedImagesCount", imageids.size());
        model.addAttribute("images", publicFilesRepositoryService.getAllPublicImages());
        return "carouselEditor";
    }

    @GetMapping("/edit/achievements")
    public String achievementEdit(Model model) {

        List<Long> imageids = new StringToListConverter().convertToLongList(hrs.getSectionData(PreBuiltSectionName.Achievements.sectionName).getRawData());
        List<PublicFiles> publicFiles = publicFilesRepositoryService.getMultipleById(imageids);
        String data = "";
        for (int i = 0; i < imageids.size(); i++) {
            Double d = Math.random()*10000;
            String divId = "image" + publicFiles.get(i).id.toString()+d.longValue();
            data += "<div id=\""+divId+"\" onclick=\"removeImage("+divId+")\"><div class=\"customImageCard\" style=\"position: relative\"><img src=\"../"+publicFiles.get(i).path+"\" class=\"rounded-circle p-2 image\" style=\"width: auto; height: 150px\">\n" +
                    "                          <div class=\"middle\">\n" +
                    "                                    <div class=\"text\" style=\"background: none\">Remove</div>\n" +
                    "                          </div>\n" +
                    "\n" +
                    "                      <input value=\""+publicFiles.get(i).id+"\" name=\"imageList\" hidden></div></div>";
        }
        model.addAttribute("alreadySelectedSection", data);
        model.addAttribute("savedImagesCount", imageids.size());
        model.addAttribute("images", publicFilesRepositoryService.getAllPublicAchievements());
        return "achievementEditor";
    }

    @PostMapping("/carousel/update")
    public String manageCarousel(@RequestParam("imageList")String imageList){
        HomepageEntity h = hrs.getSectionData(PreBuiltSectionName.Carousel.sectionName);
        h.setRawData(imageList);
        hrs.saveSection(h);
        return "redirect:/edit/carousel";
    }
    @PostMapping("/achievement/update")
    public String manageAchievement(@RequestParam("imageList")String imageList){
        HomepageEntity h = hrs.getSectionData(PreBuiltSectionName.Achievements.sectionName);
        h.setRawData(imageList);
        hrs.saveSection(h);
        return "redirect:/edit/achievements";
    }

    @GetMapping("/allAchievements")
    public String showAllAchievements(Model model){
        model.addAttribute("images", publicFilesRepositoryService.getAllPublicAchievements());
        return "publicAllAchievement";
    }

    @GetMapping("notices/view/{noticeId}")
    public String showNotice(Model model, @PathVariable("noticeId") Long noticeId){
        PublicFiles notice = publicFilesRepositoryService.getById(noticeId);
        model.addAttribute("notice", notice);
        return "WrittenNotice";
    }


}
