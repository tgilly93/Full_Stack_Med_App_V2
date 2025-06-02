package com.techelevator.service;

import org.springframework.stereotype.Component;

import com.techelevator.model.Patient;

@Component
public class SecurityService {
    private final PatientService patientService;

    public SecurityService(PatientService patientService) {
        this.patientService = patientService;
    }

    public boolean isPatientOwnedByUser(int patientId, int userId) {
        Patient patient = patientService.getPatientByPatientId(patientId);
        return patient != null && patient.getUserId() == userId;
    }
}
