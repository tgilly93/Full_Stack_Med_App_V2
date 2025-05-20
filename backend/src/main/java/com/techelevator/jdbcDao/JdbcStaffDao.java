package com.techelevator.jdbcDao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.techelevator.dao.StaffDao;
import com.techelevator.model.Staff;

@Component
public class JdbcStaffDao implements StaffDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcStaffDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Staff> staffRowMapper = (rs, rowNum) -> {
        Staff staff = new Staff();
        staff.setStaffId(rs.getInt("staff_id"));
        staff.setOfficeId(rs.getInt("office_id"));
        staff.setStaffFirstName(rs.getString("staff_first_name"));
        staff.setStaffLastName(rs.getString("staff_last_name"));
        staff.setStaffAddress(rs.getString("staff_address"));
        staff.setStaffPhoneNumber(rs.getString("staff_phone_number"));
        return staff;
    };

    @Override
    public List<Staff> getAllStaff() {
        String sql = "SELECT * FROM staff ORDER BY staff_last_name";
        return jdbcTemplate.query(sql, staffRowMapper);
    }

    @Override
    public Staff getStaffById(int staffId) {
        String sql = "SELECT * FROM staff WHERE staff_id = ?";
        return jdbcTemplate.queryForObject(sql, staffRowMapper, staffId);
    }

    @Override
    public Staff createStaff(Staff staff) {
        String sql = "INSERT INTO staff (office_id, staff_first_name, staff_last_name, staff_address, staff_phone_number) " + "VALUES (?, ?, ?, ?, ?) RETURNING staff_id";

        int newId = jdbcTemplate.queryForObject(sql, Integer.class,
            staff.getOfficeId(),
            staff.getStaffFirstName(),
            staff.getStaffLastName(),
            staff.getStaffAddress(),
            staff.getStaffPhoneNumber());

            staff.setStaffId(newId);
            return staff;
    }

    @Override
    public boolean updateStaff(Staff staff) {
        String sql = "UPDATE staff SET office_id = ?, staff_first_name = ?, staff_last_name = ?, staff_address = ?, staff_phone_number = ? WHERE staff_id = ?";

        int rowsAffected = jdbcTemplate.update(sql,
            staff.getOfficeId(),
            staff.getStaffFirstName(),
            staff.getStaffLastName(),
            staff.getStaffAddress(),
            staff.getStaffPhoneNumber(),
            staff.getStaffId());

        return rowsAffected > 0;
    }

    @Override
    public boolean deleteStaff(int staffId) {
        String sql = "DELETE FROM staff WHERE staff_id = ?";

        int rowsAffected = jdbcTemplate.update(sql, staffId);

        return rowsAffected > 0;
    }
}
