package com.techelevator.dto;

import java.time.LocalTime;

public class ClinicianAvailabilityDto {
    private int npiNumber;

    private String location;

    private String clinician;

    private String dayOfWeek;

    private LocalTime startTime;

    private LocalTime endTime;

    private boolean isAvailable;

    public ClinicianAvailabilityDto() {
    }

    public ClinicianAvailabilityDto(int npiNumber, String location, String clinician, String dayOfWeek,
            LocalTime startTime, LocalTime endTime, boolean isAvailable) {
        this.npiNumber = npiNumber;
        this.location = location;
        this.clinician = clinician;
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
        this.isAvailable = isAvailable;
    }

    public int getNpiNumber() {
        return npiNumber;
    }

    public void setNpiNumber(int npiNumber) {
        this.npiNumber = npiNumber;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getClinician() {
        return clinician;
    }

    public void setClinician(String clinician) {
        this.clinician = clinician;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
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

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    @Override
    public String toString() {
        return "ClinicianAvailabilityDto{" +
                "npiNumber='" + npiNumber + '\'' +
                ", location='" + location + '\'' +
                ", clinician='" + clinician + '\'' +
                ", dayOfWeek='" + dayOfWeek + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", isAvailable=" + isAvailable +
                '}';
    }
}
