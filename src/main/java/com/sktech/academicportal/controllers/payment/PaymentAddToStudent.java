package com.sktech.academicportal.controllers.payment;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/payment-add")
public class PaymentAddToStudent {

    @GetMapping("/to-student")
    public String addPayment(Model model, Principal principal){
        return "payment/payment-add-to-student";
    }

}
