package com.techelevator.model;

import java.sql.Date;
import java.sql.Time;

public class Appointment {
    private int appointmentId;
    private int patientId;
    private int npiNumber;
    private int officeId;
    private Date date;
    private Time startTime;
    private Time endTime;
    private String appointmentType;
    private String appointmentStatus;

    public Appointment() {
    }

    public Appointment(int appointmentId, int patientId, int npiNumber, int officeId, Date date, Time startTime, Time endTime, String appointmentType, String appointmentStatus) {
        this.appointmentId = appointmentId;
        this.patientId = patientId;
        this.npiNumber = npiNumber;
        this.officeId = officeId;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
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

    public int getOfficeId() {
        return officeId;
    }

    public void setOfficeId(int officeId) {
        this.officeId = officeId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
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
                ", officeId=" + officeId +
                ", date=" + date +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", appointmentType='" + appointmentType + '\'' +
                ", appointmentStatus='" + appointmentStatus + '\'' +
                '}';
    }
}
