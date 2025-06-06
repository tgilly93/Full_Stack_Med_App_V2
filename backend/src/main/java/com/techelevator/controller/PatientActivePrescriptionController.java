package com.techelevator.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.dto.PatientActivePrescriptionDto;
import com.techelevator.service.PatientActivePrescriptionService;

@RestController
@CrossOrigin
@RequestMapping("/api/patient-active-rx")
public class PatientActivePrescriptionController {
    private final PatientActivePrescriptionService patientActivePrescriptionService;

    public PatientActivePrescriptionController(PatientActivePrescriptionService patientActivePrescriptionService) {
        this.patientActivePrescriptionService = patientActivePrescriptionService;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLINICIAN', 'ROLE_RECEPTIONIST')")
    @GetMapping
    public List<PatientActivePrescriptionDto> getAllPatientActivePrescriptions() {
        return patientActivePrescriptionService.getAllPatientActivePrescriptions();
    }
}
