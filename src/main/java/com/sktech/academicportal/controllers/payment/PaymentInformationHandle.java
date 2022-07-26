package com.sktech.academicportal.controllers.payment;


import com.sktech.academicportal.commerz.SSLCommerz;
import com.sktech.academicportal.commerz.Utility.ParameterBuilder;
import com.sktech.academicportal.entity.PaymentInformation;
import com.sktech.academicportal.entity.User;
import com.sktech.academicportal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Map;

@Controller
@ComponentScan
@RequestMapping("/data")
public class PaymentInformationHandle {


    @Autowired
    UserService userService;


    @PostMapping(value = "/handle-payment/{schemeTotal}")
    public RedirectView payTest(@PathVariable Integer schemeTotal,
                                @ModelAttribute PaymentInformation paymentInformation,
                                Principal principal) throws Exception {

        User user = userService.getUserByEmail(principal.getName());
        Integer userId = user.getId();

        String baseurl = "https://xyzacademicportal.herokuapp.com/";
        String payment = String.valueOf(schemeTotal);
        String transactionID = "TXID" + Math.random() * 10000;
        String time = String.valueOf(LocalDateTime.now());
        String studentID = String.valueOf(userId);
        String doctorID = String.valueOf(userId);
        System.out.println(payment + " " + " " + time + " " + doctorID + " " + studentID);
        Map<String, String> transactionMap = ParameterBuilder.constructRequestParam(baseurl, payment, transactionID, studentID, doctorID);
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
//    @ResponseBody
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

        model.addAttribute("requestMapStr", requestMapStr);
        model.addAttribute("postData", postData);

//        return "The map came into comtroller is -> " + requestMapStr + " -------------- " + postDataStr;
        return "studentportal/payment-success";

    }


}
