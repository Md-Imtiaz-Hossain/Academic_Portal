package com.sktech.academicportal.controllers.studentportal;


import com.sktech.academicportal.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.Access;
import java.security.Principal;

@Controller
@RequestMapping("/report-card")
public class ReportCard {

    @Autowired
    ResultService resultService;

    @GetMapping("/view")
    public String cardHome(Model model, Principal principal){
        Float totalMarkOfAllSubject = resultService.getTotalMarkOfAllSubject();
        Float avgMarkOfAllSubject = totalMarkOfAllSubject/ resultService.totalSubject;
        String totalGradeOfAllSubject = resultService.getGradesUsingMark(Math.round(avgMarkOfAllSubject));
        model.addAttribute("pageTitle", "Mark List with user and subject");
        model.addAttribute("mark", resultService.getAllResulWithGrade());
        model.addAttribute("totalMarkOfAllSubject", totalMarkOfAllSubject);
        model.addAttribute("totalGradeOfAllSubject", totalGradeOfAllSubject);
        return "studentportal/student-report-card";
    }

}
