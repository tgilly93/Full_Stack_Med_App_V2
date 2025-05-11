package com.techelevator.controller;

import java.util.List;

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

    @GetMapping
    public List<PatientContactDto> getAllPatientContacts() {
        return patientContactService.getAllPatientContacts();
    }
}
