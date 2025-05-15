package com.techelevator.dao;

import java.util.List;

import com.techelevator.dto.PatientActivePrescriptionDto;

public interface PatientActivePrescriptionDao {
    List<PatientActivePrescriptionDto> getAllPatientActivePrescriptions();
}
