package com.techelevator.jdbcDao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.dao.PrescriptionDao;
import com.techelevator.model.Prescription;

@Component
public class JdbcPrescriptionDao implements PrescriptionDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcPrescriptionDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    private final RowMapper<Prescription> prescriptionRowMapper = (rs, rowNum) -> {
        Prescription prescription = new Prescription();
        prescription.setPrescriptionId(rs.getInt("prescription_id"));
        prescription.setPrescriptionName(rs.getString("prescription_name"));
        prescription.setPatientId(rs.getInt("patient_id"));
        prescription.setNpiNumber(rs.getInt("npi_number"));
        prescription.setPrescriptionDetails(rs.getString("prescription_details"));
        prescription.setPrescriptionCost(rs.getBigDecimal("prescription_cost"));
        prescription.setInsuranceCoverage(rs.getString("insurance_coverage"));
        prescription.setPrescriptionStatus(rs.getString("prescription_status"));
        return prescription;

    };

    @Override
    public boolean addPrescription(Prescription prescription) {
        String sql = "INSERT INTO Prescription (prescription_name, patient_id, npi_number, prescription_details, prescription_cost, insurance_coverage, prescription_status) " + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        int rowsAffected = jdbcTemplate.update(sql,
            prescription.getPrescriptionName(),
            prescription.getPatientId(),
            prescription.getNpiNumber(),
            prescription.getPrescriptionDetails(),
            prescription.getPrescriptionCost(),
            prescription.getInsuranceCoverage(),
            prescription.getPrescriptionStatus());

        return rowsAffected > 0;
    }

    @Override
    public boolean updatePrescription(Prescription prescription) {
        String sql = "UPDATE Prescription SET " +
             "prescription_name = ?, " +
             "patient_id = ?, " +
             "npi_number = ?, " +
             "prescription_details = ?, " +
             "prescription_cost = ?, " +
             "insurance_coverage = ?, " +
             "prescription_status = ? " +
             "WHERE prescription_id = ?";

        int rowsAffected = jdbcTemplate.update(sql,
            prescription.getPrescriptionName(),
            prescription.getPatientId(),
            prescription.getNpiNumber(),
            prescription.getPrescriptionDetails(),
            prescription.getPrescriptionCost(),
            prescription.getInsuranceCoverage(),
            prescription.getPrescriptionStatus(),
            prescription.getPrescriptionId());

            return rowsAffected > 0;
    }

    @Override
    public boolean deletePrescription(int prescription_id) {
        String sql = "DELETE FROM Prescription WHERE prescription_id = ?";
        int rowsAffected = jdbcTemplate.update(sql, prescription_id);

        return rowsAffected > 0;
    }

    @Override
    public List<Prescription> getAllPrescriptions() {
        String sql = "SELECT * FROM Prescription";
        return jdbcTemplate.query(sql, prescriptionRowMapper);
    }

    @Override 
    public List<Prescription> getPrescriptionsByPatientId(int patient_id) {
        String sql = "SELECT * FROM Prescription WHERE patient_id = ?";
        return jdbcTemplate.query(sql, prescriptionRowMapper, patient_id);
    }

    }
    

