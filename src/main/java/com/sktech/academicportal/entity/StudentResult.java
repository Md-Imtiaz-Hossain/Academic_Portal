package com.sktech.academicportal.entity;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

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

    @Override
    public String toString() {
        return "CT (1) = " + ct1Mark + "\n" +
                "___ CT (2) = " + ct2Mark + "\n" +
                "___ CT (3) = " + ct3Mark + "\n" +
                "___ Mid  = " + midMark + "\n" +
                "___ Final = " + finalMark ;
    }
}
