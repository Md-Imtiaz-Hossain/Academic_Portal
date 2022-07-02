package com.sktech.academicportal.entity;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Getter
@Setter
@Entity

public class StudentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(nullable = false, length = 200)
    private String email;


    @Column(name = "father_name")
    private String fatherName;

    @Column(name = "mother_name")
    private String motherName;


    @Column(name = "admission_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date admissionDate;

    @Column(name = "birth_date")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthDate;


    @Column(name = "class_roll")
    private Integer classRoll;

    @Column(name = "current_class")
    private String currentClass;

    @Column(name = "class_section")
    private String classSection;

    @Column(name = "academic_ID")
    private String academicID;

    @Column(name = "contact_No")
    private String contactNo;

    @Column(name = "address")
    private String address;


    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;


}