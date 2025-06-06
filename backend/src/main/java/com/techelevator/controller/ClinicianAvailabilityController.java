package com.techelevator.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.dto.ClinicianAvailabilityDto;
import com.techelevator.service.ClinicianAvailabilityService;

@RestController
@CrossOrigin
@RequestMapping("/api/clinician-availability")
public class ClinicianAvailabilityController {
    private final ClinicianAvailabilityService clinicianAvailabilityService;

    public ClinicianAvailabilityController(ClinicianAvailabilityService clinicianAvailabilityService) {
        this.clinicianAvailabilityService = clinicianAvailabilityService;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLINICIAN', 'ROLE_RECEPTIONIST', 'ROLE_PATIENT')")
    @GetMapping
    public List<ClinicianAvailabilityDto> getAllClinicianAvailability() {
        return clinicianAvailabilityService.getAllClinicianAvailability();
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLINICIAN', 'ROLE_RECEPTIONIST', 'ROLE_PATIENT')")
    @GetMapping("/npi/{npiNumber}")
    public List<ClinicianAvailabilityDto> getClinicianAvailabilityByNpiNumber(@PathVariable int npiNumber) {
        return clinicianAvailabilityService.getClinicianAvailabilityByNpiNumber(npiNumber);
    }
}
