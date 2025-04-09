package com.techelevator.controller;

import com.techelevator.dao.AppointmentDao;
import com.techelevator.model.Appointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/appointments")
public class AppointmentController {

    private final AppointmentDao appointmentDao;

    @Autowired
    public AppointmentController(AppointmentDao appointmentDao) {
        this.appointmentDao = appointmentDao;
    }

    @GetMapping("")
    public List<Appointment> getAllAppointments() {
        return appointmentDao.getAllAppointments();
    }

    @GetMapping("/available/{clinicianId}/date/{date}")
    public List<Appointment> getAvailableAppointmentsByClinicianIdAndDate(@PathVariable int clinicianId, @PathVariable Date date) {
        return appointmentDao.getAvailableAppointmentsByClinicianIdAndDate(clinicianId, date);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addAppointment(@RequestBody Appointment appointment) {
        boolean success = appointmentDao.addAppointment(appointment);
        if (!success) {
            throw new RuntimeException("Failed to create appointment.");
        }
    }

    @PutMapping
    public void updateAppointment(@RequestBody Appointment appointment) {
        boolean success = appointmentDao.updateAppointment(appointment);
        if (!success) {
            throw new RuntimeException("Failed to update appointment.");
        }
    }

    @DeleteMapping("/{appointmentId}")
    public void deleteAppointment(@PathVariable int appointmentId) {
        boolean success = appointmentDao.deleteAppointment(appointmentId);
        if (!success) {
            throw new RuntimeException("Failed to delete appointment.");
        }
    }

//    @GetMapping("/patient/{patientId}")
//    public List<Appointment> getAppointmentsByPatientId(@PathVariable int patientId) {
//        return appointmentDao.getAppointmentsByPatientId(patientId);
//    }
//
//    @GetMapping("/doctor/{clinicianId}/date/{date}")
//    public List<Appointment> getAppointmentsByClinicianIdAndDate(@PathVariable int clinicianId, @PathVariable Date date) {
//        return appointmentDao.getAppointmentsByClinicianIdAndDate(clinicianId, date);
//    }
//
//    @GetMapping("/doctor/{clinicianId}")
//    public List<Appointment> getAppointmentsByClinicianId(@PathVariable int clinicianId) {
//        return appointmentDao.getAppointmentsByClinicianId(clinicianId);
//    }
}
