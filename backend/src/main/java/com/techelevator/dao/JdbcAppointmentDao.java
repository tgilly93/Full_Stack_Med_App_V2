package com.techelevator.dao;

import com.techelevator.model.Appointment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcAppointmentDao implements AppointmentDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcAppointmentDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Appointment> getAllAppointments (){
        String sql = "SELECT * FROM clinician_availabilty ORDER BY start_time";

        List<Appointment> availableAppointments = new ArrayList<>();
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            availableAppointments.add(mapRowToAvailableAppointment(results));
        }
        return availableAppointments;
    }

    @Override
    public List<Appointment> getAvailableAppointmentsByClinicianIdAndDate(int npiNumber, Date date) {
        String sql = "SELECT * FROM clinician_availabilty WHERE npi_number = ? AND date = ? AND is_available = true ORDER BY start_time";

        List<Appointment> availableAppointments = new ArrayList<>();
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, npiNumber, date);
        while (results.next()) {
            availableAppointments.add(mapRowToAvailableAppointment(results));
        }
        return availableAppointments;
    }

    @Override
    public boolean addAppointment(Appointment appointment) {
        String sql = "INSERT INTO scheduled_appointments(\"Date\", start_time, end_time, type, status, \"Doctor\", \"Patient\") VALUES (?, ?, ?, ?, ?, ?, ?)";

        return jdbcTemplate.update(sql,
                appointment.getDate(),appointment.getStartTime(),appointment.getEndTime(),
                appointment.getAppointmentType(),appointment.getAppointmentStatus(),appointment.getNpiNumber(),
                appointment.getPatientId()) == 1;


    }

    @Override
    public boolean updateAppointment(Appointment appointment) {
        String sql = "UPDATE scheduled_appointments SET type = ?, status = ? WHERE \"Date\" = ? AND start_time = ? AND end_time = ? AND \"Doctor\" = ? AND \"Patient\" = ?";

        return jdbcTemplate.update(sql,
                appointment.getAppointmentType(),appointment.getAppointmentStatus(),appointment.getDate(),appointment.getStartTime(),appointment.getEndTime(),
                getDoctorFullName(appointment.getNpiNumber()),
                getPatientFullName(appointment.getPatientId())) == 1;

    }

    @Override
    public boolean deleteAppointment(int npiNumber) {
        String sql = "DELETE FROM scheduled_appointments WHERE npi_number = ?";

        return jdbcTemplate.update(sql, npiNumber) > 0;
    }

    private String getDoctorFullName(int npiNumber) {
        String sql = "SELECT s.staff_last_name || ', ' || s.staff_first_name AS doctor_name " +
                "FROM clinician c " +
                "JOIN staff s ON c.staff_id = s.staff_id " +
                "WHERE c.npi_number = ?";
        return jdbcTemplate.queryForObject(sql, String.class, npiNumber);
    }

    private String getPatientFullName(int patientId) {
        String sql = "SELECT p.patient_first_name || ' ' || p.patient_last_name AS patient_name " +
                "FROM patient p " +
                "WHERE p.patient_id = ?";
        return jdbcTemplate.queryForObject(sql, String.class, patientId);
    }

    private Appointment mapRowToAvailableAppointment(SqlRowSet rs) {
        Appointment appointment = new Appointment();
        appointment.setNpiNumber(rs.getInt("npi_number"));
        appointment.setDate(rs.getDate("date"));
        appointment.setStartTime(rs.getTime("start_time"));
        appointment.setEndTime(rs.getTime("end_time"));
        appointment.setAppointmentType("Available Slot");
        appointment.setAppointmentStatus("Available");
        return appointment;
    }


}
