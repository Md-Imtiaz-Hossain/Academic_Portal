package com.sktech.academicportal.entity;

import lombok.*;

import javax.persistence.*;
import java.time.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Entity
@Table(name = "All_Application")
public class AllApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "send_form")
    private String sendFrom;

    @Column(name = "send_to")
    private String sendTo;

    @Column(name = "application_subject")
    private String applicationSubject;

    @Column(name = "application_status") // send, saved, received
    private String applicationStatus;

    @Column(name = "application_content")
    private String applicationContent;

    @Column(name = "application_time")
    private LocalDateTime applicationTime;




}
