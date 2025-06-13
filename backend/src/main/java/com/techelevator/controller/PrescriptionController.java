package com.techelevator.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.techelevator.model.Prescription;
import com.techelevator.service.PrescriptionService;


@RestController
@CrossOrigin
@RequestMapping("/api/prescriptions")
public class PrescriptionController {
    private final PrescriptionService prescriptionService;

    public PrescriptionController(PrescriptionService prescriptionService) {
        this.prescriptionService = prescriptionService;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLINICIAN')")
    @PostMapping
    public Prescription addPrescription(@RequestBody Prescription prescription) {
        return prescriptionService.addPrescription(prescription);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLINICIAN')")
    @GetMapping
    public List<Prescription> getAllPrescriptions() {
        return prescriptionService.getAllPrescriptions();
    }

    @PreAuthorize("@securityService.isPatientOwnedByUser(#patientId, authentication.principal.userId) or hasAnyRole('ROLE_ADMIN', 'ROLE_CLINICIAN', 'ROLE_RECEPTIONIST')")
    @GetMapping("/patient/{patientId}")
    public List<Prescription> getPrescriptionsByPatientId(@PathVariable int patientId) {
        return prescriptionService.getPrescriptionsByPatientId(patientId);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLINICIAN')")
    @PutMapping("/{prescriptionId}")
    public boolean updatePrescription(@PathVariable int prescriptionId, @RequestBody Prescription prescription) {
        prescription.setPrescriptionId(prescriptionId);
        return prescriptionService.updatePrescription(prescription);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLINICIAN')")
    @DeleteMapping("/{prescriptionId}")
    public boolean deletePrescription(@PathVariable int prescriptionId) {
        return prescriptionService.deletePrescription(prescriptionId);
    }
}
