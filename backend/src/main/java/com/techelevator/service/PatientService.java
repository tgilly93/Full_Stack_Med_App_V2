package com.techelevator.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.techelevator.dao.PatientDao;
import com.techelevator.model.Patient;

@Service
public class PatientService {
    private final PatientDao patientDao;

    public PatientService(PatientDao patientDao) {
        this.patientDao = patientDao;
    }

    public List<Patient> getAllPatients() {
        return patientDao.getAllPatients();
    }

    public Patient getPatientByPatientId(int patientId) {
        return patientDao.getPatientByPatientId(patientId);
    }

    public Patient createPatient(Patient patient) {
        return patientDao.createPatient(patient);
    }

    public boolean updatePatient(Patient patient) {
        return patientDao.updatePatient(patient);
    }

    public boolean deletePatient(int patientId) {
        return patientDao.deletePatient(patientId);
    }
}
