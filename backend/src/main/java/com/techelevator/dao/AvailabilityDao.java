package com.techelevator.dao;

import com.techelevator.model.Availability;

import java.time.LocalDate;
import java.util.List;

public interface AvailabilityDao {

    boolean addAvailability(Availability availability);

    boolean updateAvailability(Availability availability);

    boolean deleteAvailability(int availabilityId);

    List<Availability> getAvailabilityByDoctorId(int doctorId);

    List<Availability> getAvailabilityByDate(LocalDate date);

    Availability getAvailabilityByDoctorIdAndDate(int doctorId, LocalDate date);
}
