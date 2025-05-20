package com.techelevator.jdbcDao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.techelevator.dao.OfficeDao;
import com.techelevator.model.Office;

@Component
public class JdbcOfficeDao implements OfficeDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcOfficeDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Office> officeRowMapper = (rs, rowNum) -> {
        Office office = new Office();
        office.setOfficeId(rs.getInt("office_id"));
        office.setOfficeName(rs.getString("office_name"));
        office.setOfficePhoneNumber(rs.getString("office_phone_number"));
        office.setOfficeAddress(rs.getString("office_address"));
        office.setOfficeCity(rs.getString("office_city"));
        office.setState(rs.getString("state"));
        office.setZipCode(rs.getString("zip_code"));
        office.setOfficeOpen(rs.getTime("office_open").toLocalTime());
        office.setOfficeClose(rs.getTime("office_close").toLocalTime());
        return office;
    };

    @Override
    public List<Office> getAllOffices() {
        String sql = "SELECT * FROM office ORDER BY office_name";
        return jdbcTemplate.query(sql, officeRowMapper);
    }

    @Override
    public Office getOfficeById(int officeId) {
        String sql = "SELECT * FROM office WHERE office_id = ?";
        return jdbcTemplate.queryForObject(sql, officeRowMapper, officeId);
    }

    @Override
    public Office createOffice(Office office) {
        String sql = "INSERT INTO office (office_name, office_phone_number, office_address, office_city, state, zip_code, office_open, office_close) "
                +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?) RETURNING office_id";

        Integer newId = jdbcTemplate.queryForObject(sql, Integer.class,
                office.getOfficeName(),
                office.getOfficePhoneNumber(),
                office.getOfficeAddress(),
                office.getOfficeCity(),
                office.getState(),
                office.getZipCode(),
                office.getOfficeOpen(),
                office.getOfficeClose());

        office.setOfficeId(newId);
        return office;
    }

    @Override
    public boolean updateOffice(Office office) {
        String sql = "UPDATE office SET office_name = ?, office_phone_number = ?, office_address = ?, office_city = ?, state = ?, zip_code = ?, office_open = ?, office_close = ? WHERE office_id = ?";

        int rowsAffected = jdbcTemplate.update(sql,
                office.getOfficeName(),
                office.getOfficePhoneNumber(),
                office.getOfficeAddress(),
                office.getOfficeCity(),
                office.getState(),
                office.getZipCode(),
                office.getOfficeOpen(),
                office.getOfficeClose(),
                office.getOfficeId());

        return rowsAffected > 0;
    }

    @Override
    public boolean deleteOffice(int officeId) {
        String sql = "DELETE FROM office WHERE office_id = ?";

        int rowsAffected = jdbcTemplate.update(sql, officeId);
        return rowsAffected > 0;
    }
}
