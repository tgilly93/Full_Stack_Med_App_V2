package com.techelevator.dao;

import com.techelevator.model.Users;

import java.util.List;

public interface UserDao {

    List<Users> getAllUsers();

    Users getUserByUserId(int userId);

    Users getUserByUsername(String username);

    Users createUser(Users user);

    boolean updateUser(Users user);

    boolean deleteUser(int userId);
}
