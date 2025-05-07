package com.techelevator.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.techelevator.dao.ScheduledAppointmentsDao;
import com.techelevator.model.ScheduledAppointments;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/scheduled-appointments")
@CrossOrigin
public class ScheduledAppointmentsController {
    private final ScheduledAppointmentsDao scheduledAppointmentsDao;

    public ScheduledAppointmentsController(ScheduledAppointmentsDao scheduledAppointmentsDao) {
        this.scheduledAppointmentsDao = scheduledAppointmentsDao;
    }

    @GetMapping("")
    public List<ScheduledAppointments> getAllScheduledAppointments() {
        return scheduledAppointmentsDao.getAllScheduledAppointments();
    }

    @GetMapping("/patient/{patientId}")
    public List<ScheduledAppointments> getScheduledAppointmentsByPatientId(@PathVariable int patientId) {
        return scheduledAppointmentsDao.getScheduledAppointmentsByPatientId(patientId);
    }

    @GetMapping("/doctor/{npiNumber}")
    public List<ScheduledAppointments> getScheduledAppointmentsByDoctorId(@PathVariable int npiNumber) {
        return scheduledAppointmentsDao.getScheduledAppointmentsByDoctorId(npiNumber);
    }

    @GetMapping("/date/{date}")
    public List<ScheduledAppointments> getScheduledAppointmentsByDate(@PathVariable String date) {
        LocalDate localDate = LocalDate.parse(date);
        return scheduledAppointmentsDao.getScheduledAppointmentsByDate(localDate);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addScheduledAppointment(@RequestBody ScheduledAppointments appointment) {
        boolean success = scheduledAppointmentsDao.addScheduledAppointment(appointment);
        if (!success) {
            throw new RuntimeException("Failed to add scheduled appointment.");
        }
    }

    @PutMapping
    public void updateScheduledAppointment(@RequestBody ScheduledAppointments appointment) {
        boolean success = scheduledAppointmentsDao.updateScheduledAppointment(appointment);
        if (!success) {
            throw new RuntimeException("Failed to update scheduled appointment.");
        }
    }

    @DeleteMapping
    public void deleteScheduledAppointment(@RequestBody ScheduledAppointments appointment) {
        boolean success = scheduledAppointmentsDao.deleteScheduledAppointment(appointment);
        if (!success) {
            throw new RuntimeException("Failed to delete scheduled appointment.");
        }
    }
}
