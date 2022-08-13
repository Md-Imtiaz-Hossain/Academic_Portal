package com.sktech.academicportal.sslcommerzpayment.Controller;


import com.sktech.academicportal.sslcommerzpayment.commerz.SSLCommerz;
import com.sktech.academicportal.sslcommerzpayment.commerz.Utility.ParameterBuilder;
import com.sktech.academicportal.sslcommerzpayment.entity.PaymentInformation;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Map;
import java.util.Random;

@Controller
@ComponentScan
@RequestMapping("/payment")
public class HomeController {

    @GetMapping("/form")
    public String indexPay(Model model) {
        model.addAttribute("paymentInformation", new PaymentInformation());
        return "sslcommerzpayment/payment-information-form";
    }


    public static String getRandomTxIdString() {
        String CHARS = "AbCdEfGhIjKlMnOpQrStWxYz1234567890";
        StringBuilder sb = new StringBuilder();
        Random rnd = new Random();
        int max = 10;
        int min = 5;
        while (sb.length() < (int)(Math.random() * ((max - min) + 1)) + min) {
            int index = (int) (rnd.nextFloat() * CHARS.length());
            sb.append(CHARS.charAt(index));
        }
        return sb.toString();
    }

    @PostMapping("/handle-payment")
    public RedirectView payTest(@ModelAttribute PaymentInformation paymentInformation) throws Exception {
        String randomTxIdString = getRandomTxIdString();
        String fee = String.valueOf(paymentInformation.getPayableAmount());
        Map<String, String> transactionMap = ParameterBuilder.constructRequestParam(fee, randomTxIdString, paymentInformation.getStudentEmail());
        SSLCommerz sslCommerz = new SSLCommerz("sktec62de70757d5df", "sktec62de70757d5df@ssl", true);
        String url = sslCommerz.initiateTransaction(transactionMap, false);
        System.out.println(url);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl(url);
        return redirectView;
    }

    @PostMapping("/success")
    public String patientAppointDoctorSuccess(@RequestParam Map<String, String> requestMap, Model model) {
        String transactionId = requestMap.get("tran_id");
        model.addAttribute("transactionId", transactionId);
        return "sslcommerzpayment/payment-information-form";
    }


    @RequestMapping("/pay-success")
    @ResponseBody
    public String paymentSuccessful(@RequestParam Map<String, String> requestMap, @RequestParam Map<String, String> postData, Model model) {

        System.out.println(requestMap.get("cus_name"));
        String requestMapStr = "";
        String postDataStr = "";
        for (String key : requestMap.keySet()) {
            System.out.println("key = " + key + " value = " + requestMap.get(key));
            String str = key = key + " value = " + requestMap.get(key) + " \n";
            requestMapStr = requestMapStr + str;
            requestMapStr = requestMapStr + '\n';
        }

        for (String key : postData.keySet()) {
            String str = key + " value = " + postData.get(key) + " \n";
            postDataStr = postDataStr + str;
            postDataStr = postDataStr + '\n';
        }
        System.out.println("This is successful page.. " + requestMapStr);
        return "The map came into comtroller is -> " + requestMapStr + " -------------- " + postDataStr;
//    return "Payment_Success";
    }


}

