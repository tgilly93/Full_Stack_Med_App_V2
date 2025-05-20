package com.techelevator.model;

public class Staff {
    private int staffId;
    private int officeId;
    private String staffFirstName;
    private String staffLastName;
    private String staffAddress;
    private String staffPhoneNumber;

    public Staff() {

    }

    public Staff(int staffId, int officeId, String staffFirstName, String staffLastName, String staffAddress, String staffPhoneNumber) {
        this.staffId = staffId;
        this.officeId = officeId;
        this.staffFirstName = staffFirstName;
        this.staffLastName = staffLastName;
        this.staffPhoneNumber = staffPhoneNumber;
    }

    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public int getOfficeId() {
        return officeId;
    }

    public void setOfficeId(int officeId) {
        this.officeId = officeId;
    }

    public String getStaffFirstName() {
        return staffFirstName;
    }

    public void setStaffFirstName(String staffFirstName) {
        this.staffFirstName = staffFirstName;
    }

    public String getStaffLastName() {
        return staffLastName;
    }

    public void setStaffLastName(String staffLastName) {
        this.staffLastName = staffLastName;
    }

    public String getStaffAddress() {
        return staffAddress;
    }

    public void setStaffAddress(String staffAddress) {
        this.staffAddress = staffAddress;
    }

    public String getStaffPhoneNumber() {
        return staffPhoneNumber;
    }

    public void setStaffPhoneNumber(String staffPhoneNumber) {
        this.staffPhoneNumber = staffPhoneNumber;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "staffId=" + staffId +
                ", officeId=" + officeId +
                ", staffFirstName='" + staffFirstName + '\'' +
                ", staffLastName='" + staffLastName + '\'' +
                ", staffAddress='" + staffAddress + '\'' +
                ", staffPhoneNumber='" + staffPhoneNumber + '\'' +
                '}';
    }
}
