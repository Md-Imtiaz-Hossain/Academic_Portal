package com.sktech.academicportal.helper;

import com.sktech.academicportal.entity.PublicFiles;
import com.sktech.academicportal.enums.PreBuiltSectionName;
import com.sktech.academicportal.service.PublicFilesRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Controller
public class ProcessHomeData {
    @Autowired
    PublicFilesRepositoryService publicFilesRepositoryService;
    
    public String htmlData(String sectionName, String rawdata){
        String processedData="";
        if(sectionName.equals(PreBuiltSectionName.Carousel.sectionName)){
            StringToListConverter stringToListConverter = new StringToListConverter();
            List<Long> imageIds = stringToListConverter.convertToLongList(rawdata);
            List<String> imagePaths = new ArrayList<>();
            List<String> headings = new ArrayList<>();
            List<String> description = new ArrayList<>();
            for (Long imageId: imageIds
            ) {
                imagePaths.add(publicFilesRepositoryService.getById(imageId).path);
                headings.add(publicFilesRepositoryService.getById(imageId).heading);
                description.add(publicFilesRepositoryService.getById(imageId).description);
            }
            if(imageIds.isEmpty()) {
                imagePaths.add("https://picsum.photos/1200");
                headings.add("This is a heading");
                description.add("This is a description");
            }
            String part1 = "<div class=\"custom\">\n" +
                    "      <div id=\"welcomeImages\" class=\"carousel slide\" data-bs-ride=\"carousel\">\n" +
                    "        <div class=\"carousel-indicators\">\n" +
                    "          <button type=\"button\" data-bs-target=\"#welcomeImages\" data-bs-slide-to=\"0\" class=\"active\" aria-current=\"true\" aria-label=\"Slide 1\"></button>\n" +
                    "          <button type=\"button\" data-bs-target=\"#welcomeImages\" data-bs-slide-to=\"1\" aria-label=\"Slide 2\"></button>\n" +
                    "          <button type=\"button\" data-bs-target=\"#welcomeImages\" data-bs-slide-to=\"2\" aria-label=\"Slide 3\"></button>\n" +
                    "        </div>\n" +
                    "        <div class=\"carousel-inner\">\n";
            String part2 = "<div class=\"carousel-item active\">\n" +
                    "            <div class=\"custom_box\">\n" +
                    "              <img src=\""  + imagePaths.get(0) + "\" class=\"active\" alt=\"...\">\n" +
                    "            </div>\n" +
                    "            <div class=\"carousel-caption ps-2 pt-5 ps-sm-4 ps-md-5\">\n" +
                    "              <h5>"+headings.get(0)+"</h5>\n" +
                    "              <p>"+description.get(0)+"</p>\n" +
                    "            </div>\n" +
                    "          </div>\n" ;
            String part3 = "";
            for (int i = 1; i<imagePaths.size(); i++){
                part3 += "<div class=\"carousel-item\">\n" +
                        "            <div class=\"custom_box\">\n" +
                        "              <img src=\"";
                part3 += imagePaths.get(i);
                part3 += "\"  class=\"\" alt=\"...\">\n" +
                        "            </div>\n" +
                        "            <div class=\"carousel-caption ps-2 pt-5 ps-sm-4 ps-md-5\">\n" +
                        "              <h5>"+headings.get(i)+"</h5>\n" +
                        "              <p>"+description.get(i)+"</p>\n" +
                        "            </div>\n" +
                        "          </div>\n";
            }
            String part4 =
                    "        <div>\n" +
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
            processedData = part1 + part2 + part3 + part4;
        }
        if(sectionName.equals(PreBuiltSectionName.History.sectionName)){
            String videoId = "";
            if(rawdata == null){
                videoId = "dQw4w9WgXcQ";
            }else {
                videoId = rawdata.substring(rawdata.lastIndexOf('=')+1);
            }

            processedData =
                    "        <div style=\"display: flex; flex-wrap: wrap; align-items: center\">\n" +
                            "          <div class=\"ratio ratio-16x9\" style=\"flex: 1 1 70%\">\n" +
                            "            <iframe\n" +
                            "              style=\"padding: 5ch\"\n" +
                            "              height=\"100%\"\n" +
                            "              width=\"100%\"\n" +
                            "              src=\"https://www.youtube.com/embed/"+videoId+"\"\n" +
                            "              title=\"YouTube video player\"\n" +
                            "              frameborder=\"0\"\n" +
                            "              allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture\"\n" +
                            "              allowfullscreen\n" +
                            "            ></iframe>\n" +
                            "          </div>\n" +
                            "          <div style=\"flex: 1 1 30%; min-width: 25ch; padding: 5ch; color: white\">\n" +
                            "       <h2>History</h2>"+
                            "            Hello, where are you, are you there, i need you. Lorem ipsum, dolor\n" +
                            "            sit amet consectetur adipisicing elit. Vel debitis ipsum dolores\n" +
                            "            repellendus? Vero autem natus temporibus, necessitatibus tenetur\n" +
                            "            eveniet libero officia adipisci non, hic harum blanditiis et,\n" +
                            "            aliquam at? Lorem ipsum dolor sit amet consectetur adipisicing elit.\n" +
                            "            Recusandae nemo est vitae, voluptate velit omnis nobis animi\n" +
                            "            veritatis nostrum cum aspernatur, explicabo repudiandae unde\n" +
                            "            perspiciatis illum dolore dolores molestiae dignissimos.\n" +
                            "          </div>\n" +
                            "        </div>";
        }
        if(sectionName.equals(PreBuiltSectionName.Achievements.sectionName)){
            processedData = "<h1>\n" +
                    "      Page Two\n" +
                    "    </h1>";
        }
        if(sectionName.equals(PreBuiltSectionName.Teachers.sectionName)){

        }
        if(sectionName.equals(PreBuiltSectionName.ContactUs.sectionName)){

        }
        if(sectionName.equals(PreBuiltSectionName.Notices.sectionName)){
            List<PublicFiles> notices = publicFilesRepositoryService.getAllPublicNotices();
            System.out.println("hello");
            String part1 = "<div\n" +
                    "          style=\"\n" +
                    "            display: flex;\n" +
                    "            flex-wrap: wrap;\n" +
                    "            align-items: center;\n" +
                    "            color: white;\n" +
                    "          \"\n" +
                    "        >\n" +
                    "          <div style=\"flex: 1 1 50%; padding: 5ch\">\n" +
                    "            <h2>Notices</h2>\n" +
                    "            Hello, where are yyou, are you there, i need you. Lorem ipsum, dolor\n" +
                    "            sit amet consectetur adipisicing elit. Vel debitis ipsum dolores\n" +
                    "            repellendus? Vero autem natus temporibus, necessitatibus tenetur\n" +
                    "            eveniet libero officia adipisci non, hic harum blanditiis et,\n" +
                    "            aliquam at? Lorem ipsum dolor sit amet consectetur adipisicing elit.\n" +
                    "            Recusandae nemo est vitae, voluptate velit omnis nobis animi\n" +
                    "            veritatis nostrum cum aspernatur, explicabo repudiandae unde\n" +
                    "            perspiciatis illum dolore dolores molestiae dignissimos.\n" +
                    "          </div>\n" +
                    "          <div\n" +
                    "            class=\"overflow-auto\"\n" +
                    "            style=\"\n" +
                    "              flex: 1 1 50%;\n" +
                    "              min-width: 300px;\n" +
                    "              max-width: 600px;\n" +
                    "              height: 50vh;\n" +
                    "              margin: 4em;\n" +
                    "              background-color: rgb(209, 209, 209);\n" +
                    "            \"\n" +
                    "          >\n" +
                    "            <ul style=\"margin: 1em 1em; list-style-type: square; color: red\">";
//            String part2 = "";
//            for (PublicFiles p:
//                 notices) {
//                part2 += "<li style=\"padding: 1em 1em\">\n" +
//                        "                <a href=";
//                part2 += p.path;
//                part2 += "\" class=\"noticeLink\">\n";
//                part2 +=p.heading;
//                part2 +="</a\n" +
//                        "                >\n" +
//                        "              </li>";
//            }
//            String part3 = "</ul>\n" +
//                    "          </div>\n" +
//                    "        </div>";
            processedData = part1;

        }

        return processedData;
    }
}
