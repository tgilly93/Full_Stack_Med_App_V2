package com.techelevator.dto;

import java.time.LocalTime;

public class SchedulingBlocksDto {
    private int blockId;

    private int npiNumber;

    private String timeBlock;

    private LocalTime startTime;

    private LocalTime endTime;

    public SchedulingBlocksDto() {

    }

    public SchedulingBlocksDto(int blockId, int npiNumber, String timeBlock, LocalTime startTime, LocalTime endTime) {
        this.blockId = blockId;
        this.npiNumber = npiNumber;
        this.timeBlock = timeBlock;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getBlockId() {
        return blockId;
    }

    public void setBlockId(int blockId) {
        this.blockId = blockId;
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
        return "SchedulingBlocksDto{" +
                "blockId=" + blockId +
                ", npiNumber=" + npiNumber +
                ", timeBlock='" + timeBlock + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
