package com.techelevator.jdbcDao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.techelevator.dao.PatientActivePrescriptionDao;
import com.techelevator.dto.PatientActivePrescriptionDto;

@Component
public class JdbcPatientActivePrescriptionDao implements PatientActivePrescriptionDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcPatientActivePrescriptionDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<PatientActivePrescriptionDto> patientActivePrescriptionRowMapper = (rs, rowNum) -> {
        PatientActivePrescriptionDto patientActivePrescription = new PatientActivePrescriptionDto();
        patientActivePrescription.setPatientId(rs.getInt("Patient ID"));
        patientActivePrescription.setName(rs.getString("Name"));
        patientActivePrescription.setDob(rs.getDate("DOB").toLocalDate());
        patientActivePrescription.setStreetAddress(rs.getString("Street Address"));
        patientActivePrescription.setCity(rs.getString("City"));
        patientActivePrescription.setState(rs.getString("State"));
        patientActivePrescription.setZipCode(rs.getString("Zip Code"));
        patientActivePrescription.setPhone(rs.getString("Phone"));
        patientActivePrescription.setPrescriptionId(rs.getInt("Prescription ID"));
        patientActivePrescription.setCommonName(rs.getString("Common Name"));
        patientActivePrescription.setDescription(rs.getString("Description"));
        patientActivePrescription.setPrescriptionStatus(rs.getString("Prescription Status"));
        patientActivePrescription.setPrescribingClinician(rs.getString("Prescribing Clinician"));
        return patientActivePrescription;
    };

    @Override
    public List<PatientActivePrescriptionDto> getAllPatientActivePrescriptions() {
        String sql = "SELECT * FROM patient_active_prescription";
        return jdbcTemplate.query(sql, patientActivePrescriptionRowMapper);
    }
}
