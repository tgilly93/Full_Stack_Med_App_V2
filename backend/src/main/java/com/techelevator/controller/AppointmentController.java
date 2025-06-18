package com.techelevator.controller;

import com.techelevator.model.Appointment;
import com.techelevator.service.AppointmentService;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize("hasAnyRole ('ROLE_ADMIN', 'ROLE_RECEPTIONIST')")
    @GetMapping
    public List<Appointment> getAllAppointments() {
        return appointmentService.getAllAppointments();
    }

    @PreAuthorize("hasAnyRole ('ROLE_ADMIN', 'ROLE_RECEPTIONIST', 'ROLE_PATIENT', 'ROLE_CLINICIAN')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Appointment addAppointment(@RequestBody Appointment appointment) {
        return appointmentService.addAppointment(appointment);
    }

    @PreAuthorize("@securityService.isPatientOwnedByUser(#appointment.patientId, authentication.principal.userId) or securityService.isClinicianOwnedByUser(#appointment.npiNumber, authentication.principal.userId) or hasAnyRole ('ROLE_ADMIN', 'ROLE_RECEPTIONIST')")
    @PutMapping("/{appointmentId}")
    public boolean updateAppointment(@PathVariable int appointmentId, @RequestBody Appointment appointment) {
        appointment.setAppointmentId(appointmentId);

        return appointmentService.updateAppointment(appointment);
    }

    @PreAuthorize("@securityService.isPatientOwnedByUser(#appointment.patientId, authentication.principal.userId) or securityService.isClinicianOwnedByUser(#appointment.npiNumber, authentication.principal.userId) or hasAnyRole ('ROLE_ADMIN', 'ROLE_RECEPTIONIST')")
    @DeleteMapping("/{appointmentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean deleteAppointment(@PathVariable int appointmentId) {
        return appointmentService.deleteAppointment(appointmentId);
    }

    @PreAuthorize("@securityService.isPatientOwnedByUser(#patientId, authentication.principal.userId) or hasAnyRole ('ROLE_ADMIN', 'ROLE_RECEPTIONIST')")
    @GetMapping("/patient/{patientId}")
    public List<Appointment> getAppointmentsByPatientId(@PathVariable int patientId) {
        return appointmentService.getAppointmentsByPatientId(patientId);
    }

    @PreAuthorize("@securityService.isClinicianOwnedByUser(#npiNumber, authentication.principal.userId) or hasAnyRole ('ROLE_ADMIN', 'ROLE_RECEPTIONIST')")
    @GetMapping("/doctor/{npiNumber}/date/{date}")
    public List<Appointment> getAppointmentsByClinicianIdAndDate(@PathVariable int npiNumber, @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return appointmentService.getAppointmentsByClinicianIdAndDate(npiNumber, date);
    }

    @PreAuthorize("@securityService.isClinicianOwnedByUser(#npiNumber, authentication.principal.userId) or hasAnyRole ('ROLE_ADMIN', 'ROLE_RECEPTIONIST')")
    @GetMapping("/doctor/{npiNumber}")
    public List<Appointment> getAppointmentsByClinicianId(@PathVariable int npiNumber) {
        return appointmentService.getAppointmentsByClinicianId(npiNumber);
    }
}
