package com.techelevator.model;

import java.util.List;

public class Office {
    private int officeId;
    private String officeName;
    private String officePhoneNumber;
    private String officeAddress;
    private String officeCity;
    private String state;
    private String zipCode;
    private String officeOpen;
    private String officeClose;
    private List<String> doctors;
    private double costPerHour;

    public Office(int officeId, String officeName, String officePhoneNumber, String officeAddress, String officeCity, String state, String zipCode, String officeOpen, String officeClose) {
        this.officeId = officeId;
        this.officeName = officeName;
        this.officePhoneNumber = officePhoneNumber;
        this.officeAddress = officeAddress;
        this.officeCity = officeCity;
        this.state = state;
        this.zipCode = zipCode;
        this.officeOpen = officeOpen;
        this.officeClose = officeClose;
    }
    public Office() {

    }

    public int getOfficeId() {
        return officeId;
    }

    public void setOfficeId(int officeId) {
        this.officeId = officeId;
    }

    public String getOfficeName() {
        return officeName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }

    public String getOfficePhoneNumber() {
        return officePhoneNumber;
    }

    public void setOfficePhoneNumber(String officePhoneNumber) {
        this.officePhoneNumber = officePhoneNumber;
    }

    public String getOfficeAddress() {
        return officeAddress;
    }

    public void setOfficeAddress(String officeAddress) {
        this.officeAddress = officeAddress;
    }

    public String getOfficeCity() {
        return officeCity;
    }

    public void setOfficeCity(String officeCity) {
        this.officeCity = officeCity;
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

    public String getOfficeOpen() {
        return officeOpen;
    }

    public void setOfficeOpen(String officeOpen) {
        this.officeOpen = officeOpen;
    }

    public String getOfficeClose() {
        return officeClose;
    }

    public void setOfficeClose(String officeClose) {
        this.officeClose = officeClose;
    }

    public List<String> getDoctors() {
        return doctors;
    }

    public void setDoctors(List<String> doctors) {
        this.doctors = doctors;
    }

    public double getCostPerHour() {
        return costPerHour;
    }

    public void setCostPerHour(double costPerHour) {
        this.costPerHour = costPerHour;
    }

    @Override
    public String toString() {
        return "Office{" +
                "officeId=" + officeId +
                ", officeName='" + officeName + '\'' +
                ", officePhoneNumber='" + officePhoneNumber + '\'' +
                ", officeAddress='" + officeAddress + '\'' +
                ", officeCity='" + officeCity + '\'' +
                ", state='" + state + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", officeOpen='" + officeOpen + '\'' +
                ", officeClose='" + officeClose + '\'' +
                ", doctors=" + doctors +
                ", costPerHour=" + costPerHour +
                '}';
    }
}
