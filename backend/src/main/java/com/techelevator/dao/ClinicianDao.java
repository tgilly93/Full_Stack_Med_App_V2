package com.techelevator.dao;

import java.util.List;

import com.techelevator.model.Clinician;

public interface ClinicianDao {
    List<Clinician> getAllClinicians();

    Clinician getClinicianByNpi(int npiNumber);

    Clinician createClinician(Clinician clinician);

    boolean updateClinician(Clinician clinician);

    boolean deleteClinician(int npiNumber);
}
