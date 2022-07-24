package com.sktech.academicportal.entity;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter
@Entity
public class PublicFiles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "type")
    public String type;

    @Column(name = "path")
    public String path;

    @Column(name = "heading")
    public String heading;

    @Column(name = "description", columnDefinition = "TEXT")
    public String description;

    @Column(name = "public_visibiliy")
    public Boolean isPublic;

}
