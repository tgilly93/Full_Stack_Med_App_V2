package com.techelevator.dto;

import java.time.LocalDate;

public class PatientActivePrescriptionDto {
    private int patientId;

    private String name;

    private LocalDate dob;

    private String streetAddress;

    private String city;

    private String state;

    private String zipCode;

    private String phone;

    private int prescriptionId;

    private String commonName;

    private String description;

    private String prescriptionStatus;

    private String prescribingClinician;

    public PatientActivePrescriptionDto() {

    }

    public PatientActivePrescriptionDto(int patientId, String name, LocalDate dob, String streetAddress, String city, String state, String zipCode, String phone, int prescriptionId, String commonName, String description, String prescriptionStatus, String prescribingClinician) {
        this.patientId = patientId;
        this.name = name;
        this.dob = dob;
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.phone = phone;
        this.prescriptionId =prescriptionId;
        this.commonName = commonName;
        this.description = description;
        this.prescriptionStatus = prescriptionStatus;
        this.prescribingClinician = prescribingClinician;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(int prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

    public String getCommonName() {
        return commonName;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrescriptionStatus() {
        return prescriptionStatus;
    }

    public void setPrescriptionStatus(String prescriptionStatus) {
        this.prescriptionStatus = prescriptionStatus;
    }

    public String getPrescribingClinician() {
        return prescribingClinician;
    }

    public void setPrescribingClinician(String prescribingClinician) {
        this.prescribingClinician = prescribingClinician;
    }

    @Override
    public String toString() {
        return "PatientActivePrescriptionDto{" +
                "patientId=" + patientId +
                ", name='" + name + '\'' +
                ", dob=" + dob +
                ", streetAddress='" + streetAddress + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", phone='" + phone + '\'' +
                ", prescriptionId=" + prescriptionId +
                ", commonName='" + commonName + '\'' +
                ", description='" + description + '\'' +
                ", prescriptionStatus='" + prescriptionStatus + '\'' +
                ", prescribingClinician='" + prescribingClinician + '\'' +
                '}';
    }
}
