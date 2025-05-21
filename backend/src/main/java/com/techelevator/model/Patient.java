package com.techelevator.model;

import java.time.LocalDate;

public class Patient {
    private int patientId;
    private int userId;
    private String patientFirstName;
    private String patientLastName;
    private LocalDate patientDateOfBirth;
    private String patientAddress;
    private String patientCity;
    private String patientState;
    private String zipCode;
    private String patientPhoneNumber;

    public Patient() {

    }

    public Patient(int patientId, int userId, String patientFirstName, String patientLastName, LocalDate patientDateOfBirth, String patientAddress, String patientCity, String patientState, String zipCode, String patientPhoneNumber) {
        this.patientId = patientId;
        this.userId = userId;
        this.patientFirstName = patientFirstName;
        this.patientLastName = patientLastName;
        this.patientDateOfBirth = patientDateOfBirth;
        this.patientAddress = patientAddress;
        this.patientCity = patientCity;
        this.patientState = patientState;
        this.zipCode = zipCode;
        this.patientPhoneNumber = patientPhoneNumber;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPatientFirstName() {
        return patientFirstName;
    }

    public void setPatientFirstName(String patientFirstName) {
        this.patientFirstName = patientFirstName;
    }

    public String getPatientLastName() {
        return patientLastName;
    }

    public void setPatientLastName(String patientLastName) {
        this.patientLastName = patientLastName;
    }

    public LocalDate getPatientDateOfBirth() {
        return patientDateOfBirth;
    }

    public void setPatientDateOfBirth(LocalDate patientDateOfBirth) {
        this.patientDateOfBirth = patientDateOfBirth;
    }

    public String getPatientAddress() {
        return patientAddress;
    }

    public void setPatientAddress(String patientAddress) {
        this.patientAddress = patientAddress;
    }

    public String getPatientCity() {
        return patientCity;
    }

    public void setPatientCity(String patientCity) {
        this.patientCity = patientCity;
    }

    public String getPatientState() {
        return patientState;
    }

    public void setPatientState(String patientState) {
        this.patientState = patientState;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getPatientPhoneNumber() {
        return patientPhoneNumber;
    }

    public void setPatientPhoneNumber(String patientPhoneNumber) {
        this.patientPhoneNumber = patientPhoneNumber;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "patientId=" + patientId +
                ", userId=" + userId +
                ", patientFirstName='" + patientFirstName + '\'' +
                ", patientLastName='" + patientLastName + '\'' +
                ", patientDateOfBirth=" + patientDateOfBirth +
                ", patientAddress='" + patientAddress + '\'' +
                ", patientCity='" + patientCity + '\'' +
                ", patientState='" + patientState + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", patientPhoneNumber='" + patientPhoneNumber + '\'' +
                '}';
    }
}
