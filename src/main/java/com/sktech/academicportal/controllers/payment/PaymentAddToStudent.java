package com.sktech.academicportal.controllers.payment;

import com.sktech.academicportal.entity.ClassRoutine;
import com.sktech.academicportal.entity.PaymentScheme;
import com.sktech.academicportal.enums.AcademicClass;
import com.sktech.academicportal.enums.WeekDay;
import com.sktech.academicportal.service.PaymentSchemeService;
import com.sktech.academicportal.service.RoutineService;
import com.sktech.academicportal.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/payment-scheme")
public class PaymentAddToStudent {

    @Autowired
    SubjectService subjectService;

    @Autowired
    PaymentSchemeService paymentSchemeService;

    // View all payment scheme
    @GetMapping("/list")
    public String home(Model model) {
        model.addAttribute("pageTitle", "All payment scheme");
        model.addAttribute("paymentList", paymentSchemeService.getAllPaymentScheme());
        return "payment/scheme-list";
    }

    // Add new scheme from
    @GetMapping("/new")
    public String newScheme(Model model) {
        model.addAttribute("pageTitle", "Scheme Add");
        model.addAttribute("scheme", new PaymentScheme());
        model.addAttribute("classList", AcademicClass.values());
        return "payment/add-scheme";
    }

    // Scheme form process
    @PostMapping("/save")
    public String processForm(@ModelAttribute("scheme") PaymentScheme scheme) {
        paymentSchemeService.save(scheme);
        return "redirect:/payment-scheme/list";
    }


    // Scheme Update form
    @GetMapping("/edit/{id}")
    public String editSchemeForm(@PathVariable Integer id, Model model) {
        model.addAttribute("pageTitle", "Scheme Update");
        model.addAttribute("scheme", paymentSchemeService.getSchemeById(id));
        model.addAttribute("classList", AcademicClass.values());
        return "payment/scheme-update-form";
    }


    // Process the updated information after update button clicked.
    @PostMapping("/update/{id}")
    public String updateScheme(@PathVariable Integer id, @ModelAttribute("scheme") PaymentScheme scheme) {
        PaymentScheme existingScheme = paymentSchemeService.getSchemeById(id);
        existingScheme.setId(id);
        existingScheme.setPaymentName(scheme.getPaymentName());
        existingScheme.setPaymentClass(scheme.getPaymentClass());
        existingScheme.setAcademicYear(scheme.getAcademicYear());
        existingScheme.setAmount(scheme.getAmount());
        paymentSchemeService.updateScheme(existingScheme);
        return "redirect:/payment-scheme/list";
    }


    @GetMapping("/delete/{id}")
    public String deleteScheme(@PathVariable Integer id) {
        paymentSchemeService.deleteSchemeById(id);
        return "redirect:/payment-scheme/list";
    }



}
