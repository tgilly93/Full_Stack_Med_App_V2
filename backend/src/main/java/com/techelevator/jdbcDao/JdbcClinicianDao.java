package com.techelevator.jdbcDao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.techelevator.dao.ClinicianDao;
import com.techelevator.model.Clinician;

@Component
public class JdbcClinicianDao implements ClinicianDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcClinicianDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Clinician> clinicianRowMapper  = (rs, rowNum) -> {
        Clinician clinician = new Clinician();
        clinician.setNpiNumber(rs.getInt("npi_number"));
        clinician.setUserId(rs.getInt("user_id"));
        clinician.setStaffId(rs.getInt("staff_id"));
        clinician.setPrimaryOffice(rs.getInt("primary_office"));
        clinician.setClinicianRatePerHour(rs.getBigDecimal("clinician_rate_per_hour"));

        return clinician;
    };

    @Override
    public List<Clinician> getAllClinicians() {
        String sql = "SELECT * FROM Clinician";
        return jdbcTemplate.query(sql, clinicianRowMapper);       
    }

    @Override
    public Clinician getClinicianByNpi(int npiNumber) {
        String sql = "SELECT * FROM Clinician WHERE npi_number = ?";
        return jdbcTemplate.queryForObject(sql, clinicianRowMapper, npiNumber);
    }

    @Override
    public Clinician createClinician(Clinician clinician) {
        String sql = "INSERT INTO Clinician (npi_number, user_id, staff_id, primary_office, clinician_rate_per_hour) VALUES (?, ?, ?, ?, ?)";

        jdbcTemplate.update(sql, 
            clinician.getNpiNumber(),
            clinician.getUserId(),
            clinician.getStaffId(),
            clinician.getPrimaryOffice(),
            clinician.getClinicianRatePerHour());

        return clinician;
    }

    @Override
    public boolean updateClinician(Clinician clinician) {
        String sql = "UPDATE Clinician SET user_id = ?, staff_id = ?, primary_office = ?, clinician_rate_per_hour = ? WHERE npi_number = ?";

        int rowsAffected = jdbcTemplate.update(sql,
            clinician.getUserId(),
            clinician.getStaffId(),
            clinician.getPrimaryOffice(),
            clinician.getClinicianRatePerHour(),
            clinician.getNpiNumber());

        return rowsAffected > 0;
    }

    @Override
    public boolean deleteClinician(int npiNumber) {
        String sql = "DELETE FROM Clinician WHERE npi_number = ?";
        
        int rowsAffected = jdbcTemplate.update(sql, npiNumber);

        return rowsAffected > 0;
    }
}
