package com.techelevator.service;

import org.springframework.stereotype.Component;

import com.techelevator.model.Patient;

@Component
public class SecurityService {
    private final PatientService patientService;
    private final ClinicianService clinicianService;

    public SecurityService(PatientService patientService, ClinicianService clinicianService) {
        this.patientService = patientService;
        this.clinicianService = clinicianService;
    }

    public boolean isPatientOwnedByUser(int patientId, int userId) {
        Patient patient = patientService.getPatientByPatientId(patientId);
        return patient != null && patient.getUserId() == userId;
    }

    public boolean isClinicianOwnedByUser(int npiNumber, int userId) {
        var clinician = clinicianService.getClinicianByNpi(npiNumber);
        return clinician != null && clinician.getUserId() == userId;
    }
}
