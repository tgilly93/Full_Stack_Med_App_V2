package com.techelevator.jdbcDao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.techelevator.dao.ScheduledAppointmentsDao;
import com.techelevator.model.ScheduledAppointments;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JdbcScheduledAppointmentsDao implements ScheduledAppointmentsDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcScheduledAppointmentsDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<ScheduledAppointments> getAllScheduledAppointments() {
        List<ScheduledAppointments> appointments = new ArrayList<>();
        String sql = "SELECT * FROM scheduled_appointments";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            appointments.add(mapRowToScheduledAppointments(results));
        }
        return appointments;
    }

    @Override
    public List<ScheduledAppointments> getScheduledAppointmentsByPatientId(int patientId) {
        List<ScheduledAppointments> appointments = new ArrayList<>();
        String sql = "SELECT * FROM scheduled_appointments WHERE \"Patient\" = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, patientId);
        while (results.next()) {
            appointments.add(mapRowToScheduledAppointments(results));
        }
        return appointments;
    }

    @Override
    public List<ScheduledAppointments> getScheduledAppointmentsByDoctorId(int npiNumber) {
        List<ScheduledAppointments> appointments = new ArrayList<>();
        String sql = "SELECT * FROM scheduled_appointments WHERE \"NPI\" = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, npiNumber);
        while (results.next()) {
            appointments.add(mapRowToScheduledAppointments(results));
        }
        return appointments;
    }

    @Override
    public List<ScheduledAppointments> getScheduledAppointmentsByDate(LocalDate date) {
        List<ScheduledAppointments> appointments = new ArrayList<>();
        String sql = "SELECT * FROM scheduled_appointments WHERE \"Date\" = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, date);
        while (results.next()) {
            appointments.add(mapRowToScheduledAppointments(results));
        }
        return appointments;
    }

    @Override
    public boolean addScheduledAppointment(ScheduledAppointments appointment) {
        String sql = "INSERT INTO scheduled_appointments (\"Date\", \"start_time\", \"end_time\", \"Type\", \"Status\", \"Patient\", \"Doctor\") "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        int rowsAffected = jdbcTemplate.update(sql,
                appointment.getDate(),
                appointment.getStartTime(),
                appointment.getEndTime(),
                appointment.getType(),
                appointment.getStatus(),
                appointment.getPatientId(),
                appointment.getDoctor());

        return rowsAffected > 0;
    }

    @Override
    public boolean updateScheduledAppointment(ScheduledAppointments appointment) {
        String sql = "UPDATE scheduled_appointments SET " +
                "\"Date\" = ?, \"start_time\" = ?, \"end_time\" = ?, " +
                "\"Type\" = ?, \"Status\" = ? " +
                "WHERE appointment_id = ?";

        int rowsAffected = jdbcTemplate.update(sql,
                appointment.getDate(),
                appointment.getStartTime(),
                appointment.getEndTime(),
                appointment.getType(),
                appointment.getStatus(),
                appointment.getAppointmentId());
        return rowsAffected > 0;
    }

    @Override
    public boolean deleteScheduledAppointment(int appointmentId) {
        String sql = "DELETE FROM scheduled_appointments WHERE appointment_id = ?";
        int rowsAffected = jdbcTemplate.update(sql,
                appointmentId);
        return rowsAffected > 0;
    }

    private ScheduledAppointments mapRowToScheduledAppointments(SqlRowSet rs) {
        ScheduledAppointments appointments = new ScheduledAppointments();
        appointments.setDate(rs.getDate("Date").toLocalDate());
        appointments.setDayOfWeek(rs.getString("Day_of_Week"));
        appointments.setDoctor(rs.getString("Doctor"));
        appointments.setNpiNumber(rs.getInt("NPI"));
        appointments.setPatientId(rs.getInt("Patient"));
        appointments.setPatientName(rs.getString("Patient Name"));
        appointments.setTimeBlock(rs.getInt("Time Block"));
        appointments.setStartTime(rs.getTime("start_time").toLocalTime());
        appointments.setEndTime(rs.getTime("end_time").toLocalTime());
        appointments.setType(rs.getString("Type"));
        appointments.setStatus(rs.getString("Status"));
        appointments.setAppointmentId(rs.getInt("appointment_id"));
        return appointments;
    }
}
