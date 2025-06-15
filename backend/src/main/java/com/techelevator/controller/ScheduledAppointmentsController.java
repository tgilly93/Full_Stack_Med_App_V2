package com.techelevator.controller;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.techelevator.model.ScheduledAppointments;
import com.techelevator.service.ScheduledAppointmentsService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/scheduled-appointments")
@CrossOrigin
public class ScheduledAppointmentsController {
    private final ScheduledAppointmentsService scheduledAppointmentsService;

    public ScheduledAppointmentsController(ScheduledAppointmentsService scheduledAppointmentsService) {
        this.scheduledAppointmentsService = scheduledAppointmentsService;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_RECEPTIONIST')") 
    @GetMapping
    public List<ScheduledAppointments> getAllScheduledAppointments() {
        return scheduledAppointmentsService.getAllScheduledAppointments();
    }

    @PreAuthorize("@securityService.isPatientOwnedByUser(#patientId, authentication.principal.userId) or hasAnyRole ('ROLE_ADMIN', 'ROLE_RECEPTIONIST')")
    @GetMapping("/patient/{patientId}")
    public List<ScheduledAppointments> getScheduledAppointmentsByPatientId(@PathVariable int patientId) {
        return scheduledAppointmentsService.getScheduledAppointmentsByPatientId(patientId);
    }

    @PreAuthorize("@securityService.isClinicianOwnedByUser(#npiNumber, authentication.principal.userId) or hasAnyRole('ROLE_ADMIN', 'ROLE_RECEPTIONIST')")
    @GetMapping("/doctor/{npiNumber}")
    public List<ScheduledAppointments> getScheduledAppointmentsByDoctorId(@PathVariable int npiNumber) {
        return scheduledAppointmentsService.getScheduledAppointmentsByDoctorId(npiNumber);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_RECEPTIONIST')")
    @GetMapping("/date/{date}")
    public List<ScheduledAppointments> getScheduledAppointmentsByDate(@PathVariable String date) {
        LocalDate localDate = LocalDate.parse(date);
        return scheduledAppointmentsService.getScheduledAppointmentsByDate(localDate);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_RECEPTIONIST', 'ROLE_CLINICIAN', 'ROLE_PATIENT')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addScheduledAppointment(@RequestBody ScheduledAppointments appointment) {
        boolean success = scheduledAppointmentsService.addScheduledAppointment(appointment);
        if (!success) {
            throw new RuntimeException("Failed to add scheduled appointment.");
        }
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_RECEPTIONIST')")
    @PutMapping
    public void updateScheduledAppointment(@RequestBody ScheduledAppointments appointment) {
        boolean success = scheduledAppointmentsService.updateScheduledAppointment(appointment);
        if (!success) {
            throw new RuntimeException("Failed to update scheduled appointment.");
        }
    }

    @PreAuthorize("@securityService.isPatientOwnedByUser(#appointmentId, authentication.principal.userId) or hasAnyRole('ROLE_ADMIN', 'ROLE_RECEPTIONIST')")
    @DeleteMapping("/{appointmentId}")
    public void deleteScheduledAppointment(@PathVariable int appointmentId) {
        boolean success = scheduledAppointmentsService.deleteScheduledAppointment(appointmentId);
        if (!success) {
            throw new RuntimeException("Failed to delete scheduled appointment.");
        }
    }
}
