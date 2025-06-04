package com.techelevator.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.model.Clinician;
import com.techelevator.service.ClinicianService;

@RestController
@CrossOrigin
@RequestMapping("/api/clinicians")
public class ClinicianController {
    private final ClinicianService clinicianService;

    public ClinicianController(ClinicianService clinicianService) {
        this.clinicianService = clinicianService;
    }


    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLINICIAN', 'ROLE_RECEPTIONIST', 'ROLE_PATIENT')")
    @GetMapping
    public List<Clinician> getAllClinicians() {
        return clinicianService.getAllClinicians();
    }

    @PreAuthorize("@securityService.isClinicianOwnedByUser(#npiNumber, authentication.principal.userId) or hasAnyRole('ROLE_ADMIN', 'ROLE_RECEPTIONIST')")
    @GetMapping("/npi/{npiNumber}")
    public Clinician getClinicianByNpi(@PathVariable int npiNumber) {
        return clinicianService.getClinicianByNpi(npiNumber);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Clinician createClinician(@RequestBody Clinician clinician) {
        return clinicianService.createClinician(clinician);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{npiNumber}")
    public boolean updateClinician(@PathVariable int npiNumber, @RequestBody Clinician clinician) {
        clinician.setNpiNumber(npiNumber);
        
        return clinicianService.updateClinician(clinician);
    }

    @PreAuthorize("@securityService.isClinicianOwnedByUser(#npiNumber, authentication.principal.userId) or hasRole('ROLE_ADMIN')")
    @DeleteMapping("/delete/{npiNumber}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean deleteClinician(@PathVariable int npiNumber) {
        return clinicianService.deleteClinician(npiNumber);
    }
}
