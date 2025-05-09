package com.techelevator.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class MonthlyAgendaDto extends DailyAgendaDto {
    private LocalDate monthStartDate;

    private String monthLabel;

    public MonthlyAgendaDto() {

    }

    public MonthlyAgendaDto(LocalDate monthStartDate, String monthLabel, LocalDate date, String dayOfWeek,
            String doctor,
            int patientId, String patientName, int timeBlock, LocalTime startTime, LocalTime endTime, String type,
            String status, int appointmentId) {
        super(date, dayOfWeek, doctor, patientId, patientName, timeBlock, startTime, endTime, type, status,
                appointmentId);
        this.monthStartDate = monthStartDate;
        this.monthLabel = monthLabel;
    }

    public LocalDate getMonthStartDate() {
        return monthStartDate;
    }

    public void setMonthStartDate(LocalDate monthStartDate) {
        this.monthStartDate = monthStartDate;
    }

    public String getMonthLabel() {
        return monthLabel;
    }

    public void setMonthLabel(String monthLabel) {
        this.monthLabel = monthLabel;
    }

    @Override
    public String toString() {
        return "MonthlyAgendaDto{" +
                "monthStartDate=" + monthStartDate +
                ", monthLabel='" + monthLabel + '\'' +
                '}';
    }
}
