package com.techelevator.dto;

public class OfficeInfoDto {
    private String name;

    private String location;

    private String city;

    private String state;

    private String zipCode;

    private String phoneNumber;

    private String hoursOfOperation;

    public OfficeInfoDto() {

    }

    public OfficeInfoDto(String name, String location, String city, String state, String zipCode, String phoneNumber, String hoursOfOperation) {
        this.name = name;
        this.location = location;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.phoneNumber = phoneNumber;
        this.hoursOfOperation = hoursOfOperation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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
        return "OfficeInfoDto{" +
                "name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", hoursOfOperation='" + hoursOfOperation + '\'' +
                '}';
    }
}
