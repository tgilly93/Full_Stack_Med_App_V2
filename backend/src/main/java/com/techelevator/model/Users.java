package com.techelevator.model;

/**
 * Model class representing an application user.
 *
 * Contains information about the user - their id, username, address information,
 * password (hashed) and authorities (user roles).
 */
public class Users {

   private int userId;
   private String username;
   private String passwordHash;
   private String role;
   private String name;
   private String address;
   private String city;
   private String stateCode;
   private String zip;

   public Users() { 

   }

   public Users(int userId, String username, String passwordHash, String role, String name, String address, String city, String stateCode, String zip) {
      this.userId = userId;
      this.username = username;
      this.passwordHash = passwordHash;
      this.role = role;
      this.name = name;
      this.address = address;
      this.city = city;
      this.stateCode = stateCode;
      this.zip = zip;
   }

   public Users(String username, String passwordHash, String role, String name, String address, String city, String stateCode, String zip) {
      this(0, username, passwordHash, role, name, address, city, stateCode, zip);
   }

   public int getUserId() {
      return userId;
   }

   public void setUserId(int userId) {
      this.userId = userId;
   }

   public String getUsername() {
      return username;
   }

   public void setUsername(String username) {
      this.username = username;
   }

   public String getPasswordHash() {
      return passwordHash;
   }

   public void setPasswordHash(String passwordHash) {
      this.passwordHash = passwordHash;
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

   public String getZip() {
      return zip;
   }

   public void setZip(String zip) {
      this.zip = zip;
   }

   @Override
   public String toString() {
      return "Users{" +
              "userId=" + userId +
              ", username='" + username + '\'' +
              ", passwordHash='" + passwordHash + '\'' +
              ", role='" + role + '\'' +
              ", name='" + name + '\'' +
              ", address='" + address + '\'' +
              ", city='" + city + '\'' +
              ", stateCode='" + stateCode + '\'' +
              ", zip='" + zip + '\'' +
              '}';
   }
}
