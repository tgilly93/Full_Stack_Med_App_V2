package com.techelevator.controller;

import com.techelevator.dao.AvailabilityDao;
import com.techelevator.model.Availability;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/availability")
public class AvailabilityController {
    private final AvailabilityDao availabilityDao;

    @Autowired
    public AvailabilityController(AvailabilityDao availabilityDao) {
        this.availabilityDao = availabilityDao;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addAvailability(@RequestBody Availability availability) {
        boolean result = availabilityDao.addAvailability(availability);
        if (!result) {
            throw new RuntimeException("Failed to create availability.");
        }
    }

    @PutMapping("/{availabilityId}")
    public void updateAvailability(@PathVariable int availabilityId, @RequestBody Availability availability) {
        availability.setAvailabilityId(availabilityId);
        boolean result = availabilityDao.updateAvailability(availability);
        if (!result) {
            throw new RuntimeException("Failed to update availability.");
        }
    }

    @DeleteMapping("/{availabilityId}")
    public void deleteAvailability(@PathVariable int availabilityId) {
        boolean result = availabilityDao.deleteAvailability(availabilityId);
        if (!result) {
            throw new RuntimeException("Failed to delete availability.");
        }
    }

    @GetMapping("/doctor/{doctorId}")
    public List<Availability> getAvailabilityByDoctorId(@PathVariable int doctorId) {
        return availabilityDao.getAvailabilityByDoctorId(doctorId);
    }

    @GetMapping("/date")
    public List<Availability> getAvailabilityByDate(@RequestParam LocalDate date) {
        return availabilityDao.getAvailabilityByDate(date);
    }

    @GetMapping("/doctor/{doctorId}/date")
    public Availability getAvailabilityByDoctorIdAndDate(@PathVariable int doctorId, @RequestParam LocalDate date) {
        return availabilityDao.getAvailabilityByDoctorIdAndDate(doctorId, date);
    }
}
