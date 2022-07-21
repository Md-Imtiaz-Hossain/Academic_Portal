package com.sktech.academicportal.controllers.classroutine;

import com.sktech.academicportal.entity.ClassRoutine;
import com.sktech.academicportal.enums.AcademicClass;
import com.sktech.academicportal.enums.WeekDay;
import com.sktech.academicportal.service.RoutineService;
import com.sktech.academicportal.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/routine")
public class Routine {

    @Autowired
    RoutineService routineService;

    @Autowired
    SubjectService subjectService;

    // ========================================   Routine , Routine add from All routine form. =============================

    // View all classes routine
    @GetMapping("/all-class")
    public String home(Model model) {
        model.addAttribute("pageTitle", "All classes routine");
        model.addAttribute("routineList", routineService.getAllClassRoutine());
        return "/classroutine/routine-all-classes";
    }

    // Add new routine from full listed.
    @GetMapping("/new")
    public String newRoutine(Model model) {
        model.addAttribute("pageTitle", "Routine Add");
        model.addAttribute("routine", new ClassRoutine());
        model.addAttribute("classList", AcademicClass.values());
        model.addAttribute("weekDays", WeekDay.values());
        model.addAttribute("subjects", subjectService.getAllSubject());
        return "/classroutine/new-routine";
    }

    // Add routine form process
    @PostMapping("/save")
    public String processForm(@ModelAttribute("routine") ClassRoutine classRoutine) {
        routineService.save(classRoutine);
        return "redirect:/routine/all-class";

    }


    // ========================================  Routine , Routine add from specific subject. =============================

    // View  classes routine to a specific class
    @GetMapping("/class/{classOf}")
    public String home(@PathVariable String classOf, Model model) {

        List<ClassRoutine> routineByClass = routineService.getRoutineByClass(classOf);

        model.addAttribute("pageTitle", "Class - " + classOf + "  routine");
        model.addAttribute("routineList", routineByClass);

        return "/classroutine/routine-using-class-name";
    }

}
