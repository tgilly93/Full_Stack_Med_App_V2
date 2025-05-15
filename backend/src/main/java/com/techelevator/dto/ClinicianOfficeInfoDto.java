package com.techelevator.dto;

public class ClinicianOfficeInfoDto {
    private String physician;

    private String primaryLocation;

    private String address;

    private String city;

    private String state;

    private String zipCode;

    private String phoneNumber;

    private String hoursOfOperation;

    public ClinicianOfficeInfoDto() {
        
    }

    public ClinicianOfficeInfoDto(String physician, String primaryLocation, String address, String city, String state, String zipCode, String phoneNumber, String hoursOfOperation) {
        this.physician = physician;
        this.primaryLocation = primaryLocation;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.phoneNumber = phoneNumber;
        this.hoursOfOperation = hoursOfOperation;
    }

    public String getPhysician() {
        return physician;
    }

    public void setPhysician(String physician) {
        this.physician = physician;
    }

    public String getPrimaryLocation() {
        return primaryLocation;
    }

    public void setPrimaryLocation(String primaryLocation) {
        this.primaryLocation = primaryLocation;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getHoursOfOperation() {
        return hoursOfOperation;
    }

    public void setHoursOfOperation(String hoursOfOperation) {
        this.hoursOfOperation = hoursOfOperation;
    }

    @Override
    public String toString() {
        return "ClinicianOfficeInfoDto{" +
                "physician='" + physician + '\'' +
                ", primaryLocation='" + primaryLocation + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", hoursOfOperation='" + hoursOfOperation + '\'' +
                '}';
    }
}
