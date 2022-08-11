package com.sktech.academicportal.enums;

public enum PreBuiltSectionName {
    Carousel("Carousel"),
    History("History"),
    Achievements("Achievements"),
    ContactUs("Contact Us"),
    Teachers("Teachers"),
    Notices("Public Notices");

    public final String sectionName;

    PreBuiltSectionName(String name) {
        this.sectionName = name;
    }
}
