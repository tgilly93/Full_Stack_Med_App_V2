package com.techelevator.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
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

    @GetMapping
    public List<Clinician> getAllClinicians() {
        return clinicianService.getAllClinicians();
    }

    @GetMapping("/{npiNumber}")
    public Clinician getClinicianByNpi(@PathVariable int npiNumber) {
        return clinicianService.getClinicianByNpi(npiNumber);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Clinician createClinician(@RequestBody Clinician clinician) {
        return clinicianService.createClinician(clinician);
    }

    @PutMapping("/{npiNumber}")
    public boolean updateClinician(@PathVariable int npiNumber, @RequestBody Clinician clinician) {
        clinician.setNpiNumber(npiNumber);
        
        return clinicianService.updateClinician(clinician);
    }

    @DeleteMapping("/{npiNumber}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean deleteClinician(@PathVariable int npiNumber) {
        return clinicianService.deleteClinician(npiNumber);
    }
}
