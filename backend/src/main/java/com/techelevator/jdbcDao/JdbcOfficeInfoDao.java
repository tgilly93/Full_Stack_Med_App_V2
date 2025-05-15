package com.techelevator.jdbcDao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.techelevator.dao.OfficeInfoDao;
import com.techelevator.dto.OfficeInfoDto;

@Component
public class JdbcOfficeInfoDao implements OfficeInfoDao{
    private final JdbcTemplate jdbcTemplate;

    public JdbcOfficeInfoDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<OfficeInfoDto> officeInfoRowMapper = (rs, rowNum) -> {
        OfficeInfoDto officeInfo = new OfficeInfoDto();
        officeInfo.setName(rs.getString("Name"));
        officeInfo.setLocation(rs.getString("Location"));
        officeInfo.setCity(rs.getString("City"));
        officeInfo.setState(rs.getString("State"));
        officeInfo.setZipCode(rs.getString("Zip Code"));
        officeInfo.setPhoneNumber(rs.getString("Phone Number"));
        officeInfo.setHoursOfOperation(rs.getString("Hours of Operation"));
        return officeInfo;
    };

    @Override
    public List<OfficeInfoDto> getAllOfficesInfo() {
        String sql = "SELECT * FROM office_info";
        return jdbcTemplate.query(sql, officeInfoRowMapper);
    }
}
