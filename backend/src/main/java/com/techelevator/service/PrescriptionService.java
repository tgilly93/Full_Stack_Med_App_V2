package com.techelevator.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.techelevator.dao.PrescriptionDao;
import com.techelevator.model.Prescription;

@Service
public class PrescriptionService {
    private final PrescriptionDao prescriptionDao;

    public PrescriptionService(PrescriptionDao prescriptionDao) {
        this.prescriptionDao = prescriptionDao;
    }

    public boolean addPrescription(Prescription prescription) {
        return prescriptionDao.addPrescription(prescription);
    }

    public boolean updatePrescription(Prescription prescription) {
        return prescriptionDao.updatePrescription(prescription);
    }

    public boolean deletePrescription(int prescription_id) {
        return prescriptionDao.deletePrescription(prescription_id);
    }

    public List<Prescription> getAllPrescriptions() {
        return prescriptionDao.getAllPrescriptions();
    }

    public List<Prescription> getPrescriptionsByPatientId(int patient_id) {
        return prescriptionDao.getPrescriptionsByPatientId(patient_id);
    }
}
