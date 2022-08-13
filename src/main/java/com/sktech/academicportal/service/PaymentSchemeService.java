package com.sktech.academicportal.service;

import com.sktech.academicportal.entity.PaymentScheme;
import com.sktech.academicportal.repositories.PaymentSchemeRepository;
import com.sktech.academicportal.repositories.RoutineRepository;
import com.sktech.academicportal.repositories.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class PaymentSchemeService {

    @Autowired
    SubjectRepository subjectRepository;

    @Autowired
    RoutineRepository routineRepository;

    @Autowired
    PaymentSchemeRepository paymentSchemeRepository;


    // Get All Subject
    public List<PaymentScheme> getAllPaymentScheme() {
        return paymentSchemeRepository.findAll();
    }

    public List<PaymentScheme> getSchemeByClass(String classOf) {
        List<PaymentScheme> paymentSchemes = paymentSchemeRepository.findAll();
        List<PaymentScheme> paymentSchemeArrayList = new ArrayList<>();
        for (PaymentScheme paymentScheme : paymentSchemes) {
            String subjectClass = String.valueOf(paymentScheme.getPaymentClass());
            if (subjectClass.equals(classOf)) {
                paymentSchemeArrayList.add(paymentScheme);
            }
        }
        return paymentSchemeArrayList;
    }

    public void save(PaymentScheme paymentScheme) {
        paymentSchemeRepository.save(paymentScheme);
    }

    public PaymentScheme getSchemeById(Integer id) {
        return paymentSchemeRepository.findById(id).get();
    }

    public void updateScheme(PaymentScheme paymentScheme) {
        paymentSchemeRepository.save(paymentScheme);
    }

    public void deleteSchemeById(Integer id) {
        paymentSchemeRepository.deleteById(id);
    }

    public Integer getSchemeTotal(String loggedUserClass) {
        List<PaymentScheme> schemeByClass = getSchemeByClass(loggedUserClass);
        Integer total = 0;
        for (PaymentScheme paymentScheme : schemeByClass) {
            total += paymentScheme.getAmount();
        }
        return total;
    }
}