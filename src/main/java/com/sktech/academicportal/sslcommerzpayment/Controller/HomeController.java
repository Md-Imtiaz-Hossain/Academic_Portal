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

@Controller
@ComponentScan
@RequestMapping("/payment")
public class HomeController {

    @GetMapping("/form")
    public String indexPay(@ModelAttribute PaymentInformation paymentInformation, Model model) {
        model.addAttribute("paymentInformation", paymentInformation);
        return "sslcommerzpayment/Patient_appoint_doctor";
    }

    @PostMapping(value = "/handle-payment")
    public RedirectView payTest(@ModelAttribute PaymentInformation paymentInformation) throws Exception {
        String baseurl = "https://xyzacademicportal.herokuapp.com/";
        String payment = String.valueOf(paymentInformation.getPayableAmount());
        String transactionID = "TXID" + Math.random() * 10000;
        String time = paymentInformation.getPayedTime();
        String patientID = paymentInformation.getStudentId();
        String doctorID = paymentInformation.getStudentId();
        System.out.println(payment + " " + " " + time + " " + doctorID + " " + patientID);
        Map<String, String> transactionMap = ParameterBuilder.constructRequestParam(baseurl, payment, transactionID, patientID, doctorID);
//        Map<String, String> transactionMap = ParameterBuilder.constructRequestParameters();

        SSLCommerz sslCommerz = new SSLCommerz("sktec62de70757d5df", "sktec62de70757d5df@ssl", true);
        String url = sslCommerz.initiateTransaction(transactionMap, false);
        System.out.println("The url: " + url);
        System.out.println("after previous url");
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl(url);
        return redirectView;
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

