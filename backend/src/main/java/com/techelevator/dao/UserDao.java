package com.techelevator.dao;

import com.techelevator.model.Patient;
import com.techelevator.model.Users;

import java.util.List;

public interface UserDao {

    List<Users> getAllUsers();

    Users getUserByUserId(int userId);

    Users getUserByUsername(String username);

    Users createUser(Users user);

    boolean updateUser(Users user);

    boolean deleteUser(int userId);

    boolean updateUserDetailsFromPatient(Patient patient);

    boolean updateUserNameAndAddress(int userId, String name, String address);
}
