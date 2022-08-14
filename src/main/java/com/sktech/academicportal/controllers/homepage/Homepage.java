package com.sktech.academicportal.controllers.homepage;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.sktech.academicportal.entity.HomepageEntity;
import com.sktech.academicportal.enums.PreBuiltSectionName;
import com.sktech.academicportal.helper.misc.Contact;
import com.sktech.academicportal.helper.misc.History;
import com.sktech.academicportal.helper.misc.ListOfNav;
import com.sktech.academicportal.helper.misc.NavBar;
import com.sktech.academicportal.service.HomepageRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/homepage")
public class Homepage {



    @Autowired
    HomepageRepositoryService homepageRepositoryService;

    @GetMapping("/all")
    public String viewAll(Model model) throws JsonProcessingException {
        HomepageEntity navbar = homepageRepositoryService.getSectionData(PreBuiltSectionName.NavBar.sectionName);
        HomepageEntity history = homepageRepositoryService.getSectionData(PreBuiltSectionName.History.sectionName);
        HomepageEntity contactus = homepageRepositoryService.getSectionData(PreBuiltSectionName.ContactUs.sectionName);
        model.addAttribute("carousel", homepageRepositoryService.getSectionData(PreBuiltSectionName.Carousel.sectionName));
        model.addAttribute("notice", homepageRepositoryService.getSectionData(PreBuiltSectionName.Notices.sectionName));
        model.addAttribute("achievement", homepageRepositoryService.getSectionData(PreBuiltSectionName.Achievements.sectionName));
        model.addAttribute("history", history);
        model.addAttribute("contactus", contactus);
        model.addAttribute("customs", homepageRepositoryService.getAllCustom());
        ObjectMapper objectMapper =new ObjectMapper();
        ListOfNav ls = new ListOfNav();
        if (navbar.getRawData() == null) {
            navbarUpdate(List.of("Home"), List.of("/"));
        }
        else ls = objectMapper.readValue(navbar.getRawData(), ListOfNav.class);
        History hs = new History();
        if (history.getRawData() == null) historyUpdate("https://www.youtube.com/watch?v=dQw4w9WgXcQ", "This is a description");
        else hs = objectMapper.readValue(history.getRawData(), History.class);

        Contact contact = new Contact();
        if(contactus.getRawData() == null)contactUpdate("a@a.com", "abc, xyz road", "001144");
        else contact = objectMapper.readValue(contactus.getRawData(), Contact.class);

        model.addAttribute("contact_data", contact);

        model.addAttribute("history_data", hs);
        model.addAttribute("navbar_items", ls.getEditableList());
        model.addAttribute("navbar_items_count", ls.getList().size());
        model.addAttribute("navigable_sections", homepageRepositoryService.getAllPublicSectionName());
        return "homepageALl";
    }


    @GetMapping("/init")
    public String initDefault(){
        List<HomepageEntity> default_ = List.of(new HomepageEntity(PreBuiltSectionName.Carousel.sectionName, null, true,  false),
                new HomepageEntity(PreBuiltSectionName.History.sectionName, null, true, false) ,
                new HomepageEntity(PreBuiltSectionName.Notices.sectionName, "null", true, false),
                new HomepageEntity(PreBuiltSectionName.Achievements.sectionName, null, true, false),
                new HomepageEntity(PreBuiltSectionName.NavBar.sectionName, null, true, false),
                new HomepageEntity(PreBuiltSectionName.ContactUs.sectionName, null, true, false)
                );
//        Clearing all previous data and resetting all to default
        homepageRepositoryService.clearAll();
        homepageRepositoryService.saveMultiple(default_);
        return "redirect:/";
    }

//    @PostMapping("/update")
//    public String updatePage()

    @GetMapping("/hide/{id}")
    public String hideSection(@PathVariable("id")Long id){
        HomepageEntity hs = homepageRepositoryService.getById(id);
        hs.setIsPublic(false);
        homepageRepositoryService.saveSection(hs);
        return "redirect:/homepage/all";
    }

    @GetMapping("/show/{id}")
    public String showSection(@PathVariable("id")Long id){
        HomepageEntity hs = homepageRepositoryService.getById(id);
        hs.setIsPublic(true);
        homepageRepositoryService.saveSection(hs);
        return "redirect:/homepage/all";
    }

    @PostMapping("/navbar/save")
    public String navbarUpdate(@RequestParam("navbar-text")List<String> text, @RequestParam("navbar-link")List<String> link) throws JsonProcessingException {
        HomepageEntity he = homepageRepositoryService.getSectionData(PreBuiltSectionName.NavBar.sectionName);
        ListOfNav listOfNav = new ListOfNav();
        for (int i=0; i < text.size(); i++) {
            NavBar navBar = new NavBar(text.get(i), link.get(i));
            listOfNav.add(navBar);
        }
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(listOfNav);
        he.setRawData(json);
        homepageRepositoryService.saveSection(he);
        return "redirect:/homepage/all";
    }

    @PostMapping("/history/save")
    public String historyUpdate(@RequestParam("videoId")String videoId, @RequestParam("description")String description) throws JsonProcessingException {
        HomepageEntity he = homepageRepositoryService.getSectionData(PreBuiltSectionName.History.sectionName);
        History history = new History(videoId, description);
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(history);
        he.setRawData(json);
        homepageRepositoryService.saveSection(he);
        return "redirect:/homepage/all";
    }

    @PostMapping("/notice/save")
    public String historyUpdate(@RequestParam("noticedescription")String description) {
        HomepageEntity he = homepageRepositoryService.getSectionData(PreBuiltSectionName.Notices.sectionName);
        he.setRawData(description);
        homepageRepositoryService.saveSection(he);
        return "redirect:/homepage/all";
    }

    @PostMapping("/contactinfo/save")
    public String contactUpdate(@RequestParam("email")String email, @RequestParam("address")String address, @RequestParam("phone")String phone) throws JsonProcessingException {
        HomepageEntity he = homepageRepositoryService.getSectionData(PreBuiltSectionName.ContactUs.sectionName);
        Contact contact = new Contact(email, address, phone);
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(contact);
        he.setRawData(json);
        homepageRepositoryService.saveSection(he);
        return "redirect:/homepage/all";
    }
}
