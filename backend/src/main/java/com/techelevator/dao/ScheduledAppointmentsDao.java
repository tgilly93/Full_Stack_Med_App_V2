package com.techelevator.dao;

import java.time.LocalDate;
import java.util.List;

import com.techelevator.model.ScheduledAppointments;

public interface ScheduledAppointmentsDao {

    List<ScheduledAppointments> getAllScheduledAppointments();

    List<ScheduledAppointments> getScheduledAppointmentsByPatientId(int patientId);

    List<ScheduledAppointments> getScheduledAppointmentsByDoctorId(int npiNumber);

    List<ScheduledAppointments> getScheduledAppointmentsByDate(LocalDate date);

    boolean addScheduledAppointment(ScheduledAppointments appointment);

    boolean updateScheduledAppointment(ScheduledAppointments appointment);

    boolean deleteScheduledAppointment(int appointmentId);
}
