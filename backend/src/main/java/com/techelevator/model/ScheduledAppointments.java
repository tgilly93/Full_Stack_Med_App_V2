package com.techelevator.model;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ScheduledAppointments {
    private int appointmentId;
    private LocalDate date;
    private String dayOfWeek;
    private String doctor;
    private int npiNumber;
    private int patientId;
    private String patientName;
    private int timeBlock;
    private LocalTime startTime;
    private LocalTime endTime;
    private String type;
    private String status;

    public ScheduledAppointments() {
    }

    public ScheduledAppointments(int appointmentId, LocalDate date, String dayOfWeek, String doctor,
            int npiNumber, int patientId, String patientName,
            int timeBlock, LocalTime startTime, LocalTime endTime,
            String type, String status) {
        this.appointmentId = appointmentId;
        this.date = date;
        this.dayOfWeek = dayOfWeek;
        this.doctor = doctor;
        this.npiNumber = npiNumber;
        this.patientId = patientId;
        this.patientName = patientName;
        this.timeBlock = timeBlock;
        this.startTime = startTime;
        this.endTime = endTime;
        this.type = type;
        this.status = status;
    }
    
    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    @JsonProperty("Date")
    public LocalDate getDate() {
        return date;
    }

    @JsonProperty("Date")
    public void setDate(LocalDate date) {
        this.date = date;
    }
    
    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    @JsonProperty("Doctor")
    public String getDoctor() {
        return doctor;
    }

    @JsonProperty("Doctor")
    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public int getNpiNumber() {
        return npiNumber;
    }

    public void setNpiNumber(int npiNumber) {
        this.npiNumber = npiNumber;
    }

    @JsonProperty("Patient")
    public int getPatientId() {
        return patientId;
    }

    @JsonProperty("Patient")
    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public int getTimeBlock() {
        return timeBlock;
    }

    public void setTimeBlock(int timeBlock) {
        this.timeBlock = timeBlock;
    }

    @JsonProperty("start_time")
    public LocalTime getStartTime() {
        return startTime;
    }

    @JsonProperty("start_time")
    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    @JsonProperty("end_time")
    public LocalTime getEndTime() {
        return endTime;
    }

    @JsonProperty("end_time")
    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    @JsonProperty("Type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("Status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ScheduledAppointments{" +
                "appointmentId=" + appointmentId +
                ", date=" + date +
                ", dayOfWeek='" + dayOfWeek + '\'' +
                ", doctor='" + doctor + '\'' +
                ", npiNumber=" + npiNumber +
                ", patientId=" + patientId +
                ", patientName='" + patientName + '\'' +
                ", timeBlock=" + timeBlock +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", type='" + type + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}