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
@RequestMapping("/specific-routine")
public class ClassWiseRoutine {

    @Autowired
    RoutineService routineService;

    @Autowired
    SubjectService subjectService;


    @GetMapping("/class/{classOf}")
    public String home(@PathVariable String classOf, Model model) {
        List<ClassRoutine> routineByClass = routineService.getRoutineByClass(classOf);
        model.addAttribute("pageTitle", "Class - " + classOf + "  routine");
        model.addAttribute("routineList", routineService.getRoutineByClass(classOf));
        model.addAttribute("className", classOf);
        return "/specificclassroutine/routine-using-class-name";
    }

    @GetMapping("/new/{className}")
    public String newRoutine(@PathVariable String className, Model model) {
        model.addAttribute("pageTitle", "Routine Add");
        model.addAttribute("routine", new ClassRoutine());
        model.addAttribute("className", className);
        model.addAttribute("weekDays", WeekDay.values());
        model.addAttribute("subjects", subjectService.getAllSubjectByClass(className));
        return "/specificclassroutine/new-routine";
    }

    @PostMapping("/save/{className}")
    public String processForm(@PathVariable String className,
                              @ModelAttribute("routine") ClassRoutine classRoutine) {
        classRoutine.setSubjectClass(className);
        routineService.save(classRoutine);
        return "redirect:/specific-routine/class/{className}";
    }

    @GetMapping("/edit/{id}")
    public String editRoutineForm(@PathVariable Integer id, Model model) {
        model.addAttribute("pageTitle", "Routine Update");
        model.addAttribute("routine", routineService.getRoutineById(id));
        model.addAttribute("classList", AcademicClass.values());
        model.addAttribute("weekDays", WeekDay.values());
        model.addAttribute("subjects", subjectService.getAllSubject());
        return "/allclassroutine/routine-update-form";
    }

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
        return "redirect:/specific-routine/all-class";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Integer id) {
        routineService.deleteRoutineById(id);
        return "redirect:/specific-routine/all-class";
    }


}
