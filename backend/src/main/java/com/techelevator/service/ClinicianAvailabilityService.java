package com.techelevator.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.techelevator.dao.ClinicianAvailabilityDao;
import com.techelevator.dto.ClinicianAvailabilityDto;

@Service
public class ClinicianAvailabilityService {
    private final ClinicianAvailabilityDao clinicianAvailabilityDao;

    public ClinicianAvailabilityService(ClinicianAvailabilityDao clinicianAvailabilityDao) {
        this.clinicianAvailabilityDao = clinicianAvailabilityDao;
    }

    public List<ClinicianAvailabilityDto> getAllClinicianAvailability() {
        return clinicianAvailabilityDao.getAllClinicianAvailability();
    }

    public List<ClinicianAvailabilityDto> getClinicianAvailabilityByNpiNumber(int npiNumber) {
        return clinicianAvailabilityDao.getClinicianAvailabilityByNpiNumber(npiNumber);
    }
}
