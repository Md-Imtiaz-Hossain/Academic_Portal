package com.sktech.academicportal.entity;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Entity
@Table(name = "Teacher_Evaluation")
public class TeacherEvaluation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "reviewer_id")
    private Integer reviewerId;

    @Column(name = "subject_id")
    private Integer subjectId;

    @Column(name = "teacher_id")
    private Integer teacherId;

    @Column(name = "question_one")
    private Integer questionOne;

    @Column(name = "question_two")
    private Integer questionTwo;

    @Column(name = "question_three")
    private Integer questionThree;

    @Column(name = "question_four")
    private Integer questionFour;

    @Column(name = "question_five")
    private Integer questionFive;

    @Column(name = "question_six")
    private Integer questionSix;

    @Column(name = "question_seven")
    private Integer questionSeven;

    @Column(name = "question_eight")
    private Integer questionEight;

    @Column(name = "question_nine")
    private Integer questionNine;

    @Column(name = "question_ten")
    private Integer questionTen;

    @Column(name = "comment")
    private String comment;

}
