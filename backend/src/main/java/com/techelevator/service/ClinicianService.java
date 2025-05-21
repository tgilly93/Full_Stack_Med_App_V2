package com.techelevator.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.techelevator.dao.ClinicianDao;
import com.techelevator.model.Clinician;

@Service
public class ClinicianService {
    private final ClinicianDao clinicianDao;

    public ClinicianService(ClinicianDao clinicianDao) {
        this.clinicianDao = clinicianDao;
    }

    public List<Clinician> getAllClinicians() {
        return clinicianDao.getAllClinicians();
    }

    public Clinician getClinicianByNpi(int npiNumber) {
        return clinicianDao.getClinicianByNpi(npiNumber);
    }

    public Clinician createClinician(Clinician clinician) {
        return clinicianDao.createClinician(clinician);
    }

    public boolean updateClinician(Clinician clinician) {
        return clinicianDao.updateClinician(clinician);
    }

    public boolean deleteClinician(int npiNumber) {
        return clinicianDao.deleteClinician(npiNumber);
    }
}
