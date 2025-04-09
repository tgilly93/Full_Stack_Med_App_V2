package com.techelevator.dao;

import com.techelevator.model.Appointment;

import java.sql.Date;
import java.util.List;

public interface AppointmentDao {
    List<Appointment> getAllAppointments ();
    List<Appointment> getAvailableAppointmentsByClinicianIdAndDate(int clinicianId, Date date);
    boolean addAppointment(Appointment appointment);
    boolean updateAppointment(Appointment appointment);
    boolean deleteAppointment(int appointmentId);

}
