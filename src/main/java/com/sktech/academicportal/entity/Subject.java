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

    @Column(name = "subject_name")
    private String subjectName;

    @Column(name = "subject_code")
    private String subjectCode;

    @Column(name = "subject_class")
    private String subjectClass;

    @Column(name = "subject_type")
    private String subjectType;

    @ManyToOne
    User user;

    @Override
    public String toString() {
        return this.subjectName + "_" + this.subjectCode + "_" + this.subjectClass + "\n";
    }
}
