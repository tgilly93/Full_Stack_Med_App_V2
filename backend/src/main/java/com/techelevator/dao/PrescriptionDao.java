package com.techelevator.dao;

import java.util.List;

import com.techelevator.model.Prescription;

public interface PrescriptionDao {
    boolean addPrescription(Prescription prescription);

    boolean updatePrescription(Prescription prescription);

    boolean deletePrescription(int prescriptionId);

    List<Prescription> getAllPrescriptions();

    List<Prescription> getPrescriptionsByPatientId(int patientId);
}
