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
@Table(name = "Result")
public class StudentResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

//    @Column(name = "result_creation_time")
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
//    @Temporal(TemporalType.DATE)
//    private LocalDateTime current_dateTime;

    @Column(name = "ct_1_mark")
    private Integer ct1Mark;

    @Column(name = "ct_2_mark")
    private Integer ct2Mark;

    @Column(name = "ct_3_mark")
    private Integer ct3Mark;

    @Column(name = "mid_mark")
    private Integer midMark;

    @Column(name = "final_mark")
    private Integer finalMark;

    @Column(name = "ct_average_mark")
    private Float ctAverageMark;

    @Column(name = "total_mark")
    private Float totalMark;

    @Column(name = "final_grade")
    private String finalGrade;


    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "user_name")
    private String userName;
    @Column(name = "subject_id")
    private Integer subjectId;

    @Column(name = "subject_name")
    private String subjectName;



    @Override
    public String toString() {
        return "StudentResult{" +
                "id=" + id +
                ", ct1Mark=" + ct1Mark +
                ", ct2Mark=" + ct2Mark +
                ", ct3Mark=" + ct3Mark +
                ", midMark=" + midMark +
                ", finalMark=" + finalMark +
                ", userId=" + userId +
                ", subjectId=" + subjectId +
                '}';
    }
}
