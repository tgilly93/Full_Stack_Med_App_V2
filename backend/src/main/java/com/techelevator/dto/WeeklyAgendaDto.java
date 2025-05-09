package com.techelevator.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class WeeklyAgendaDto extends DailyAgendaDto {
    private LocalDate weekStartDate;

    private String weekLabel;

    public WeeklyAgendaDto() {

    }

    public WeeklyAgendaDto(LocalDate weekStartDate, String weekLabel, LocalDate date, String dayOfWeek, String doctor,
            int patientId, String patientName, int timeBlock, LocalTime startTime, LocalTime endTime, String type,
            String status, int appointmentId) {
        super(date, dayOfWeek, doctor, patientId, patientName, timeBlock, startTime, endTime, type, status,
                appointmentId);
        this.weekStartDate = weekStartDate;
        this.weekLabel = weekLabel;
    }

    public LocalDate getWeekStartDate() {
        return weekStartDate;
    }

    public void setWeekStartDate(LocalDate weekStartDate) {
        this.weekStartDate = weekStartDate;
    }

    public String getWeekLabel() {
        return weekLabel;
    }

    public void setWeekLabel(String weekLabel) {
        this.weekLabel = weekLabel;
    }

    @Override
    public String toString() {
        return "WeeklyAgendaDto{" +
                "weekStartDate=" + weekStartDate +
                ", weekLabel='" + weekLabel + '\'' +
                '}';
    }
}
