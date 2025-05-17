package com.techelevator.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.techelevator.dao.PrescriptionDao;
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

    @PostMapping
    public boolean addPrescription(@RequestBody Prescription prescription) {
        return prescriptionService.addPrescription(prescription);
    }

    @GetMapping
    public List<Prescription> getAllPrescriptions() {
        return prescriptionService.getAllPrescriptions();
    }

    @GetMapping("/patient/{patient_id}")
    public List<Prescription> getPrescriptionsByPatientId(@PathVariable int patient_id) {
        return prescriptionService.getPrescriptionsByPatientId(patient_id);
    }

    @PutMapping("/{prescriptionId}")
    public boolean updatePrescription(@PathVariable int prescriptionId, @RequestBody Prescription prescription) {
        prescription.setPrescriptionId(prescriptionId);
        return prescriptionService.updatePrescription(prescription);
    }

    @DeleteMapping("/{prescriptionId}")
    public boolean deletePrescription(@PathVariable int prescriptionId) {
        return prescriptionService.deletePrescription(prescriptionId);
    }
}
