package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.RegisterUserDto;
import com.techelevator.model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;


import java.util.List;

public class JdbcUserDaoTests extends BaseDaoTests {
    protected static final User USER_1 = new User(1, "user1", "user1", "ROLE_USER", "User One", "123 Main St", "Newark", "DE", "19702");
    protected static final User USER_2 = new User(2, "user2", "user2", "ROLE_USER", "User Two", "456 Oak Rd", "Newark", "DE", "19702");
    private static final User USER_3 = new User(3, "user3", "user3", "ROLE_USER", "User Three", "789 Pine St", "Newark", "DE", "19702");

    private JdbcUserDao sut;

    @Before
    public void setup() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        sut = new JdbcUserDao(jdbcTemplate);
    }

    private User mapRegisterUserDtoToUser(RegisterUserDto dto) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setAuthorities(dto.getRole());
        user.setName("");
        user.setAddress("");
        user.setCity("");
        user.setStateCode("");
        user.setZIP("");
        return user;
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
        User actualUser = sut.getUserByUsername(USER_1.getUsername());

        Assert.assertEquals(USER_1, actualUser);
    }

    @Test
    public void getUserById_given_invalid_user_id_returns_null() {
        User actualUser = sut.getUserById(-1);

        Assert.assertNull(actualUser);
    }

    @Test
    public void getUserById_given_valid_user_id_returns_user() {
        User actualUser = sut.getUserById(USER_1.getId());

        Assert.assertEquals(USER_1, actualUser);
    }

    @Test
    public void getUsers_returns_all_users() {
        List<User> users = sut.getUsers();

        Assert.assertNotNull(users);
        Assert.assertEquals(3, users.size());
        Assert.assertEquals(USER_1, users.get(0));
        Assert.assertEquals(USER_2, users.get(1));
        Assert.assertEquals(USER_3, users.get(2));
    }

    @Test(expected = DaoException.class)
    public void createUser_with_null_username() {
        RegisterUserDto registerUserDto = new RegisterUserDto();
        registerUserDto.setUsername(null);
        registerUserDto.setPassword(USER_3.getPassword());
        registerUserDto.setRole("ROLE_USER");
        sut.createUser(mapRegisterUserDtoToUser(registerUserDto));
    }

    @Test(expected = DaoException.class)
    public void createUser_with_existing_username() {
        RegisterUserDto registerUserDto = new RegisterUserDto();
        registerUserDto.setUsername(USER_1.getUsername());
        registerUserDto.setPassword(USER_3.getPassword());
        registerUserDto.setRole("ROLE_USER");
        sut.createUser(mapRegisterUserDtoToUser(registerUserDto));
    }

    @Test(expected = IllegalArgumentException.class)
    public void createUser_with_null_password() {
        RegisterUserDto registerUserDto = new RegisterUserDto();
        registerUserDto.setUsername(USER_3.getUsername());
        registerUserDto.setPassword(null);
        registerUserDto.setRole("ROLE_USER");
        sut.createUser(mapRegisterUserDtoToUser(registerUserDto));
    }

    @Test
    public void createUser_creates_a_user() {
        User user = new User(0, "newuser", "password", "ROLE_USER", "New User", "123 Example St", "Newark", "DE", "19702");
        User createdUser = sut.createUser(user);

        Assert.assertNotNull(createdUser);

        User retrievedUser = sut.getUserByUsername(createdUser.getUsername());
        Assert.assertEquals(retrievedUser.getUsername(), createdUser.getUsername());
        Assert.assertEquals(retrievedUser.getRole(), createdUser.getRole());
    }
}
