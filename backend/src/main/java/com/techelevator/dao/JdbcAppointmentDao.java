package com.techelevator.dao;

import com.techelevator.model.Appointment;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.time.format.TextStyle;
import java.util.Locale;

@Component
public class JdbcAppointmentDao implements AppointmentDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcAppointmentDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Appointment> getAllAppointments() {
        String sql = "SELECT * FROM appointment ORDER BY start_time";

        List<Appointment> availableAppointments = new ArrayList<>();
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            availableAppointments.add(mapRowToAppointment(results));
        }
        return availableAppointments;
    }

    @Override
    public List<Appointment> getAppointmentsByClinicianIdAndDate(int npiNumber, Date date) {
        String sql = "SELECT * FROM appointment WHERE npi_number = ? AND date = ? ";

        String dayOfWeek = date.toLocalDate().getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ENGLISH);

        List<Appointment> availableAppointments = new ArrayList<>();
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, npiNumber, dayOfWeek);
        while (results.next()) {
            availableAppointments.add(mapRowToAppointment(results));
        }
        return availableAppointments;
    }

    @Override
    public boolean addAppointment(Appointment appointment) {
        String sql = "INSERT INTO appointment(npi_number, patient_id, date, start_time, end_time, appointment_type, appointment_status)" + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        int rowsAffected = jdbcTemplate.update(sql,
                appointment.getNpiNumber(),
                appointment.getPatientId(),
                appointment.getDate(),
                appointment.getStartTime(),
                appointment.getEndTime(),
                appointment.getAppointmentType(),
                appointment.getAppointmentStatus());

        return rowsAffected > 0;
    }

    @Override
    public boolean updateAppointment(Appointment appointment) {
        String sql = "UPDATE scheduled_appointments SET type = ?, status = ? WHERE \"Date\" = ? AND start_time = ? AND end_time = ? AND \"Doctor\" = ? AND \"Patient\" = ?";

        return jdbcTemplate.update(sql,
                appointment.getAppointmentType(), appointment.getAppointmentStatus(), appointment.getDate(),
                appointment.getStartTime(), appointment.getEndTime(),
                getDoctorFullName(appointment.getNpiNumber()),
                getPatientFullName(appointment.getPatientId())) == 1;
    }

    @Override
    public boolean deleteAppointment(int appointmentId) {
        String sql = "DELETE FROM appointment WHERE appointment_id = ?";
        int rowsAffected = jdbcTemplate.update(sql, appointmentId);

        return rowsAffected > 0;
    }

    @Override
    public List<Appointment> getAppointmentsByPatientId(int patientId) {
        String sql = "SELECT * FROM scheduled_appointments WHERE \"Patient\" = ?";

        List<Appointment> appointments = new ArrayList<>();
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, patientId);
        while (results.next()) {
            appointments.add(mapRowToAppointment(results));
        }
        return appointments;
    }

    @Override
    public List<Appointment> getAppointmentsByClinicianId(int npiNumber) {
        String sql = "SELECT * FROM scheduled_appointments WHERE \"Doctor\" = ?";

        List<Appointment> appointments = new ArrayList<>();
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, npiNumber);
        while (results.next()) {
            appointments.add(mapRowToAppointment(results));
        }
        return appointments;
    }

    private String getDoctorFullName(int npiNumber) {
        String sql = "SELECT s.staff_last_name || ', ' || s.staff_first_name AS doctor_name " +
                "FROM clinician c " +
                "JOIN staff s ON c.staff_id = s.staff_id " +
                "WHERE c.npi_number = ?";
        try {
            return jdbcTemplate.queryForObject(sql, String.class, npiNumber);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    private String getPatientFullName(int patientId) {
        String sql = "SELECT p.patient_first_name || ' ' || p.patient_last_name AS patient_name " +
                "FROM patient p " +
                "WHERE p.patient_id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, String.class, patientId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    private Appointment mapRowToAppointment(SqlRowSet rs) {
        Appointment appointment = new Appointment();
        appointment.setAppointmentId(rs.getInt("appointment_id"));
        appointment.setNpiNumber(rs.getInt("npi_number"));
        appointment.setPatientId(rs.getInt("patient_id"));
        appointment.setDate(rs.getDate("date"));
        appointment.setStartTime(rs.getTime("start_time"));
        appointment.setEndTime(rs.getTime("end_time"));
        appointment.setAppointmentType(rs.getString("appointment_type"));
        appointment.setAppointmentStatus(rs.getString("appointment_status"));
        return appointment;
    }
}
