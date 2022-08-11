package com.sktech.academicportal.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.sktech.academicportal.helper.Contact;
import com.sktech.academicportal.service.HomepageRepositoryService;
import com.sktech.academicportal.service.PublicFilesRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
    @Autowired
    HomepageRepositoryService hrs;
    @Autowired
    PublicFilesRepositoryService publicFilesRepositoryService;
    

    @GetMapping("/")
    public String viewHomePage(Model model) {
        if(hrs.getAll().isEmpty()) return "redirect:/homepage/init";
        System.out.println(publicFilesRepositoryService.getAllPublicNotices());
        model.addAttribute("sections", hrs.getAll());
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
        Contact ct = new Contact("a@a.com", "0011", "West");
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(ct);
        System.out.println(ct.getAddress());
        System.out.println(json);
        ObjectMapper mapper = new ObjectMapper();
//        mapper.readTree(json).get("phone");
        Contact cyt = mapper.readValue(json, Contact.class);
        System.out.println(cyt.getPhone());
        model.addAttribute("images", publicFilesRepositoryService.getAllPublicImages());
        return "carouselEditor";
    }

    @PostMapping("/carousel/update")
    public String manage(@RequestParam("imageList")String hello){
        System.out.println(hello);
        return "redirect:/carousel/update";
    }



}
