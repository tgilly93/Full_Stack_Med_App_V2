package com.techelevator.model;
import java.math.BigDecimal;

public class Prescription {
    private int prescriptionId;
    private String prescriptionName;
    private int patientId;
    private int npiNumber;
    private String prescriptionDetails;
    private BigDecimal prescriptionCost;
    private String insuranceCoverage;
    private String prescriptionStatus;
    
    public Prescription() {

    }

    public Prescription(int prescriptionId, String prescriptionName, int patientId, int npiNumber, String prescriptionDetails, BigDecimal prescriptionCost, String insuranceCoverage, String prescriptionStatus) {
        this.prescriptionId = prescriptionId;
        this.prescriptionName = prescriptionName;
        this.patientId = patientId;
        this.npiNumber = npiNumber;
        this.prescriptionDetails =prescriptionDetails;
        this.prescriptionCost = prescriptionCost;
        this.insuranceCoverage = insuranceCoverage;
        this.prescriptionStatus = prescriptionStatus;
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

    public String getPrescriptionDetails() {
        return prescriptionDetails;
    }

    public void setPrescriptionDetails(String prescriptionDetails) {
        this.prescriptionDetails = prescriptionDetails;
    }

    public BigDecimal getPrescriptionCost() {
        return prescriptionCost;
    }

    public void setPrescriptionCost(BigDecimal prescriptionCost) {
        this.prescriptionCost = prescriptionCost;
    }

    public String getInsuranceCoverage() {
        return insuranceCoverage;
    }

    public void setInsuranceCoverage(String insuranceCoverage) {
        this.insuranceCoverage = insuranceCoverage;
    }

    public String getPrescriptionStatus() {
        return prescriptionStatus;
    }

    public void setPrescriptionStatus(String prescriptionStatus) {
        this.prescriptionStatus = prescriptionStatus;
    }

    @Override
    public String toString() {
        return "Prescription{" +
                "prescriptionId=" + prescriptionId +
                ", prescriptionName='" + prescriptionName + '\'' +
                ", patientId=" + patientId +
                ", npiNumber=" + npiNumber +
                ", prescriptionDetails='" + prescriptionDetails + '\'' +
                ", prescriptionCost=" + prescriptionCost +
                ", insuranceCoverage='" + insuranceCoverage + '\'' +
                ", prescriptionStatus='" + prescriptionStatus + '\'' +
                '}';
    }
}

