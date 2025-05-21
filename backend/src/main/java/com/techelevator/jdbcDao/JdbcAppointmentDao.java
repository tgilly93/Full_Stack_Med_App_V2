package com.techelevator.jdbcDao;

import com.techelevator.dao.AppointmentDao;
import com.techelevator.model.Appointment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.util.List;

@Component
public class JdbcAppointmentDao implements AppointmentDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcAppointmentDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Appointment> appointmentRowMapper = (rs, rowNum) -> {
        Appointment appointment = new Appointment();
        appointment.setAppointmentId(rs.getInt("appointment_id"));
        appointment.setNpiNumber(rs.getInt("npi_number"));
        appointment.setPatientId(rs.getInt("patient_id"));
        appointment.setDate(rs.getDate("date").toLocalDate());
        appointment.setStartTime(rs.getTime("start_time").toLocalTime());
        appointment.setEndTime(rs.getTime("end_time").toLocalTime());
        appointment.setAppointmentType(rs.getString("appointment_type"));
        appointment.setAppointmentStatus(rs.getString("appointment_status"));
        return appointment;
    };

    @Override
    public List<Appointment> getAllAppointments() {
        String sql = "SELECT * FROM Appointment ORDER BY start_time";
        
        return jdbcTemplate.query(sql, appointmentRowMapper);
    }

    @Override
    public List<Appointment> getAppointmentsByClinicianIdAndDate(int npiNumber, LocalDate date) {
        String sql = "SELECT * FROM Appointment WHERE npi_number = ? AND date = ? ";

       return jdbcTemplate.query(sql, appointmentRowMapper, npiNumber, date);
    }

    @Override
    public Appointment addAppointment(Appointment appointment) {
        String sql = "INSERT INTO Appointment (npi_number, patient_id, date, start_time, end_time, appointment_type, appointment_status)"
                + "VALUES (?, ?, ?, ?, ?, ?, ?) RETURNING appointment_id";

        int newId = jdbcTemplate.queryForObject(sql, Integer.class,
                appointment.getNpiNumber(),
                appointment.getPatientId(),
                appointment.getDate(),
                appointment.getStartTime(),
                appointment.getEndTime(),
                appointment.getAppointmentType(),
                appointment.getAppointmentStatus());

        appointment.setAppointmentId(newId);

        return appointment;
    }

    @Override
    public boolean updateAppointment(Appointment appointment) {
        String sql = "UPDATE Appointment SET npi_number = ?, patient_id = ?, date = ?, start_time = ?, end_time = ?, appointment_type = ?, appointment_status = ? WHERE appointment_id = ?";

        int rowsAffected = jdbcTemplate.update(sql,
                appointment.getNpiNumber(),
                appointment.getPatientId(),
                appointment.getDate(),
                appointment.getStartTime(),
                appointment.getEndTime(),
                appointment.getAppointmentType(),
                appointment.getAppointmentStatus(),
                appointment.getAppointmentId());

        return rowsAffected > 0;
    }
        

    @Override
    public boolean deleteAppointment(int appointmentId) {
        String sql = "DELETE FROM appointment WHERE appointment_id = ?";

        int rowsAffected = jdbcTemplate.update(sql, appointmentId);

        return rowsAffected > 0;
    }

    @Override
    public List<Appointment> getAppointmentsByPatientId(int patientId) {
        String sql = "SELECT * FROM appointment WHERE patient_id = ?";

        return jdbcTemplate.query(sql, appointmentRowMapper, patientId);
    }

    @Override
    public List<Appointment> getAppointmentsByClinicianId(int npiNumber) {
        String sql = "SELECT * FROM appointment WHERE npi_number = ?";

        return jdbcTemplate.query(sql, appointmentRowMapper, npiNumber);
    }
}
