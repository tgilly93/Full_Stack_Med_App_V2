package com.techelevator.dao;

import java.util.List;
import com.techelevator.dto.PatientContactDto;

public interface PatientContactDao {
    List<PatientContactDto> getAllPatientContacts();
}
