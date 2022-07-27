package com.sktech.academicportal.sslcommerzpayment.entity;

public class Appointment {

    private int appointID;

    private String patient_ID;

    private String doctor_ID;

    private String appointTime;

    private String paid;

    public Appointment(String patient_ID, String doctor_ID, String appointTime, String paid) {
        this.patient_ID = patient_ID;
        this.doctor_ID = doctor_ID;
        this.appointTime = appointTime;
        this.paid = paid;
    }

    public Appointment() {
    }

    public int getAppointID() {
        return appointID;
    }

    public void setAppointID(int appointID) {
        this.appointID = appointID;
    }

    public String getPatient_ID() {
        return patient_ID;
    }

    public void setPatient_ID(String patient_ID) {
        this.patient_ID = patient_ID;
    }

    public String getDoctor_ID() {
        return doctor_ID;
    }

    public void setDoctor_ID(String doctor_ID) {
        this.doctor_ID = doctor_ID;
    }

    public String getAppointTime() {
        return appointTime;
    }

    public void setAppointTime(String appointTime) {
        this.appointTime = appointTime;
    }

    public String getPaid() {
        return paid;
    }

    public void setPaid(String paid) {
        this.paid = paid;
    }
}
