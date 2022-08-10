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
public class HomepageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "section", nullable = false)
    private String section;
    @Column(name = "rawData")
    private String rawData;
    @Column(name = "processedData", columnDefinition = "TEXT")
    private String processedData;
    @Column(name = "isPublic", nullable = false)
    private Boolean isPublic;
    @Column(name = "isCustom", nullable = false)
    private Boolean isCustom;
    @Column(name = "isOnNavbar", nullable = false)
    private Boolean isOnNavbar;

    public HomepageEntity(String section, String rawData, String processedData, Boolean isPublic, Boolean isCustom, Boolean isOnNavbar) {
        this.section = section;
        this.rawData = rawData;
        this.processedData = processedData;
        this.isPublic = isPublic;
        this.isCustom = isCustom;
        this.isOnNavbar = isOnNavbar;
    }
}
