package com.techelevator.dto;

import java.time.LocalTime;

public class TimeBlocksDto {
    private int npiNumber;

    private String timeBlock;

    private LocalTime startTime;

    private LocalTime endTime;

    public TimeBlocksDto() {

    }

    public TimeBlocksDto(int npiNumber, String timeBlock, LocalTime startTime, LocalTime endTime) {
        this.npiNumber = npiNumber;
        this.timeBlock = timeBlock;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getNpiNumber() {
        return npiNumber;
    }

    public void setNpiNumber(int npiNumber) {
        this.npiNumber = npiNumber;
    }

    public String getTimeBlock() {
        return timeBlock;
    }

    public void setTimeBlock(String timeBlock) {
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

    @Override
    public String toString() {
        return "TimeBlocksDto{" +
                "npiNumber=" + npiNumber +
                ", timeBlock='" + timeBlock + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
