package com.sktech.academicportal.sslcommerzpayment.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PaymentInformation {

    private int payableAmount;

    private String studentId;

    private String studentEmail;

    private String payedTime;


}
