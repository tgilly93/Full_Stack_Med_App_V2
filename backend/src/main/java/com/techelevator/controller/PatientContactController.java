package com.techelevator.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.dto.PatientContactDto;
import com.techelevator.service.PatientContactService;

@RestController
@CrossOrigin
@RequestMapping("/api/patient-contacts")
public class PatientContactController {
    private final PatientContactService patientContactService;

    public PatientContactController(PatientContactService patientContactService) {
        this.patientContactService = patientContactService;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLINICIAN', 'ROLE_RECEPTIONIST')")
    @GetMapping
    public List<PatientContactDto> getAllPatientContacts() {
        return patientContactService.getAllPatientContacts();
    }
}
