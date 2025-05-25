package com.techelevator.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.techelevator.dao.ScheduledAppointmentsDao;
import com.techelevator.model.ScheduledAppointments;

@Service
public class ScheduledAppointmentsService {
    private final ScheduledAppointmentsDao scheduledAppointmentsDao;

    public ScheduledAppointmentsService(ScheduledAppointmentsDao scheduledAppointmentsDao) {
        this.scheduledAppointmentsDao = scheduledAppointmentsDao;
    }

    public List<ScheduledAppointments> getAllScheduledAppointments() {
        return scheduledAppointmentsDao.getAllScheduledAppointments();
    }

    public List<ScheduledAppointments> getScheduledAppointmentsByPatientId(int patientId) {
        return scheduledAppointmentsDao.getScheduledAppointmentsByPatientId(patientId);
    }

    public List<ScheduledAppointments> getScheduledAppointmentsByDoctorId(int npiNumber) {
        return scheduledAppointmentsDao.getScheduledAppointmentsByDoctorId(npiNumber);
    }

    public List<ScheduledAppointments> getScheduledAppointmentsByDate(LocalDate date) {
        return scheduledAppointmentsDao.getScheduledAppointmentsByDate(date);
    }

    public boolean addScheduledAppointment(ScheduledAppointments appointment) {
        return scheduledAppointmentsDao.addScheduledAppointment(appointment);
    }

    public boolean updateScheduledAppointment(ScheduledAppointments appointment) {
        return scheduledAppointmentsDao.updateScheduledAppointment(appointment);
    }

    public boolean deleteScheduledAppointment(int appointmentId) {
        return scheduledAppointmentsDao.deleteScheduledAppointment(appointmentId);
    }
}
