package com.techelevator.jdbcDao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.techelevator.dao.ClinicianAvailabilityDao;
import com.techelevator.dto.ClinicianAvailabilityDto;

@Component
public class JdbcClinicianAvailabilityDao implements ClinicianAvailabilityDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcClinicianAvailabilityDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<ClinicianAvailabilityDto> clinicianAvailabilityRowMapper = (rs, rowNum) -> {
        ClinicianAvailabilityDto clinicianAvailability = new ClinicianAvailabilityDto();
        clinicianAvailability.setNpiNumber(rs.getInt("npi_number"));
        clinicianAvailability.setLocation(rs.getString("location"));
        clinicianAvailability.setClinician(rs.getString("clinician"));
        clinicianAvailability.setDayOfWeek(rs.getString("day_of_week"));
        clinicianAvailability.setStartTime(rs.getTime("start_time").toLocalTime());
        clinicianAvailability.setEndTime(rs.getTime("end_time").toLocalTime());
        clinicianAvailability.setAvailable(rs.getBoolean("is_available"));
        return clinicianAvailability;
    };

    @Override
    public List<ClinicianAvailabilityDto> getAllClinicianAvailability() {
        String sql = "SELECT * FROM clinician_availability";
        return jdbcTemplate.query(sql, clinicianAvailabilityRowMapper);
    }

    @Override
    public List<ClinicianAvailabilityDto> getClinicianAvailabilityByNpiNumber(int npiNumber) {
        String sql = "SELECT * FROM clinician_availability WHERE npi_number = ?";
        return jdbcTemplate.query(sql, clinicianAvailabilityRowMapper, npiNumber);
    }
}
