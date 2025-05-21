package com.techelevator.model;

import java.math.BigDecimal;

public class Clinician {
    private int npiNumber;
    private int userId;
    private int staffId;
    private int primaryOffice;
    private BigDecimal clinicianRatePerHour;

    public Clinician() {

    }

    public Clinician(int npiNumber, int userId, int staffId, int primaryOffice, BigDecimal clinicianRatePerHour) {
        this.npiNumber = npiNumber;
        this.userId = userId;
        this.staffId = staffId;
        this.primaryOffice = primaryOffice;
        this.clinicianRatePerHour = clinicianRatePerHour;
    }

    public int getNpiNumber() {
        return npiNumber;
    }

    public void setNpiNumber(int npiNumber) {
        this.npiNumber = npiNumber;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public int getPrimaryOffice() {
        return primaryOffice;
    }

    public void setPrimaryOffice(int primaryOffice) {
        this.primaryOffice = primaryOffice;
    }

    public BigDecimal getClinicianRatePerHour() {
        return clinicianRatePerHour;
    }

    public void setClinicianRatePerHour(BigDecimal clinicianRatePerHour) {
        this.clinicianRatePerHour = clinicianRatePerHour;
    }

    @Override
    public String toString() {
        return "Clinician{" +
                "npiNumber=" + npiNumber +
                ", userId=" + userId +
                ", staffId=" + staffId +
                ", primaryOffice=" + primaryOffice +
                ", clinicianRatePerHour=" + clinicianRatePerHour +
                '}';
    }
}
