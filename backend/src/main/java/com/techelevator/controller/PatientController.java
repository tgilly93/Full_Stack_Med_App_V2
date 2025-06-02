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

import com.techelevator.model.Patient;
import com.techelevator.service.PatientService;

@RestController
@CrossOrigin
@RequestMapping("/api/patients")
public class PatientController {
    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLINICIAN', 'ROLE_RECEPTIONIST')")
    @GetMapping
    public List<Patient> getAllPatients() {
        return patientService.getAllPatients();
    }

    @PreAuthorize("@securityService.isPatientOwnedByUser(#patientId, authentication.principal.userId) or hasAnyRole('ROLE_ADMIN', 'ROLE_CLINICIAN', 'ROLE_RECEPTIONIST')")
    @GetMapping("/id/{patientId}")
    public Patient getPatientByPatientId(@PathVariable int patientId) {
        return patientService.getPatientByPatientId(patientId);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Patient createPatient(@RequestBody Patient patient) {
        return patientService.createPatient(patient);
    }

    @PreAuthorize("@securityService.isPatientOwnedByUser(#patientId, authentication.principal.userId) or hasAnyRole('ROLE_ADMIN', 'ROLE_RECEPTIONIST')")
    @PutMapping("/{patientId}")
    public boolean updatePatient(@PathVariable int patientId, @RequestBody Patient patient) {
        patient.setPatientId(patientId);

        return patientService.updatePatientFromPatient(patient);
    }

    @PreAuthorize("@securityService.isPatientOwnedByUser(#patientId, authentication.principal.userId) or hasRole('ROLE_ADMIN')")
    @DeleteMapping("/delete/{patientId}")
    public boolean deletePatient(@PathVariable int patientId) {
        return patientService.deletePatient(patientId);
    }
}
