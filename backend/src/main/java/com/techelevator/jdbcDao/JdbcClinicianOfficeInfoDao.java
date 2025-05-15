package com.techelevator.jdbcDao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.techelevator.dao.ClinicianOfficeInfoDao;
import com.techelevator.dto.ClinicianOfficeInfoDto;

@Component
public class JdbcClinicianOfficeInfoDao implements ClinicianOfficeInfoDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcClinicianOfficeInfoDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<ClinicianOfficeInfoDto> clinicianOfficeInfoRowMapper = (rs, rowNum) -> {
        ClinicianOfficeInfoDto clinicianOfficeInfo = new ClinicianOfficeInfoDto();
        clinicianOfficeInfo.setPhysician(rs.getString("Physician"));
        clinicianOfficeInfo.setPrimaryLocation(rs.getString("Primary Location"));
        clinicianOfficeInfo.setAddress(rs.getString("Address"));
        clinicianOfficeInfo.setCity(rs.getString("City"));
        clinicianOfficeInfo.setState(rs.getString("State"));
        clinicianOfficeInfo.setZipCode(rs.getString("Zip Code"));
        clinicianOfficeInfo.setPhoneNumber(rs.getString("Phone Number"));
        clinicianOfficeInfo.setHoursOfOperation(rs.getString("Hours of Operation"));
        return clinicianOfficeInfo;
    };

    @Override
    public List<ClinicianOfficeInfoDto> getAllClinicianOfficesInfo() {
        String sql = "SELECT * FROM clinician_office_info";
        return jdbcTemplate.query(sql, clinicianOfficeInfoRowMapper);
    }
}
