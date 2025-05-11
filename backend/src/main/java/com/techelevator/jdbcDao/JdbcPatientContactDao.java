package com.techelevator.jdbcDao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.techelevator.dao.PatientContactDao;
import com.techelevator.dto.PatientContactDto;

@Component
public class JdbcPatientContactDao implements PatientContactDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcPatientContactDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<PatientContactDto> patientContactRowMapper = (rs, rowNum) -> {
        PatientContactDto patientContact = new PatientContactDto();
        patientContact.setPatientFirstName(rs.getString("patient_first_name"));
        patientContact.setPatientLastName(rs.getString("patient_last_name"));
        patientContact.setPatientAddress(rs.getString("patient_address"));
        patientContact.setPatientCity(rs.getString("patient_city"));
        patientContact.setPatientState(rs.getString("patient_state"));
        patientContact.setZipCode(rs.getString("zip_code"));
        patientContact.setPatientPhoneNumber(rs.getString("patient_phone_number"));
        return patientContact;
    };

    @Override
    public List<PatientContactDto> getAllPatientContacts() {
        String sql = "SELECT * FROM patient_contact";
        return jdbcTemplate.query(sql, patientContactRowMapper);
    }
}
