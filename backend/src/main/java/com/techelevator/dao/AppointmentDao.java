package com.techelevator.dao;

import com.techelevator.model.Appointment;
import java.time.LocalDate;
import java.util.List;

public interface AppointmentDao {
    List<Appointment> getAllAppointments();

    Appointment addAppointment(Appointment appointment);

    boolean updateAppointment(Appointment appointment);

    boolean deleteAppointment(int appointmentId);

    List<Appointment> getAppointmentsByPatientId(int patientId);

    List<Appointment> getAppointmentsByClinicianIdAndDate(int npiNumber, LocalDate date);

    List<Appointment> getAppointmentsByClinicianId(int npiNumber);

}
