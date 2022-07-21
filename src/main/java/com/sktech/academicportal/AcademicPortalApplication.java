package com.sktech.academicportal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan({"com.sktech.academicportal.entity", "com.sktech.academicportal.*"})
public class AcademicPortalApplication {

    public static void main(String[] args) {
        SpringApplication.run(AcademicPortalApplication.class, args);
    }

}
