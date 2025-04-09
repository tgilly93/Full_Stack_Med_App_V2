package com.techelevator.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.model.Prescription;

@Component
public class JdbcPrescription implements PrescriptionDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcPrescription(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    private Prescription mapRowToPrescription(SqlRowSet rs) {
        Prescription newPrescription = new Prescription();
        newPrescription.setPatientId( rs.getInt("patient_id"));
        newPrescription.setPrescriptionId(rs.getInt("prescription_id"));
        newPrescription.setPrescriptionName(rs.getString("prescription_name"));
        newPrescription.setDoctorId(rs.getInt("npi_number"));
        newPrescription.setPrescriptionDescription(rs.getString("prescription_details"));
        newPrescription.setPrescriptionCost(rs.getDouble("prescription_cost"));
        newPrescription.setInsuranceCoverage(rs.getString("insurance_coverage"));
        newPrescription.setPrescriptionStatus(rs.getString("prescription_status"));

        return newPrescription;

    }
    @Override
    public Prescription getPrescriptionById(int prescriptionId) {
        String sql = "SELECT prescription_id, patient_id, npi_number, prescription_name, prescription_details, prescription_cost, insurance_coverage, prescription_status\n" +
                "FROM public.prescription WHERE prescription_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, prescriptionId);
        if (results.next()) {
            return mapRowToPrescription(results);
        }
        return null;
    }

    @Override
    public Prescription getPrescriptionByPatientId(int patientId) {
        String sql = "SELECT prescription_id, patient_id, npi_number, prescription_name, prescription_details, prescription_cost, insurance_coverage, prescription_status\n" +
                "FROM public.prescription WHERE patient_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, patientId);
        if (results.next()) {
            return mapRowToPrescription(results);
        }
        return null;
    }

    @Override
    public Prescription getPrescriptionByDoctorId(int doctorId) {
        String sql = "SELECT prescription_id, patient_id, npi_number, prescription_name, prescription_details, prescription_cost, insurance_coverage, prescription_status\n" +
                "FROM public.prescription WHERE npi_number = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, doctorId);
        if (results.next()) {
            return mapRowToPrescription(results);
        }
        return null;
    }

    @Override
    public Prescription getPrescriptionByPrescriptionName(String prescriptionName) {
        String sql = "SELECT prescription_id, patient_id, npi_number, prescription_name, prescription_details, prescription_cost, insurance_coverage, prescription_status\n" +
                "FROM public.prescription WHERE prescription_name = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, prescriptionName);
        if (results.next()) {
            return mapRowToPrescription(results);
        }
        return null;
    }

    @Override
    public List<Prescription> getAllPrescriptionsByPatientId(int patientId) {
        String sql = "SELECT prescription_id, patient_id, npi_number, prescription_name, prescription_details, prescription_cost, insurance_coverage, prescription_status\n" +
                "FROM public.prescription WHERE patient_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, patientId);
        List<Prescription> prescriptions = new ArrayList<>();
        while (results.next()) {
            prescriptions.add(mapRowToPrescription(results));
        }
        return prescriptions;
    }

    @Override
    public List<Prescription> getPatientPrescriptionByStatus(int patientId, String prescriptionStatus) {
        String sql = "SELECT prescription_id, patient_id, npi_number, prescription_name, prescription_details, prescription_cost, insurance_coverage, prescription_status\n" +
                "FROM public.prescription WHERE patient_id = ? AND prescription_status = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, patientId, prescriptionStatus);
        List<Prescription> prescriptions = new ArrayList<>();
        while (results.next()) {
            prescriptions.add(mapRowToPrescription(results));
        }
        return prescriptions;
    }

    @Override
    public List<Prescription> getAllPrescriptionsByDoctorId(int doctorId) {
        String sql = "SELECT prescription_id, patient_id, npi_number, prescription_name, prescription_details, prescription_cost, insurance_coverage, prescription_status\n" +
                "FROM public.prescription WHERE npi_number = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, doctorId);
        List<Prescription> prescriptions = new ArrayList<>();
        while (results.next()) {
            prescriptions.add(mapRowToPrescription(results));
        }
        return prescriptions;
    }


    @Override
    public boolean updatePrescription(Prescription prescription) {
        String sql = "UPDATE Prescription SET patient_id = ?, npi_number = ?, prescription_name = ?, prescription_details = ?, prescription_cost = ?, insurance_coverage = ?, prescription_status = ? WHERE prescription_id = ?";
        return jdbcTemplate.update(sql, prescription.getPatientId(), prescription.getDoctorId(), prescription.getPrescriptionName(), prescription.getPrescriptionDescription(), prescription.getPrescriptionCost(), prescription.getInsuranceCoverage(), prescription.getPrescriptionStatus(), prescription.getPrescriptionId()) > 0;
    }

    @Override
    public boolean addPrescription(Prescription prescription) {
        String sql = "INSERT INTO Prescription (patient_id, npi_number, prescription_name, prescription_details, prescription_cost, insurance_coverage, prescription_status) VALUES (?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, prescription.getPatientId(), prescription.getDoctorId(), prescription.getPrescriptionName(), prescription.getPrescriptionDescription(), prescription.getPrescriptionCost(), prescription.getInsuranceCoverage(), prescription.getPrescriptionStatus()) > 0;
    }

    @Override
    public boolean deletePrescription(int prescriptionId) {
        String sql = "DELETE FROM Prescription WHERE prescription_id = ?";
        return jdbcTemplate.update(sql, prescriptionId) > 0;
    }

    @Override
    public boolean deletePrescriptionByPatientId(int patientId) {
        String sql = "DELETE FROM Prescription WHERE patient_id = ?";
        return jdbcTemplate.update(sql, patientId) > 0;
      
    }


}