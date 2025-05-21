package com.techelevator.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Appointment {
    private int appointmentId;
    private int patientId;
    private int npiNumber;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private String appointmentType;
    private String appointmentStatus;

    public Appointment() {
    }

    public Appointment(int appointmentId, int patientId, int npiNumber, LocalDate date, LocalTime startTime, LocalTime endTime, String appointmentType, String appointmentStatus) {
        this.appointmentId = appointmentId;
        this.patientId = patientId;
        this.npiNumber = npiNumber;
        this.date = date;
        this.startTime = (startTime);
        this.endTime = (endTime);
        this.appointmentType = appointmentType;
        this.appointmentStatus = appointmentStatus;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getNpiNumber() {
        return npiNumber;
    }

    public void setNpiNumber(int npiNumber) {
        this.npiNumber = npiNumber;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public String getAppointmentType() {
        return appointmentType;
    }

    public void setAppointmentType(String appointmentType) {
        this.appointmentType = appointmentType;
    }

    public String getAppointmentStatus() {
        return appointmentStatus;
    }

    public void setAppointmentStatus(String appointmentStatus) {
        this.appointmentStatus = appointmentStatus;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "appointmentId=" + appointmentId +
                ", patientId=" + patientId +
                ", npiNumber=" + npiNumber +
                ", date=" + date +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", appointmentType='" + appointmentType + '\'' +
                ", appointmentStatus='" + appointmentStatus + '\'' +
                '}';
    }
}
