package com.techelevator.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.techelevator.dao.PatientDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Patient;

@Service
public class PatientService {
    private final PatientDao patientDao;
    private final UserDao userDao;

    public PatientService(PatientDao patientDao, UserDao userDao) {
        this.patientDao = patientDao;
        this.userDao = userDao;
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

    @Transactional
    public boolean updatePatientFromPatient(Patient patient) {
        boolean patientUpdated = patientDao.updatePatient(patient);
        boolean userUpdated = userDao.updateUserDetailsFromPatient(patient);
        return patientUpdated && userUpdated;
    }

    @Transactional
    public boolean deletePatient(int patientId) {
        Patient patient = patientDao.getPatientByPatientId(patientId);
        if (patient == null) {
            return false;
        }

        boolean patientDeleted = patientDao.deletePatient(patientId);
        boolean userDeleted = userDao.deleteUser(patient.getUserId());
        return patientDeleted && userDeleted;
    }
}
