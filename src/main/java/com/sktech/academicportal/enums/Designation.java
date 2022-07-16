package com.sktech.academicportal.enums;

public enum Designation {

    PRINCIPLE ("Principle"),
    VICE_PRINCIPLE ("Vice Principle"),
    TEACHER ("Teacher");

    private final String designation;

    Designation(String designation) {
        this.designation = designation;
    }

    public String getDesignation() {
        return designation;
    }
}
