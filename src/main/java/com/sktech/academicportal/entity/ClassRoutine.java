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
@Table(name = "Student_Routine_Table")
public class ClassRoutine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "week_day")
    private String weekDay;

    @Column(name = "subject_class")
    private String subjectClass;

    @Column(name = "period_1st")
    private String period1st;

    @Column(name = "period_2nd")
    private String period2nd;

    @Column(name = "period_3rd")
    private String period3rd;

    @Column(name = "period_4th")
    private String period4th;

    @Column(name = "period_5th")
    private String period5th;

    @Column(name = "period_6th")
    private String period6th;


}
