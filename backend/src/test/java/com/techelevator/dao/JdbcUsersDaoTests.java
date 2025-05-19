package com.techelevator.dao;

import com.techelevator.dto.RegisterUserDto;
import com.techelevator.exception.DaoException;
import com.techelevator.jdbcDao.JdbcUserDao;
import com.techelevator.model.Users;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;


import java.util.List;

public class JdbcUsersDaoTests extends BaseDaoTests {
    protected static final Users USERS_1 = new Users(1, "user1", "user1", "ROLE_USER", "User One", "123 Main St", "Newark", "DE", "19702");
    protected static final Users USERS_2 = new Users(2, "user2", "user2", "ROLE_USER", "User Two", "456 Oak Rd", "Newark", "DE", "19702");
    private static final Users USERS_3 = new Users(3, "user3", "user3", "ROLE_USER", "User Three", "789 Pine St", "Newark", "DE", "19702");

    private JdbcUserDao sut;

    @Before
    public void setup() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        sut = new JdbcUserDao(jdbcTemplate);
    }

    private Users mapRegisterUserDtoToUser(RegisterUserDto dto) {
        Users users = new Users();
        users.setUsername(dto.getUsername());
        users.setPasswordHash(dto.getPassword());
        users.setRole(dto.getRole());
        users.setName("");
        users.setAddress("");
        users.setCity("");
        users.setStateCode("");
        users.setZip("");
        return users;
    }

    @Test(expected = IllegalArgumentException.class)
    public void getUserByUsername_given_null_throws_exception() {
        sut.getUserByUsername(null);
    }

    @Test
    public void getUserByUsername_given_invalid_username_returns_null() {
        Assert.assertNull(sut.getUserByUsername("invalid"));
    }

    @Test
    public void getUserByUsername_given_valid_user_returns_user() {
        Users actualUsers = sut.getUserByUsername(USERS_1.getUsername());

        Assert.assertEquals(USERS_1, actualUsers);
    }

    @Test
    public void getUserById_given_invalid_user_id_returns_null() {
        Users actualUsers = sut.getUserByUserId(-1);

        Assert.assertNull(actualUsers);
    }

    @Test
    public void getUserById_given_valid_user_id_returns_user() {
        Users actualUsers = sut.getUserByUserId(USERS_1.getUserId());

        Assert.assertEquals(USERS_1, actualUsers);
    }

    @Test
    public void getUsers_returns_all_users() {
        List<Users> users = sut.getAllUsers();

        Assert.assertNotNull(users);
        Assert.assertEquals(3, users.size());
        Assert.assertEquals(USERS_1, users.get(0));
        Assert.assertEquals(USERS_2, users.get(1));
        Assert.assertEquals(USERS_3, users.get(2));
    }

    @Test(expected = DaoException.class)
    public void createUser_with_null_username() {
        RegisterUserDto registerUserDto = new RegisterUserDto();
        registerUserDto.setUsername(null);
        registerUserDto.setPassword(USERS_3.getPasswordHash());
        registerUserDto.setRole("ROLE_USER");
        sut.createUser(mapRegisterUserDtoToUser(registerUserDto));
    }

    @Test(expected = DaoException.class)
    public void createUser_with_existing_username() {
        RegisterUserDto registerUserDto = new RegisterUserDto();
        registerUserDto.setUsername(USERS_1.getUsername());
        registerUserDto.setPassword(USERS_3.getPasswordHash());
        registerUserDto.setRole("ROLE_USER");
        sut.createUser(mapRegisterUserDtoToUser(registerUserDto));
    }

    @Test(expected = IllegalArgumentException.class)
    public void createUser_with_null_password() {
        RegisterUserDto registerUserDto = new RegisterUserDto();
        registerUserDto.setUsername(USERS_3.getUsername());
        registerUserDto.setPassword(null);
        registerUserDto.setRole("ROLE_USER");
        sut.createUser(mapRegisterUserDtoToUser(registerUserDto));
    }

    @Test
    public void createUser_creates_a_user() {
        Users users = new Users(0, "newuser", "password", "ROLE_USER", "New User", "123 Example St", "Newark", "DE", "19702");
        Users createdUsers = sut.createUser(users);

        Assert.assertNotNull(createdUsers);

        Users retrievedUsers = sut.getUserByUsername(createdUsers.getUsername());
        Assert.assertEquals(retrievedUsers.getUsername(), createdUsers.getUsername());
        Assert.assertEquals(retrievedUsers.getRole(), createdUsers.getRole());
    }
}
