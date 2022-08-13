package com.sktech.academicportal.controllers.classroutine;

import com.sktech.academicportal.entity.ClassRoutine;
import com.sktech.academicportal.entity.User;
import com.sktech.academicportal.enums.AcademicClass;
import com.sktech.academicportal.enums.WeekDay;
import com.sktech.academicportal.service.RoutineService;
import com.sktech.academicportal.service.SubjectService;
import com.sktech.academicportal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/routine")
public class AllClassRoutine {

    @Autowired
    SubjectService subjectService;
    @Autowired
    RoutineService routineService;
    @Autowired
    UserService userService;

    // Extra data go through model attribute before all other controller run.
    @ModelAttribute("loggedInUser")
    public User extraData(Principal principal) {
        return userService.getUserByEmail(principal.getName());
    }

    // View all classes routine
    @GetMapping("/all-class")
    public String home(Model model) {
        model.addAttribute("pageTitle", "All classes routine");
        model.addAttribute("routineList", routineService.getAllClassRoutine());
        return "allclassroutine/routine-all-classes";
    }

    // Add new routine from full listed.
    @GetMapping("/new")
    public String newRoutine(Model model) {
        model.addAttribute("pageTitle", "Routine Add");
        model.addAttribute("routine", new ClassRoutine());
        model.addAttribute("classList", AcademicClass.values());
        model.addAttribute("weekDays", WeekDay.values());
        model.addAttribute("subjects", subjectService.getAllSubject());
        return "allclassroutine/new-routine";
    }

    // Add routine form process
    @PostMapping("/save")
    public String processForm(@ModelAttribute("routine") ClassRoutine classRoutine) {
        routineService.save(classRoutine);
        return "redirect:/routine/all-class";
    }


    // Routine Update form
    @GetMapping("/edit/{id}")
    public String editRoutineForm(@PathVariable Integer id, Model model) {
        model.addAttribute("pageTitle", "Routine Update");
        model.addAttribute("routine", routineService.getRoutineById(id));
        model.addAttribute("classList", AcademicClass.values());
        model.addAttribute("weekDays", WeekDay.values());
        model.addAttribute("subjects", subjectService.getAllSubject());
        return "allclassroutine/routine-update-form";
    }


    // Process routine update
    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable Integer id, @ModelAttribute("routine") ClassRoutine routine) {
        ClassRoutine existingRoutine = routineService.getRoutineById(id);
        existingRoutine.setId(id);
        existingRoutine.setWeekDay(routine.getWeekDay());
        existingRoutine.setSubjectClass(routine.getSubjectClass());
        existingRoutine.setPeriod1st(routine.getPeriod1st());
        existingRoutine.setPeriod2nd(routine.getPeriod2nd());
        existingRoutine.setPeriod3rd(routine.getPeriod3rd());
        existingRoutine.setPeriod4th(routine.getPeriod4th());
        existingRoutine.setPeriod5th(routine.getPeriod5th());
        existingRoutine.setPeriod6th(routine.getPeriod6th());
        routineService.updateRoutine(existingRoutine);
        return "redirect:/routine/all-class";
    }

    // Delete routine
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Integer id) {
        routineService.deleteRoutineById(id);
        return "redirect:/routine/all-class";
    }



}
