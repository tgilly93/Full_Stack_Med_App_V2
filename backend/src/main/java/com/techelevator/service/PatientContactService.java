package com.techelevator.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.techelevator.dao.PatientContactDao;
import com.techelevator.dto.PatientContactDto;

@Service
public class PatientContactService {
    private final PatientContactDao patientContactDao;

    public PatientContactService(PatientContactDao patientContactDao) {
        this.patientContactDao = patientContactDao;
    }

    public List<PatientContactDto> getAllPatientContacts() {
        return patientContactDao.getAllPatientContacts();
    }
}
