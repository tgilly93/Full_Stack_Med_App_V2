package com.techelevator.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.techelevator.dao.AvailabilityDao;
import com.techelevator.model.Availability;

@Service
public class AvailabilityService { 
    private final AvailabilityDao availabilityDao;

    public AvailabilityService(AvailabilityDao availabilityDao) {
        this.availabilityDao = availabilityDao;
    }

    public Availability addAvailability(Availability availability) {
        return availabilityDao.addAvailability(availability);
    }

    public boolean updateAvailability(Availability availability) {
        return availabilityDao.updateAvailability(availability);
    }

    public boolean deleteAvailability(int availabilityId) {
        return availabilityDao.deleteAvailability(availabilityId);
    }

    public List<Availability> getAvailabilityByDoctorId(int npiNumber) {
        return availabilityDao.getAvailabilityByDoctorId(npiNumber);
    }

    public List<Availability> getAvailabilityByDate(LocalDate date){
        return availabilityDao.getAvailabilityByDate(date);
    }

    public List<Availability> getAvailabilityByDoctorIdAndDate(int npiNumber, LocalDate date) {
        return availabilityDao.getAvailabilityByDoctorIdAndDate(npiNumber, date);
    }

    public Availability getAvailabilityById(int availabilityId) {
        return availabilityDao.getAvailabilityById(availabilityId);
    }
}
