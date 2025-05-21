package com.techelevator.controller;

import com.techelevator.model.Appointment;
import com.techelevator.service.AppointmentService;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping
    public List<Appointment> getAllAppointments() {
        return appointmentService.getAllAppointments();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Appointment addAppointment(@RequestBody Appointment appointment) {
        return appointmentService.addAppointment(appointment);
    }

    @PutMapping("/{appointmentId}")
    public boolean updateAppointment(@PathVariable int appointmentId, @RequestBody Appointment appointment) {
        appointment.setAppointmentId(appointmentId);

        return appointmentService.updateAppointment(appointment);
    }

    @DeleteMapping("/{appointmentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean deleteAppointment(@PathVariable int appointmentId) {
        return appointmentService.deleteAppointment(appointmentId);
    }

    @GetMapping("/patient/{patientId}")
    public List<Appointment> getAppointmentsByPatientId(@PathVariable int patientId) {
        return appointmentService.getAppointmentsByPatientId(patientId);
    }

    @GetMapping("/doctor/{npiNumber}/date/{date}")
    public List<Appointment> getAppointmentsByClinicianIdAndDate(@PathVariable int npiNumber, @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return appointmentService.getAppointmentsByClinicianIdAndDate(npiNumber, date);
    }

    @GetMapping("/doctor/{npiNumber}")
    public List<Appointment> getAppointmentsByClinicianId(@PathVariable int npiNumber) {
        return appointmentService.getAppointmentsByClinicianId(npiNumber);
    }
}
