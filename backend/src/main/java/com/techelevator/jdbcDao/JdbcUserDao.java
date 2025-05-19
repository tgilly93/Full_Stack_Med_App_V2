package com.techelevator.jdbcDao;

import java.util.List;

import com.techelevator.dao.UserDao;
import com.techelevator.model.Users;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class JdbcUserDao implements UserDao {
    private final JdbcTemplate jdbcTemplate;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public JdbcUserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Users> usersRowMapper = (rs, rowNum) -> {
        Users user = new Users();
        user.setUserId(rs.getInt("user_id"));
        user.setUsername(rs.getString("username"));
        user.setPasswordHash(rs.getString("password_hash"));
        user.setRole(rs.getString("role"));
        user.setName(rs.getString("name"));
        user.setAddress(rs.getString("address"));
        user.setCity(rs.getString("city"));
        user.setStateCode(rs.getString("state_code"));
        user.setZip(rs.getString("zip"));
        return user;
    };

    @Override
    public List<Users> getAllUsers() {
        String sql = "SELECT * FROM users";
        return jdbcTemplate.query(sql, usersRowMapper);
    }

    @Override
    public Users getUserByUserId(int userId) {
        String sql = "SELECT * FROM users WHERE user_id = ?";
        return jdbcTemplate.queryForObject(sql, usersRowMapper, userId);
    }

    @Override
    public Users getUserByUsername(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";
        return jdbcTemplate.queryForObject(sql, usersRowMapper, username);
    }

    @Override
    public Users createUser(Users user) {
        String sql = "INSERT INTO users (username, password_hash, role, name, address, city, state_code, zip) " + "VALUES (?, ?, ?, ?, ?, ?, ?, ?) RETURNING user_id";

        String hashedPassword = passwordEncoder.encode(user.getPasswordHash());

        int newUserId = jdbcTemplate.queryForObject(sql, Integer.class, user.getUsername(), hashedPassword, user.getRole(), user.getName(), user.getAddress(), user.getCity(), user.getStateCode(), user.getZip());

        user.setUserId(newUserId);
        user.setPasswordHash(hashedPassword);

        return user;
    }

    @Override
    public boolean updateUser(Users user) {
        String sql = "UPDATE users SET username = ?, password_hash = ?, role = ?, name = ?, address = ?, city = ?, state_code = ?, zip = ? WHERE user_id = ?";

        int rowsAffected = jdbcTemplate.update(sql, 
            user.getUsername(),
            user.getPasswordHash(),
            user.getRole(),
            user.getName(),
            user.getAddress(),
            user.getCity(),
            user.getStateCode(),
            user.getZip(),
            user.getUserId());

            return rowsAffected > 0;
    }

    @Override
    public boolean deleteUser(int userId) {
        String sql = "DELETE FROM users WHERE user_id = ?";
        int rowsAffected = jdbcTemplate.update(sql, userId);

        return rowsAffected > 0;
    }
}
