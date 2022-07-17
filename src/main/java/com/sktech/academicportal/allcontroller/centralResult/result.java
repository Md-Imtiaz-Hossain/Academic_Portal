package com.sktech.academicportal.allcontroller.centralResult;

import com.sktech.academicportal.entity.StudentResult;
import com.sktech.academicportal.entity.User;
import com.sktech.academicportal.repository.UserRepository;
import com.sktech.academicportal.service.ResultRepositoryService;
import com.sktech.academicportal.service.UserRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/central-result")
public class result {

    @Autowired
    UserRepositoryService userRepositoryService;

    @Autowired
    ResultRepositoryService resultRepositoryService;


    @GetMapping("/list")
    public String resultHome(Model model) {
        model.addAttribute("user", userRepositoryService.getAllUserByStudentRole());
        return "/CentralResult/student-result-list";
    }


    @GetMapping("/add/{id}")
    public String resultAdd(@PathVariable Integer id, Model model) {

        StudentResult results = new StudentResult();

        model.addAttribute("pageTitle", "Result Add");
        model.addAttribute("user", userRepositoryService.getUserById(id));
        model.addAttribute("result", results);

        return "/CentralResult/student-result-add";
    }


    @PostMapping("/addProcess/{id}")
    public String processResultAddForm(@PathVariable Integer id,
                                       @ModelAttribute("result") StudentResult result) {

        Integer studentsResultId = resultRepositoryService.save(result);
        Optional<StudentResult> r = resultRepositoryService.getResultById(studentsResultId);
        User user = userRepositoryService.getUserById(id);
        user.setStudentResults(r.get());
        userRepositoryService.saveUser(user);

        return "redirect:/central-result/list";
    }

    @GetMapping("/update/{id}")
    public String resultUpdate(@PathVariable Integer id, Model model) {

        User u = userRepositoryService.getUserById(id);
        StudentResult studentResults = u.getStudentResults();

        model.addAttribute("pageTitle", "Result Add");
        model.addAttribute("user", userRepositoryService.getUserById(id));
        model.addAttribute("result", studentResults);

        return "/CentralResult/student-result-update";
    }


    @PostMapping("/updateProcess/{id}")
    public String processResultUpdateForm(@PathVariable Integer id,
                                          @ModelAttribute("result") StudentResult result) {

        Integer studentResultId = resultRepositoryService.save(result);
        Optional<StudentResult> resultById = resultRepositoryService.getResultById(studentResultId);
        User user = userRepositoryService.getUserById(id);
        user.setStudentResults(resultById.get());
        userRepositoryService.saveUser(user);

        return "redirect:/central-result/list";
    }

}
