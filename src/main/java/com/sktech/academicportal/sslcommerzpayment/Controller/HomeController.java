package com.sktech.academicportal.sslcommerzpayment.Controller;


import com.sktech.academicportal.sslcommerzpayment.commerz.SSLCommerz;
import com.sktech.academicportal.sslcommerzpayment.commerz.Utility.ParameterBuilder;
import com.sktech.academicportal.sslcommerzpayment.entity.PaymentInformation;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.sql.Time;
import java.time.LocalTime;
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

    @PostMapping(value = "/handle-payment")
    public RedirectView payTest(@ModelAttribute PaymentInformation paymentInformation) throws Exception {
        String appointDrTxId = getRandomTxIdString();
        Time appointTime = Time.valueOf(LocalTime.now());
        String doctorFee = String.valueOf(paymentInformation.getPayableAmount()); // Doctor_Fee needed

        String baseurl = "https://xyzacademicportal.herokuapp.com/";
//        SSL redirect to payment
        Map<String, String> transactionMap = ParameterBuilder.constructRequestParam(doctorFee, appointDrTxId, paymentInformation.getStudentEmail());
//        Map<String, String> transactionMap = ParameterBuilder.constructRequestParameters();

        SSLCommerz sslCommerz = new SSLCommerz("sktec62de70757d5df", "sktec62de70757d5df@ssl", true);
        String url = sslCommerz.initiateTransaction(transactionMap, false);
        System.out.println(url);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl(url);
        return redirectView;
    }


//        @PostMapping(value = "/handle-payment")
//    public String payTest(@ModelAttribute PaymentInformation paymentInformation) throws Exception {
//
//            System.out.println("======================================"+paymentInformation.getPayedTime());
//            System.out.println("======================================"+paymentInformation.getPayableAmount());
//            System.out.println("======================================"+paymentInformation.getStudentId());
//            System.out.println("======================================"+paymentInformation.getStudentEmail());
//
//        return "redirect:/payment/form";
//    }


    @PostMapping("/success")
    public String patientAppointDoctorSuccess(@RequestParam Map<String, String> requestMap, Model model) {
        String transactionId = requestMap.get("tran_id");
//        AppointDoctorTransaction appointDoctorTransaction = appointDoctorTransactionRepository.findByTxid(transactionId); // Fetching from database
//        appointDoctorTransaction.setTransactionStatus("Paid");
//        appointDoctorTransactionRepository.save(appointDoctorTransaction);
//        String doctorID = appointDoctorTransaction.getDoctorId(); // Doctor_ID needed
//        String doctorFee = appointDoctorTransaction.getDoctorFee(); // Doctor_Fee needed
//        Time appointTime = appointDoctorTransaction.getAppointmentTime(); // Appoint_Time needed
//
//        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        LocalDateTime localDateTime = LocalDateTime.now();
//
//        Date appointDate = Date.valueOf(localDateTime.format(dateTimeFormatter)); // Appoint Date
//
//        updateDoctorsAvailableTimeDB(doctorID, appointTime);
//
//        AppointDoctor appointDoctor = new AppointDoctor(patientID, doctorID, doctorFee, appointTime, appointDate);
//        appointDoctor = appointDoctorRepository.save(appointDoctor);
//        Prescription prescription = new Prescription();
//        prescription.setId(appointDoctor.getId());
//        prescription.setSymptoms("");
//        prescription.setTests(" ");
//        prescription.setAdvice(" ");
//        prescription.setMedicines(" ");
//        appointDoctor.setPrescription(prescription);
//        prescription.setAppointDoctor(appointDoctor);

        System.out.println("==============================================================================="+transactionId);

        model.addAttribute("transactionId", transactionId);

        return "sslcommerzpayment/payment-success";
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

