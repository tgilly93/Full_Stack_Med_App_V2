package com.techelevator.dao;

import java.util.List;

import com.techelevator.model.Prescription;

public interface PrescriptionDao {
    Prescription getPrescriptionById(int prescriptionId);
    Prescription getPrescriptionByPatientId(int patientId);
    Prescription getPrescriptionByDoctorId(int doctorId);
    Prescription getPrescriptionByPrescriptionName(String prescriptionName);
    List<Prescription> getAllPrescriptionsByPatientId(int patientId);
    List<Prescription> getAllPrescriptionsByDoctorId(int doctorId);
    List<Prescription> getPatientPrescriptionByStatus(int patientId, String prescriptionStatus);
    boolean updatePrescription(Prescription prescription);
    boolean addPrescription(Prescription prescription);
    boolean deletePrescription(int prescriptionId);
    boolean deletePrescriptionByPatientId(int patientId);
}
