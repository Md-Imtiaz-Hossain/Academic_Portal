package com.sktech.academicportal.enums;

public enum AcademicSection {

    A ("A"), B ("B"), C ("C"), D ("D"), E ("E"), F ("F");

    private final String section;

    AcademicSection(String section) {
        this.section = section;
    }

    public String getSection() {
        return section;
    }
}
