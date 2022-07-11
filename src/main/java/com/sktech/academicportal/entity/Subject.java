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
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "subject_name", nullable = false, length = 40, unique = true)
    private String subjectName;

    @Column(name = "subject_code", nullable = false, length = 40, unique = true)
    private String subjectCode;

    @Column(name = "subject_class", nullable = false, length = 40)
    private String subjectClass;

    @Column(name = "subject_type", nullable = false, length = 40)
    private String subjectType;


    @Override
    public String toString() {
        return this.subjectName;
    }
}
