package com.techelevator.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * RegisterUserDto is a class used to hold the registration information for a new user
 * that's sent from the client to the server for the register endpoint.
 *
 * The acronym DTO is being used for "data transfer object". It means that this type of
 * class is specifically created to transfer data between the client and the server.
 */
public class RegisterUserDto {

    @NotEmpty(message = "Username is required.")
    private String username;
    @NotEmpty(message = "Password is required.")
    private String password;
    @NotEmpty(message = "Please confirm your password.")
    private String confirmPassword;
    @NotBlank(message = "First name is required.")
    private String firstName;
    @NotBlank(message = "Last name is required.")
    private String lastName;
    @NotNull(message = "Date of birth is required.")
    @JsonProperty("dateOfBirth")    
    private LocalDate dateOfBirth;
    @NotEmpty(message = "Please select a role for this user.")
    private String role;
    @NotBlank(message = "Phone number is required.")
    private String phoneNumber;
    @NotBlank(message = "Address is required.")
    private String address;
    private String city;
    @NotEmpty(message = "State code is required.")
    @Pattern(regexp = "^(AL|AK|AR|AZ|CA|CO|CT|DC|DE|FL|GA|HI|IA|ID|IL|IN|KS|KY|LA|MA|MD|ME|MI|MN|MO|MS|MT|NC|ND|NE|NH|NJ|NM|NV|NY|OH|OK|OR|PA|RI|SC|SD|TN|TX|UT|VA|VT|WA|WI|WV|WY)$",
            message = "State code is invalid")
    private String stateCode;
    @NotEmpty(message = "ZIP is required.")
    @Pattern(regexp = "^[0-9]{5}$",
            message = "ZIP must be 5 digits")
    @JsonProperty("ZIP")
    private String ZIP;
    @NotNull(message = "Office is required.")
    private Integer primaryOffice;

    private Integer npiNumber;
    private BigDecimal clinicianRatePerHour;

    public RegisterUserDto() {}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public String getZIP() {
        return ZIP;
    }

    public void setZIP(String ZIP) {
        this.ZIP = ZIP;
    }

    public Integer getPrimaryOffice() {
        return primaryOffice;
    }

    public void setPrimaryOffice(Integer primaryOffice) {
        this.primaryOffice = primaryOffice;
    }

    public Integer getNpiNumber() {
        return npiNumber;
    }

    public void setNpiNumber(Integer npiNumber) {
        this.npiNumber = npiNumber;
    }

    public BigDecimal getClinicianRatePerHour() {
        return clinicianRatePerHour;
    }

    public void setClinicianRatePerHour(BigDecimal clinicianRatePerHour) {
        this.clinicianRatePerHour = clinicianRatePerHour;
    }
}
