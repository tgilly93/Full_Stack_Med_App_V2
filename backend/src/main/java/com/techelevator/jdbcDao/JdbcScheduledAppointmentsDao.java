package com.techelevator.jdbcDao;

import java.time.LocalDate;
import java.util.List;

import com.techelevator.dao.ScheduledAppointmentsDao;
import com.techelevator.model.ScheduledAppointments;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class JdbcScheduledAppointmentsDao implements ScheduledAppointmentsDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcScheduledAppointmentsDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<ScheduledAppointments> scheduledAppointmentsRowMapper = (rs, rowNum) -> {
        ScheduledAppointments scheduledAppointments = new ScheduledAppointments();
        scheduledAppointments.setAppointmentId(rs.getInt("appointment_id"));
        scheduledAppointments.setDate(rs.getDate("Date").toLocalDate());
        scheduledAppointments.setDayOfWeek(rs.getString("Day_of_Week"));
        scheduledAppointments.setDoctor(rs.getString("Doctor"));
        scheduledAppointments.setNpiNumber(rs.getInt("NPI"));
        scheduledAppointments.setPatientId(rs.getInt("Patient"));
        scheduledAppointments.setPatientName(rs.getString("Patient Name"));
        scheduledAppointments.setTimeBlock(rs.getInt("Time Block"));
        scheduledAppointments.setStartTime(rs.getTime("start_time").toLocalTime());
        scheduledAppointments.setEndTime(rs.getTime("end_time").toLocalTime());
        scheduledAppointments.setType(rs.getString("Type"));
        scheduledAppointments.setStatus(rs.getString("Status"));
        scheduledAppointments.setAppointmentId(rs.getInt("appointment_id"));

        return scheduledAppointments;
    };

    @Override
    public List<ScheduledAppointments> getAllScheduledAppointments() {
        String sql = "SELECT * FROM scheduled_appointments";
        return jdbcTemplate.query(sql, scheduledAppointmentsRowMapper);
    }

    @Override
    public List<ScheduledAppointments> getScheduledAppointmentsByPatientId(int patientId) {
        String sql = "SELECT * FROM scheduled_appointments WHERE \"Patient\" = ?";
        return jdbcTemplate.query(sql, scheduledAppointmentsRowMapper, patientId);
    }

    @Override
    public List<ScheduledAppointments> getScheduledAppointmentsByDoctorId(int npiNumber) {
        String sql = "SELECT * FROM scheduled_appointments WHERE \"NPI\" = ?";
        return jdbcTemplate.query(sql, scheduledAppointmentsRowMapper, npiNumber);
    }

    @Override
    public List<ScheduledAppointments> getScheduledAppointmentsByDate(LocalDate date) {
        String sql = "SELECT * FROM scheduled_appointments WHERE \"Date\" = ?";
        return jdbcTemplate.query(sql, scheduledAppointmentsRowMapper, date);
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
}
