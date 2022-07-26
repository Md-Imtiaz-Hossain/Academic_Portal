package com.sktech.academicportal.controllers.payment;

import com.sktech.academicportal.commerz.TransactionResponseValidator;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
public class PaymentRESTController {

    @PostMapping("/pay-success-new")
    public String paymentSuccessNew(@RequestParam Map<String, String> requestMap) throws Exception {
        TransactionResponseValidator transactionResponseValidator = new TransactionResponseValidator();
        transactionResponseValidator.receiveSuccessResponse(requestMap);
        System.out.println("Pay-success-new is called");
// Validator
        return "Everything is okay! ";
    }

}
