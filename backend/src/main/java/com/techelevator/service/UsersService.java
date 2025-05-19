package com.techelevator.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.techelevator.dao.UserDao;
import com.techelevator.model.Users;

@Service
public class UsersService {
    private final UserDao userDao;

    public UsersService(UserDao userDao) {
        this.userDao = userDao;
    }

    public List<Users> getAllUsers() {
        return userDao.getAllUsers();
    }

    public Users getUserByUserId(int userId) {
        return userDao.getUserByUserId(userId);
    }

    public Users getUserByUsername(String username) {
        return userDao.getUserByUsername(username);
    }

    public Users createUser(Users user) {
        return userDao.createUser(user);
    }

    public boolean updateUser(Users user) {
        return userDao.updateUser(user);
    }

    public boolean deleteUser(int userId) {
        return userDao.deleteUser(userId);
    }
}
