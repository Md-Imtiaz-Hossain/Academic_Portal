package com.sktech.academicportal.allcontroller.homepage;

import com.sktech.academicportal.entity.HomepageEntity;
import com.sktech.academicportal.service.HomepageRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/homepage")
public class Homepage {
    private static final String home = "<div class=\"custom\">\n" +
            "      <div id=\"welcomeImages\" class=\"carousel slide\" data-bs-ride=\"carousel\">\n" +
            "        <div class=\"carousel-indicators\">\n" +
            "          <button type=\"button\" data-bs-target=\"#welcomeImages\" data-bs-slide-to=\"0\" class=\"active\" aria-current=\"true\" aria-label=\"Slide 1\"></button>\n" +
            "          <button type=\"button\" data-bs-target=\"#welcomeImages\" data-bs-slide-to=\"1\" aria-label=\"Slide 2\"></button>\n" +
            "          <button type=\"button\" data-bs-target=\"#welcomeImages\" data-bs-slide-to=\"2\" aria-label=\"Slide 3\"></button>\n" +
            "        </div>\n" +
            "        <div class=\"carousel-inner\">\n" +
            "          <div class=\"carousel-item active\">\n" +
            "            <div class=\"custom_box\">\n" +
            "              <img src=\"https://picsum.photos/1200?random=1\" class=\"active\" alt=\"...\">\n" +
            "            </div>\n" +
            "            <div class=\"carousel-caption ps-2 pt-5 ps-sm-4 ps-md-5\">\n" +
            "              <h5>1st Heading</h5>\n" +
            "              <p>This is the description</p>\n" +
            "            </div>\n" +
            "          </div>\n" +
            "          <div class=\"carousel-item\">\n" +
            "            <div class=\"custom_box\">\n" +
            "              <img src=\"https://picsum.photos/1200?random=2\"  class=\"\" alt=\"...\">\n" +
            "            </div>\n" +
            "            <div class=\"carousel-caption ps-2 pt-5 ps-sm-4 ps-md-5\">\n" +
            "              <h5>2nd Heading</h5>\n" +
            "              <p>This is the description</p>\n" +
            "            </div>\n" +
            "          </div>\n" +
            "          <div class=\"carousel-item\">\n" +
            "            <div class=\"custom_box\">\n" +
            "              <img src=\"https://picsum.photos/1200?random=3\" class=\"\" alt=\"...\">\n" +
            "              <div class=\"carousel-caption ps-2 pt-5 ps-sm-4 ps-md-5\">\n" +
            "                <h5>3rd Heading</h5>\n" +
            "                <p>This is the description</p>\n" +
            "              </div>\n" +
            "            </div>\n" +
            "          </div>\n" +
            "        </div>\n" +
            "        <button class=\"carousel-control-prev\" type=\"button\" data-bs-target=\"#welcomeImages\" data-bs-slide=\"prev\">\n" +
            "          <span class=\"carousel-control-prev-icon\" aria-hidden=\"true\"></span>\n" +
            "          <span class=\"visually-hidden\">Previous</span>\n" +
            "        </button>\n" +
            "        <button class=\"carousel-control-next\" type=\"button\" data-bs-target=\"#welcomeImages\" data-bs-slide=\"next\">\n" +
            "          <span class=\"carousel-control-next-icon\" aria-hidden=\"true\"></span>\n" +
            "          <span class=\"visually-hidden\">Next</span>\n" +
            "        </button>\n" +
            "      </div>\n" +
            "    </div>";

    private static final String history = "<h1>\n" +
            "      Page Two\n" +
            "    </h1>";
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
        List<HomepageEntity> default_ = List.of(new HomepageEntity(1L,"Home", home),
                new HomepageEntity(2L, "History", history),
                new HomepageEntity(3L, "Achievements", achievement)) ;
//        Clearing all previous data and resetting all to default
        homepageRepositoryService.clearAll();
        homepageRepositoryService.saveMultiple(default_);
        System.out.println(default_);
        return "redirect:/";
    }

//    @PostMapping("/update")
//    public String updatePage()
}
