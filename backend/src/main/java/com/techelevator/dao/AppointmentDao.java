package com.techelevator.dao;

import com.techelevator.model.Appointment;
import java.sql.Date;
import java.util.List;

public interface AppointmentDao {
    List<Appointment> getAllAppointments();

    boolean addAppointment(Appointment appointment);

    boolean updateAppointment(Appointment appointment);

    boolean deleteAppointment(int appointmentId);

    List<Appointment> getAppointmentsByPatientId(int patientId);

    List<Appointment> getAppointmentsByClinicianIdAndDate(int npiNumber, Date date);

    List<Appointment> getAppointmentsByClinicianId(int npiNumber);

}
