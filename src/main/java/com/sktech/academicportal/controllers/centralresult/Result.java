package com.sktech.academicportal.controllers.centralresult;

import com.sktech.academicportal.entity.StudentResult;
import com.sktech.academicportal.entity.User;
import com.sktech.academicportal.service.ResultService;
import com.sktech.academicportal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/central-result")
public class Result {

    @Autowired
    UserService userService;

    @Autowired
    ResultService resultService;


    @GetMapping("/list")
    public String resultHome(Model model) {
        model.addAttribute("user", userService.getAllUserByStudentRole());
        return "/centralresult/student-result-list";
    }


    /**
     * After add button clicked we got user id
     * Using the user id we find out the user and sent to the form through model attribute
     * And we send the Student Result for scanning the StudentResult variable
     */
    @GetMapping("/add/{id}")
    public String resultAdd(@PathVariable Integer id, Model model) {

        StudentResult results = new StudentResult();

        model.addAttribute("pageTitle", "Result Add");
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("result", results);

        return "/centralresult/student-result-add";
    }


    /**
     * After click button to save we got user id and StudentResult object
     * All result save to the resultDB
     * Take the newly saved result id and through id we find the whole Student result Optional data
     * And finally saved into the user variable which is studentResults.
     */
    @PostMapping("/addProcess/{id}")
    public String processResultAddForm(@PathVariable Integer id,
                                       @ModelAttribute("result") StudentResult result) {

        Integer studentsResultId = resultService.save(result);
        Optional<StudentResult> r = resultService.getResultById(studentsResultId);
        User user = userService.getUserById(id);
        user.setStudentResults(r.get());
        userService.saveUser(user);

        return "redirect:/central-result/list";
    }

    @GetMapping("/update/{id}")
    public String resultUpdate(@PathVariable Integer id, Model model) {

        User u = userService.getUserById(id);
        StudentResult studentResults = u.getStudentResults();

        model.addAttribute("pageTitle", "Result Add");
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("result", studentResults);

        return "/centralresult/student-result-update";
    }


    @PostMapping("/updateProcess/{id}")
    public String processResultUpdateForm(@PathVariable Integer id,
                                          @ModelAttribute("result") StudentResult result) {

        Integer studentResultId = resultService.save(result);
        Optional<StudentResult> resultById = resultService.getResultById(studentResultId);
        User user = userService.getUserById(id);
        user.setStudentResults(resultById.get());
        userService.saveUser(user);

        return "redirect:/central-result/list";
    }

}
