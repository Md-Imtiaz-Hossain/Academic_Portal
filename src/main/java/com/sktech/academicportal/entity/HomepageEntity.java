package com.sktech.academicportal.entity;

import com.sktech.academicportal.enums.PreBuiltSectionName;
import com.sktech.academicportal.helper.ProcessHomeData;
import com.sktech.academicportal.helper.StringToListConverter;
import com.sktech.academicportal.service.PublicFilesRepositoryService;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import com.sktech.academicportal.helper.StringToListConverter;
import org.springframework.beans.factory.annotation.Autowired;

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

    public HomepageEntity(String section, String rawData, Boolean isPublic, Boolean isCustom) {
        this.section = section;
        this.isPublic = isPublic;
        this.isCustom = isCustom;
        this.setRawData(rawData);
    }

    public void setRawData(String rawData) {
        ProcessHomeData processHomeData = new ProcessHomeData();
        this.rawData = rawData;
        this.processedData = processHomeData.htmlData(this.section,this.rawData);

    }
}
