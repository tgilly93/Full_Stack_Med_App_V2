package com.techelevator.model;

import java.sql.Time;
import java.util.Date;

public class Agenda {
    private Date date;
    private String dayOfWeek;
    private String doctorName;
    private String patientName;
    private int patientId;
    private String appointmentType;
    private String appointmentStatus;
    private int scheduleBlock;
    private Time appointmentStartTime;
    private Time appointmentEndTime;

    public Agenda(Date date, String dayOfWeek, String doctorName, String patientName, int patientId, String appointmentType, String appointmentStatus, int scheduleBlock, Time appointmentStartTime, Time appointmentEndTime) {
        this.date = date;
        this.dayOfWeek = dayOfWeek;
        this.doctorName = doctorName;
        this.patientName = patientName;
        this.patientId = patientId;
        this.appointmentType = appointmentType;
        this.appointmentStatus = appointmentStatus;
        this.scheduleBlock = scheduleBlock;
        this.appointmentStartTime = appointmentStartTime;
        this.appointmentEndTime = appointmentEndTime;
    }

    public Agenda() {
    
    }

    public Date getDate() {
        return date;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public String getPatientName() {
        return patientName;
    }

    public int getPatientId() {
        return patientId;
    }

    public String getAppointmentType() {
        return appointmentType;
    }

    public String getAppointmentStatus() {
        return appointmentStatus;
    }

    public int getScheduleBlock() {
        return scheduleBlock;
    }

    public Time getAppointmentStartTime() {
        return appointmentStartTime;
    }

    public Time getAppointmentEndTime() {
        return appointmentEndTime;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public void setAppointmentType(String appointmentType) {
        this.appointmentType = appointmentType;
    }

    public void setAppointmentStatus(String appointmentStatus) {
        this.appointmentStatus = appointmentStatus;
    }

    public void setScheduleBlock(int scheduleBlock) {
        this.scheduleBlock = scheduleBlock;
    }

    public void setAppointmentStartTime(Time appointmentStartTime) {
        this.appointmentStartTime = appointmentStartTime;
    }

    public void setAppointmentEndTime(Time appointmentEndTime) {
        this.appointmentEndTime = appointmentEndTime;
    }

    @Override
    public String toString() {
        return "Agenda{" +
                "date=" + date +
                ", dayOfWeek='" + dayOfWeek + '\'' +
                ", doctorName='" + doctorName + '\'' +
                ", patientName='" + patientName + '\'' +
                ", patientId=" + patientId +
                ", appointmentType='" + appointmentType + '\'' +
                ", appointmentStatus='" + appointmentStatus + '\'' +
                ", scheduleBlock=" + scheduleBlock +
                ", appointmentStartTime=" + appointmentStartTime +
                ", appointmentEndTime=" + appointmentEndTime +
                '}';
    }


}
