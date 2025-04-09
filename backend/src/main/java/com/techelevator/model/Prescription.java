package com.techelevator.model;
import java.util.ArrayList;
import java.util.List;

public class Prescription {
    private int prescriptionId;
    private int patientId;
    private int doctorId;
    private String prescriptionName;
    private String prescriptionDescription;
    private Double prescriptionCost;
    private String insuranceCoverage;
    private String prescriptionStatus;
    private List<Prescription> prescriptions;

    public Prescription(int prescriptionId, int patientId, int doctorId, String prescriptionName, String prescriptionDescription, Double prescriptionCost, String insuranceCoverage, String prescriptionStatus) {
        this.prescriptionId = prescriptionId;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.prescriptionName = prescriptionName;
        this.prescriptionDescription = prescriptionDescription;
        this.prescriptionCost = prescriptionCost;
        this.insuranceCoverage = insuranceCoverage;
        this.prescriptionStatus = prescriptionStatus;
    }

    public Prescription() {

    }

    public int  getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(int prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getPrescriptionName() {
        return prescriptionName;
    }

    public void setPrescriptionName(String prescriptionName) {
        this.prescriptionName = prescriptionName;
    }

    public String getPrescriptionDescription() {
        return prescriptionDescription;
    }

    public void setPrescriptionDescription(String prescriptionDescription) {
        this.prescriptionDescription = prescriptionDescription;
    }

    public Double getPrescriptionCost() {
        return prescriptionCost;
    }

    public void setPrescriptionCost(Double prescriptionCost) {
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

    public List<Prescription> getPrescriptions() {
        List<Prescription> prescriptions = new ArrayList<>();
        return prescriptions;
    }

    public void setPrescriptions(List<Prescription> prescriptions) {
        this.prescriptions = prescriptions;
    }


    @Override
    public String toString() {
        return "Prescription{" +
                "prescriptionId=" + prescriptionId +
                ", patientId=" + patientId + '\'' +
                ", doctorId=" + doctorId + '\'' +
                ", prescriptionName=" + prescriptionName + '\'' +
                ", prescriptionDescription=" + prescriptionDescription + '\'' +
                ", prescriptionCost=" + prescriptionCost + '\'' +
                ", insuranceCoverage=" + insuranceCoverage + '\'' +
                ", prescriptionStatus=" + prescriptionStatus + '\'' +
                '}';
    }
}

