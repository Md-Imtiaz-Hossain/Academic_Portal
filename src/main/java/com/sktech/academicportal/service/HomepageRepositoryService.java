package com.sktech.academicportal.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sktech.academicportal.controllers.homepage.Homepage;
import com.sktech.academicportal.entity.HomepageEntity;
import com.sktech.academicportal.entity.PublicFiles;
import com.sktech.academicportal.enums.PreBuiltSectionName;
import com.sktech.academicportal.helper.StringToListConverter;
import com.sktech.academicportal.helper.misc.Contact;
import com.sktech.academicportal.helper.misc.History;
import com.sktech.academicportal.helper.misc.ListOfNav;
import com.sktech.academicportal.helper.misc.NavBar;
import com.sktech.academicportal.repositories.HomepageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HomepageRepositoryService {
    @Autowired
    HomepageRepository homepageRepository;
    @Autowired
    PublicFilesRepositoryService publicFilesRepositoryService;

    public List<HomepageEntity> getAll(){
        return homepageRepository.findAll();
    }

    public List<HomepageEntity> getAllPublicExceptNav(){
        List<HomepageEntity> list = new ArrayList<>();
        for (HomepageEntity h: getAllPublic()
             ) {
            if(h.getSection().equals(PreBuiltSectionName.NavBar.sectionName)) continue;
            list.add(h);
        }
        return list;
    }

    public List<HomepageEntity> getPublicPremade(){
        List<HomepageEntity> publicPremade = new ArrayList<>();
        for (HomepageEntity h:
             homepageRepository.findAll()) {
            if(h.getIsPublic() && !h.getIsCustom()) publicPremade.add(h);
        }
        return publicPremade;
    }

    public List<HomepageEntity> getAllCustom(){
        List<HomepageEntity> Custom = new ArrayList<>();
        for (HomepageEntity h:
                homepageRepository.findAll()) {
            if(h.getIsCustom()) Custom.add(h);
        }
        return Custom;
    }
    public HomepageEntity getById(Long id){
        return homepageRepository.findById(id).get();
    }
    public List<HomepageEntity> getPublicCustom(){
        List<HomepageEntity> publicCustom = new ArrayList<>();
        for (HomepageEntity h:
                homepageRepository.findAll()) {
            if(h.getIsPublic() && h.getIsCustom()) publicCustom.add(h);
        }
        return publicCustom;
    }

    public HomepageEntity getSectionData(String s){
        return homepageRepository.findBySection(s);
    }

    public List<HomepageEntity> getAllPublic(){
        List<HomepageEntity> list = new ArrayList<>();
        list.addAll(getPublicPremade());
        list.addAll(getPublicCustom());
        return list;
    }

    public List<String> getAllPublicSectionName(){
        List<String> names = new ArrayList<>();
        for (HomepageEntity h: getAllPublic()
             ) {
            names.add(h.getSection());
        }
        return names;
    }
    public void refreshProcessedData() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        for (HomepageEntity h: homepageRepository.findAll()
             ) {
            String processedData = "";
            if(h.getSection().equals(PreBuiltSectionName.NavBar.sectionName)){
                processedData += "<nav class=\"navbar sticky-top navbar-expand-lg navbar-dark bg-dark\">\n" +
                        "    <div class=\"container-fluid\">+ <a class=\"navbar-brand\" href=\"/\">XYZ School</a>\n" +
                        "      <button class=\"navbar-toggler\" type=\"button\" data-bs-toggle=\"collapse\" data-bs-target=\"#navbarSupportedContent\" aria-controls=\"navbarSupportedContent\" aria-expanded=\"false\" aria-label=\"Toggle navigation\">\n" +
                        "        <span class=\"navbar-toggler-icon\"></span>\n" +
                        "      </button> <div class=\"collapse navbar-collapse\" id=\"navbarSupportedContent\">\n" +
                        "        <ul class=\"navbar-nav me-auto mb-2 mb-lg-0\">";
                ListOfNav ls = new ListOfNav();
                if (!(h.getRawData()==null)){
                    ls = objectMapper.readValue(h.getRawData(), ListOfNav.class);
                    for (NavBar n : ls.getList()
                    ) {
                        processedData += "<li class=\"nav-item\">";
                        processedData += "<a class=\"nav-link\" href=\"" + n.getPath() + "\">" + n.getNavText() + "</a>";
                        processedData += "</li>";
                    }
                }
                processedData+= "</ul>\n" +
                        "        <a href=\"/login\" class=\"btn btn-outline-success\">Login</a>\n" +
                        "      </div>\n" +
                        "    </div>\n" +
                        "  </nav>";
            }
            if (h.getSection().equals(PreBuiltSectionName.Carousel.sectionName)) {
                StringToListConverter stringToListConverter = new StringToListConverter();
                List<Long> imageIds = stringToListConverter.convertToLongList(h.getRawData());
                List<String> imagePaths = new ArrayList<>();
                List<String> headings = new ArrayList<>();
                List<String> description = new ArrayList<>();
                for (Long imageId : imageIds
                ) {
                    if(!publicFilesRepositoryService.getById(imageId).isPublic) continue;
                    imagePaths.add(publicFilesRepositoryService.getById(imageId).path);
                    headings.add(publicFilesRepositoryService.getById(imageId).heading);
                    description.add(publicFilesRepositoryService.getById(imageId).description);
                }
                if (imagePaths.isEmpty()) {
                    imagePaths.add("https://picsum.photos/1200");
                    headings.add("This is a heading");
                    description.add("This is a description");
                }
                String part1 = "<div class=\"custom\">\n" +
                        "      <div id=\"welcomeImages\" class=\"carousel slide\" data-bs-ride=\"carousel\">\n" +
                        "        <div class=\"carousel-indicators\">\n";
                String btns = "";
                for (int i = 0; i < imagePaths.size(); i++) {
                    btns += "<button type=\"button\" data-bs-target=\"#welcomeImages\" data-bs-slide-to=\"" + i + "\" aria-label=\"Slide " + i + "\"";
                    if (i == 0) btns += "class=\"active\" aria-current=\"true\"";
                    btns += "></button>\n";
                }

                part1 += btns + "        </div>\n" +
                        "        <div class=\"carousel-inner\">\n";
                String part2 = "<div class=\"carousel-item active\">\n" +
                        "            <div class=\"custom_box\">\n" +
                        "              <img src=\"" + imagePaths.get(0) + "\" class=\"active\" alt=\"...\">\n" +
                        "            </div>\n" +
                        "            <div class=\"carousel-caption ps-2 pt-5 ps-sm-4 ps-md-5\">\n" +
                        "              <h5>" + headings.get(0) + "</h5>\n" +
                        "              <p>" + description.get(0) + "</p>\n" +
                        "            </div>\n" +
                        "          </div>\n";
                String part3 = "";
                for (int i = 1; i < imagePaths.size(); i++) {
                    part3 += "<div class=\"carousel-item\">\n" +
                            "            <div class=\"custom_box\">\n" +
                            "              <img src=\"";
                    part3 += imagePaths.get(i);
                    part3 += "\"  class=\"\" alt=\"...\">\n" +
                            "            </div>\n" +
                            "            <div class=\"carousel-caption ps-2 pt-5 ps-sm-4 ps-md-5\">\n" +
                            "              <h5>" + headings.get(i) + "</h5>\n" +
                            "              <p>" + description.get(i) + "</p>\n" +
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
            if (h.getSection().equals(PreBuiltSectionName.History.sectionName)) {
                History history=new History();
                String videoId = "";
                if (h.getRawData() == null) {
                    videoId = "dQw4w9WgXcQ";
                    history.setVideoId("dQw4w9WgXcQ");
                    history.setDescription("Lorem ipsum, dolor " +
                            "                                            sit amet consectetur adipisicing elit. Vel debitis ipsum dolores " +
                            "                                            repellendus? Vero autem natus temporibus, necessitatibus tenetur" +
                            "                                           eveniet libero officia adipisci non, hic harum blanditiis et, " +
                            "                                            aliquam at? Lorem ipsum dolor sit amet consectetur adipisicing elit." +
                            "                                            Recusandae nemo est vitae, voluptate velit omnis nobis animi" +
                            "                                            veritatis nostrum cum aspernatur, explicabo repudiandae unde" +
                            "                                            perspiciatis illum dolore dolores molestiae dignissimos.");
                } else {
                    history = objectMapper.readValue(h.getRawData(), History.class);
                    videoId = history.getVideoId().substring(history.getVideoId().lastIndexOf('=') + 1);
                }

                processedData =
                        "        <div style=\"display: flex; flex-wrap: wrap; align-items: center\">\n" +
                                "          <div class=\"ratio ratio-16x9\" style=\"flex: 1 1 70%\">\n" +
                                "            <iframe\n" +
                                "              style=\"padding: 5ch\"\n" +
                                "              height=\"100%\"\n" +
                                "              width=\"100%\"\n" +
                                "              src=\"https://www.youtube.com/embed/" + videoId + "\"\n" +
                                "              title=\"YouTube video player\"\n" +
                                "              frameborder=\"0\"\n" +
                                "              allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture\"\n" +
                                "              allowfullscreen\n" +
                                "            ></iframe>\n" +
                                "          </div>\n" +
                                "          <div  class=\"fadeTextAnim\" style=\"flex: 1 1 30%; min-width: 40ch; padding: 5ch; color: white\">\n" +
                                "       <h2>History</h2>" +
                                "            <div>"+history.getDescription()+"</div>\n" +
                                "          </div>\n" +
                                "        </div>";
            }
            if (h.getSection().equals(PreBuiltSectionName.Achievements.sectionName)) {
                StringToListConverter stringToListConverter = new StringToListConverter();
                List<Long> imageIds = stringToListConverter.convertToLongList(h.getRawData());
                processedData = "";
                List<PublicFiles> publicFiles=publicFilesRepositoryService.getMultipleById(imageIds);
                processedData += "<div\n" +
                        "          style=\"\n" +
                        "            display: flex;\n" +
                        "            flex-direction: column;\n" +
                        "            flex-wrap: nowrap;\n" +
                        "            justify-content: flex-start;\n" +
                        "            max-height: calc(100vh-56px);\n" +
                        "          \"\n" +
                        "        >\n" +
                        "          <h2 style=\"color: blue\">Achievements</h2>";
                for (PublicFiles p: publicFiles
                     ) {
                    processedData += "<div class=\"card mb-3\" style=\"max-width: 540px;\">\n" +
                            "            <div class=\"row g-0\">\n" +
                            "              <div class=\"col-4\">\n" +
                            "                <img\n" +
                            "                  src=\"../"+p.path+"\"\n" +
                            "                  class=\"img-fluid rounded-start\"\n" +
                            "                  alt=\"...\"\n" +
                            "                />\n" +
                            "              </div>\n" +
                            "              <div class=\"col-8\">\n" +
                            "                <div class=\"card-body\">\n" +
                            "                  <h5 class=\"card-title\">"+p.heading+"</h5>\n" +
                            "                  <p class=\"card-text\">\n" +
                                                p.description+
                            "                  </p>\n" +
                            "                </div>\n" +
                            "              </div>\n" +
                            "            </div>\n" +
                            "          </div>";
                }
                processedData+="<a href=\"/allAchievements\" style=\"text-decoration: none\">See more</a>\n" +
                        "        </div>";
            }

            if (h.getSection().equals(PreBuiltSectionName.ContactUs.sectionName)) {
                Contact contact = new Contact();
                if(h.getRawData()== null) contact = new Contact("a@a.com", "abc, xyz road", "001144");
                else contact = objectMapper.readValue(h.getRawData(), Contact.class);
                processedData += "<div class=\"card text-dark bg-info mb-3\" style=\"min-width: 25rem\">\n" +
                        "          <div class=\"card-header\">Contact Information</div>\n" +
                        "          <div class=\"card-body\">\n" +
                        "            <h5 class=\"card-title\">You can Contact Us at:</h5>\n" +
                        "            <p class=\"card-text\">\n" +
                        "              Email: "+contact.getEmail()+"<br/>\n" +
                        "              Address:"+contact.getAddress()+"<br/>\n" +
                        "              Phone: "+contact.getPhone()+"\n" +
                        "            </p>\n" +
                        "          </div>\n" +
                        "        </div>";
            }
            if (h.getSection().equals(PreBuiltSectionName.Notices.sectionName)) {
                String notice_desc = h.getRawData();
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
                                    notice_desc +
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
                String part2 = "";
                for (PublicFiles p :
                        notices) {
                    part2 += "<li style=\"padding: 1em 1em\">\n" +
                            "                <a href=\"";
                    part2 += p.path;
                    part2 += "\" class=\"noticeLink\">\n";
                    part2 += p.heading;
                    part2 += "</a>\n" +
                            "</li>";
                }
                String part3 = "</ul>\n" +
                        "          </div>\n" +
                        "        </div>";
                processedData = part1 + part2 + part3;
            }
            h.setProcessedData(processedData);
            saveSection(h);
        }
    }
    public void saveSection(HomepageEntity h){
        homepageRepository.save(h);
    }

    public void saveMultiple(List<HomepageEntity> h){
        homepageRepository.saveAll(h);
    }
    public void clearAll(){
        homepageRepository.deleteAll();
    }

    public void delete(HomepageEntity h){
        homepageRepository.delete(h);
    }

}
