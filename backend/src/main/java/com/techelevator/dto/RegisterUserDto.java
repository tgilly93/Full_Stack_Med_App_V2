package com.techelevator.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

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
    @NotEmpty(message = "Name is required.")
    private String name;
    private String address;
    private String city;
    @NotEmpty(message = "State code is required.")
    @Pattern(regexp = "^(AL|AK|AR|AZ|CA|CO|CT|DC|DE|FL|GA|HI|IA|ID|IL|IN|KS|KY|LA|MA|MD|ME|MI|MN|MO|MS|MT|NC|ND|NE|NH|NJ|NM|NV|NY|OH|OK|OR|PA|RI|SC|SD|TN|TX|UT|VA|VT|WA|WI|WV|WY)$",
            message = "State code is invalid")
    private String stateCode;
    @NotEmpty(message = "ZIP is required.")
    @Pattern(regexp = "^[0-9]{5}$",
            message = "ZIP must be 5 digits")
    private String ZIP;
    @NotEmpty(message = "Please select a role for this user.")
    private String role;

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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
