package com.sktech.academicportal.enums;

public enum WeekDay {

    Saturday ("Saturday"),
    Sunday ("Sunday"),
    Monday ("Monday"),
    Tuesday ("Tuesday"),
    Wednesday ("Wednesday"),
    Thursday ("Thursday"),
    Friday ("Friday");

    private final String  weekDay;

    WeekDay(String weekDay) {
        this.weekDay = weekDay;
    }

    public String getWeekDay() {
        return weekDay;
    }
}
