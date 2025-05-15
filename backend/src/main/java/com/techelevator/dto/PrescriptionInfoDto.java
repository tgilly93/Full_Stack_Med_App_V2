package com.techelevator.dto;

import java.math.BigDecimal;

public class PrescriptionInfoDto {
    private int prescriptionId;

    private String prescriptionName;

    private String description;

    private BigDecimal cost;

    public PrescriptionInfoDto() {

    }

    public PrescriptionInfoDto(int prescriptionId, String prescriptionName, String description, BigDecimal cost) {
        this.prescriptionId = prescriptionId;
        this.prescriptionName = prescriptionName;
        this.description = description;
        this.cost = cost;
    }

    public int getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(int prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

    public String getPrescriptionName() {
        return prescriptionName;
    }

    public void setPrescriptionName(String prescriptionName) {
        this.prescriptionName = prescriptionName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "PrescriptionInfoDto{" +
                "prescriptionId=" + prescriptionId +
                ", prescriptionName='" + prescriptionName + '\'' +
                ", description='" + description + '\'' +
                ", cost=" + cost +
                '}';
    }
}
