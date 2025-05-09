package com.techelevator.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class DailyAgendaDto {
    private LocalDate date;

    private String dayOfWeek;

    private String doctor;

    private int patientId;

    private String patientName;

    private int timeBlock;

    private LocalTime startTime;

    private LocalTime endTime;

    private String type;

    private String status;

    private int appointmentId;

    public DailyAgendaDto() {

    }

    public DailyAgendaDto(LocalDate date, String dayOfWeek, String doctor, int patientId, String patientName,
            int timeBlock, LocalTime startTime, LocalTime endTime, String type, String status, int appointmentId) {
        this.date = date;
        this.dayOfWeek = dayOfWeek;
        this.doctor = doctor;
        this.patientId = patientId;
        this.patientName = patientName;
        this.timeBlock = timeBlock;
        this.startTime = startTime;
        this.endTime = endTime;
        this.type = type;
        this.status = status;
        this.appointmentId = appointmentId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public int getPatientId() {
        return patientId;
    }

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    @Override
    public String toString() {
        return "DailyAgendaDto{" +
                "date=" + date +
                ", dayOfWeek='" + dayOfWeek + '\'' +
                ", doctor='" + doctor + '\'' +
                ", patientId=" + patientId +
                ", patientName='" + patientName + '\'' +
                ", timeBlock=" + timeBlock +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", type='" + type + '\'' +
                ", status='" + status + '\'' +
                ", appointmentId=" + appointmentId +
                '}';
    }
}
