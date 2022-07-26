package com.sktech.academicportal.entity;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Getter
@Setter
@Entity
@Table(name = "Student_Payment_Information")
public class PaymentInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "payment_time")
    private String time;

    @Column(name = "payment_amount")
    private Integer payedAmount;

    @Column(name = "user_email")
    private String userEmail;

    @Column(name = "user_name")
    private String userName;

}
