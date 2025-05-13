package com.techelevator.dao;

import java.util.List;

import com.techelevator.dto.ClinicianAvailabilityDto;

public interface ClinicianAvailabilityDao {
    List<ClinicianAvailabilityDto> getAllClinicianAvailability();

    List<ClinicianAvailabilityDto> getClinicianAvailabilityByNpiNumber(int npiNumber);
}
