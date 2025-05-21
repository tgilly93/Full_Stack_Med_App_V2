package com.techelevator.dao;

import java.util.List;

import com.techelevator.model.Patient;

public interface PatientDao {
    List<Patient> getAllPatients();

    Patient getPatientByPatientId(int patientId);

    Patient createPatient(Patient patient);

    boolean updatePatient(Patient patient);

    boolean deletePatient(int patientId);
}
