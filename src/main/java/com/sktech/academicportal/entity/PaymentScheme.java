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
@Table(name = "Student_Payment_Scheme")
public class PaymentScheme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "payment_name")
    private String paymentName;

    @Column(name = "payment_class")
    private String paymentClass;

    @Column(name = "academic_year")
    private String academicYear;

    @Column(name = "amount")
    private Integer amount;

}
