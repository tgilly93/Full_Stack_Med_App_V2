package com.techelevator.controller;

import com.techelevator.model.Availability;
import com.techelevator.service.AvailabilityService;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/availability")
public class AvailabilityController {
    private final AvailabilityService availabilityService;

    public AvailabilityController(AvailabilityService availabilityService) {
        this.availabilityService = availabilityService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Availability addAvailability(@RequestBody Availability availability) {
        return availabilityService.addAvailability(availability);
    }

    @PutMapping("/{availabilityId}")
    public boolean updateAvailability(@PathVariable int availabilityId, @RequestBody Availability availability) {
        availability.setAvailabilityId(availabilityId);
       
        return availabilityService.updateAvailability(availability);
    }

    @DeleteMapping("/{availabilityId}")
    public boolean deleteAvailability(@PathVariable int availabilityId) {
        return availabilityService.deleteAvailability(availabilityId);
    }

    @GetMapping("/doctor/{npiNumber}")
    public List<Availability> getAvailabilityByDoctorId(@PathVariable int npiNumber) {
        return availabilityService.getAvailabilityByDoctorId(npiNumber);
    }

    @GetMapping("/date/{date}")
    public List<Availability> getAvailabilityByDate(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return availabilityService.getAvailabilityByDate(date);
    }

    @GetMapping("/doctor/{npiNumber}/date/{date}")
    public List<Availability> getAvailabilityByDoctorIdAndDate(@PathVariable int npiNumber, @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return availabilityService.getAvailabilityByDoctorIdAndDate(npiNumber, date);
    }
}
