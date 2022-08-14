package com.sktech.academicportal.enums;

public enum PreBuiltSectionName {
    NavBar("NavBar"),
    Carousel("Carousel"),
    History("History"),
    Achievements("Achievements"),
    ContactUs("Contact_Us"),
    Teachers("Teachers"),
    Notices("Public_Notices");

    public final String sectionName;

    PreBuiltSectionName(String name) {
        this.sectionName = name;
    }
}
