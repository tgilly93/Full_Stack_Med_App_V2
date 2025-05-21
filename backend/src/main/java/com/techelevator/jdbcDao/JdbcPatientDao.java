package com.techelevator.jdbcDao;

import java.sql.Date;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.techelevator.dao.PatientDao;
import com.techelevator.model.Patient;

@Component
public class JdbcPatientDao implements PatientDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcPatientDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Patient> patientRowMapper = (rs, rowNum) -> {
        Patient patient = new Patient();
        patient.setPatientId(rs.getInt("patient_id"));
        patient.setUserId(rs.getInt("user_id"));
        patient.setPatientFirstName(rs.getString("patient_first_name"));
        patient.setPatientLastName(rs.getString("patient_last_name"));
        patient.setPatientDateOfBirth(rs.getDate("patient_date_of_birth").toLocalDate());       
        patient.setPatientAddress(rs.getString("patient_address"));
        patient.setPatientCity(rs.getString("patient_city"));
        patient.setPatientState(rs.getString("patient_state"));
        patient.setZipCode(rs.getString("zip_code"));
        patient.setPatientPhoneNumber(rs.getString("patient_phone_number"));

        return patient;

    };

    @Override
    public List<Patient> getAllPatients() {
        String sql = "SELECT * FROM Patient ORDER BY patient_last_name";
        return jdbcTemplate.query(sql, patientRowMapper);
    }

    @Override
    public Patient getPatientByPatientId(int patientId) {
        String sql = "SELECT * FROM Patient WHERE patient_id = ?";
        return jdbcTemplate.queryForObject(sql, patientRowMapper, patientId);
    }

    @Override
    public Patient createPatient(Patient patient) {
        String sql = "INSERT INTO Patient (user_id, patient_first_name, patient_last_name, patient_date_of_birth, patient_address, patient_city, patient_state, zip_code, patient_phone_number) " + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING patient_id";

        int newId = jdbcTemplate.queryForObject(sql, Integer.class,
            patient.getUserId(),
            patient.getPatientFirstName(),
            patient.getPatientLastName(),
            Date.valueOf(patient.getPatientDateOfBirth()),
            patient.getPatientAddress(),
            patient.getPatientCity(),
            patient.getPatientState(),
            patient.getZipCode(),
            patient.getPatientPhoneNumber());

        patient.setPatientId(newId);

        return patient;
    }

    @Override
    public boolean updatePatient(Patient patient) {
        String sql = "UPDATE Patient SET user_id = ?, patient_first_name = ?, patient_last_name = ?, patient_date_of_birth = ?, patient_address = ?, patient_city = ?, patient_state = ?, zip_code = ?, patient_phone_number = ? WHERE patient_id = ?";

        int rowsAffected = jdbcTemplate.update(sql, 
            patient.getUserId(),
            patient.getPatientFirstName(),
            patient.getPatientLastName(),
            Date.valueOf(patient.getPatientDateOfBirth()),
            patient.getPatientAddress(),
            patient.getPatientCity(),
            patient.getPatientState(),
            patient.getZipCode(),
            patient.getPatientPhoneNumber(),
            patient.getPatientId());

        return rowsAffected > 0;
    }

    @Override 
    public boolean deletePatient(int patientId) {
        String sql = "DELETE FROM Patient WHERE patient_id = ?";

        int rowsAffected = jdbcTemplate.update(sql, patientId);

        return rowsAffected > 0;
    }
}
