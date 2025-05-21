package com.techelevator.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.techelevator.dao.AppointmentDao;
import com.techelevator.model.Appointment;

@Service
public class AppointmentService {
    private final AppointmentDao appointmentDao;

    public AppointmentService(AppointmentDao appointmentDao) {
        this.appointmentDao = appointmentDao;
    }

    public List<Appointment> getAllAppointments() {
        return appointmentDao.getAllAppointments();
    }

    public List<Appointment> getAppointmentsByClinicianIdAndDate(int npiNumber, LocalDate date) {
        return appointmentDao.getAppointmentsByClinicianIdAndDate(npiNumber, date);
    }

    public Appointment addAppointment(Appointment appointment) {
        return appointmentDao.addAppointment(appointment);
    }

    public boolean updateAppointment(Appointment appointment) {
        return appointmentDao.updateAppointment(appointment);
    }

    public boolean deleteAppointment(int appointmentId) {
        return appointmentDao.deleteAppointment(appointmentId);
    }

    public List<Appointment> getAppointmentsByPatientId(int patientId){
        return appointmentDao.getAppointmentsByPatientId(patientId);
    }

    public List<Appointment> getAppointmentsByClinicianId(int npiNumber) {
        return appointmentDao.getAppointmentsByClinicianId(npiNumber);
    }
}
