package com.techelevator.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.techelevator.dao.PatientActivePrescriptionDao;
import com.techelevator.dto.PatientActivePrescriptionDto;

@Service
public class PatientActivePrescriptionService {
    private final PatientActivePrescriptionDao patientActivePrescriptionDao;

    public PatientActivePrescriptionService(PatientActivePrescriptionDao patientActivePrescriptionDao) {
        this.patientActivePrescriptionDao = patientActivePrescriptionDao;
    }

    public List<PatientActivePrescriptionDto> getAllPatientActivePrescriptions(){
        return patientActivePrescriptionDao.getAllPatientActivePrescriptions();
    }
}
