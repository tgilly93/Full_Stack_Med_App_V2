package com.techelevator.dto;

public class PatientContactDto {
    private String patientFirstName;

    private String patientLastName;

    private String patientAddress;

    private String patientCity;

    private String patientState;

    private String zipCode;

    private String patientPhoneNumber;

    public PatientContactDto() {

    }

    public PatientContactDto(String patientFirstName, String patientLastName, String patientAddress, String patientCity, String patientState, String zipCode, String patientPhoneNumber) {
        this.patientFirstName = patientFirstName;
        this.patientLastName = patientLastName;
        this.patientAddress = patientAddress;
        this.patientCity = patientCity;
        this.patientState = patientState;
        this.zipCode = zipCode;
        this.patientPhoneNumber = patientPhoneNumber;
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
        return "PatientContactDto{" +
                "patientFirstName='" + patientFirstName + '\'' +
                ", patientLastName='" + patientLastName + '\'' +
                ", patientAddress='" + patientAddress + '\'' +
                ", patientCity='" + patientCity + '\'' +
                ", patientState='" + patientState + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", patientPhoneNumber='" + patientPhoneNumber + '\'' +
                '}';
    }
}
