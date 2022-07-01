package com.sktech.academicportal.enums;

public enum AcademicClass {

    ONE ("1"),
    TWO ("2"),
    THREE ("3"),
    FOUR ("4"),
    FIVE ("5"),
    SIX ("6"),
    SEVEN ("7"),
    EIGHT ("8"),
    NINE ("9"),
    TEN ("10"),
    ELEVEN ("11"),
    TWELVE ("12");

    private final String  className;

    AcademicClass(String className) {
        this.className = className;
    }

    public String getClassName() {
        return className;
    }
}
