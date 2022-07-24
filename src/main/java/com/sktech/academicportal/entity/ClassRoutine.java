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
@Table(name = "Routine")
public class ClassRoutine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "week_day")
    private String weekDay;

    @Column(name = "subject_class")
    private String subjectClass;

    @Column(name = "1st_period")
    private String period1st;

    @Column(name = "2nd_period")
    private String period2nd;

    @Column(name = "3rd_period")
    private String period3rd;

    @Column(name = "4th_period")
    private String period4th;

    @Column(name = "5th_period")
    private String period5th;

    @Column(name = "6th_period")
    private String period6th;


}
