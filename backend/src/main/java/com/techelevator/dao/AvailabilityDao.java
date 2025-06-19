package com.techelevator.dao;

import com.techelevator.model.Availability;

import java.time.LocalDate;
import java.util.List;

public interface AvailabilityDao {

    Availability addAvailability(Availability availability);

    boolean updateAvailability(Availability availability);

    boolean deleteAvailability(int availabilityId);

    List<Availability> getAvailabilityByDoctorId(int npiNumber);

    List<Availability> getAvailabilityByDate(LocalDate date);

    List<Availability> getAvailabilityByDoctorIdAndDate(int npiNumber, LocalDate date);

    Availability getAvailabilityById(int availabilityId);
}
