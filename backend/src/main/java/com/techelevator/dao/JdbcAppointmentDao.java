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
        String sql = "SELECT a.npi_number, a.office_id, a.date, a.start_time, a.end_time, a.is_available " +
                "FROM availability a " +
                "LEFT JOIN appointment ap ON a.npi_number = ap.npi_number AND a.date = ap.date AND a.start_time = ap.start_time " +
                "ORDER BY a.start_time";

        List<Appointment> availableAppointments = new ArrayList<>();
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            availableAppointments.add(mapRowToAvailableAppointment(results));
        }
        return availableAppointments;
    }

    @Override
    public List<Appointment> getAvailableAppointmentsByClinicianIdAndDate(int clinicianId, Date date) {
        String sql = "SELECT a.npi_number, a.office_id, a.date, a.start_time, a.end_time, a.is_available " +
                "FROM availability a " +
                "LEFT JOIN appointment ap ON a.npi_number = ap.npi_number AND a.date = ap.date AND a.start_time = ap.start_time " +
                "WHERE a.npi_number = ? AND a.date = ? AND a.is_available = 'true'" +
                "ORDER BY a.start_time";

        List<Appointment> availableAppointments = new ArrayList<>();
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, clinicianId, date);
        while (results.next()) {
            availableAppointments.add(mapRowToAvailableAppointment(results));
        }
        return availableAppointments;
    }

    @Override
    public boolean addAppointment(Appointment appointment) {
        String sql = "INSERT INTO public.appointment(\n" +
                "\tnpi_number, patient_id, date, start_time, end_time, appointment_type, appointment_status)\n" +
                "\tVALUES (?, ?, ?, ?, ?, ?, ?);";

        return jdbcTemplate.update(sql,
                appointment.getClinicianId(),
                appointment.getPatientId(),
                appointment.getDate(),
                appointment.getStartTime(),
                appointment.getEndTime(),
                appointment.getAppointmentType(),
                appointment.getAppointmentStatus()) == 1;


    }

    @Override
    public boolean updateAppointment(Appointment appointment) {
        String sql = "UPDATE public.appointment\n" +
                "\tSET npi_number=?, patient_id=?, date=?, start_time=?, end_time=?, appointment_type=?, appointment_status=?\n" +
                "\tWHERE npi_number=? AND patient_id=? AND date=? AND start_time=?;";
        return jdbcTemplate.update(sql,
                appointment.getClinicianId(),
                appointment.getPatientId(),
                appointment.getDate(),
                appointment.getStartTime(),
                appointment.getEndTime(),
                appointment.getAppointmentType(),
                appointment.getAppointmentStatus(),
                appointment.getClinicianId(),
                appointment.getPatientId(),
                appointment.getDate(),
                appointment.getStartTime()) == 1;

    }

    @Override
    public boolean deleteAppointment(int appointmentId) {
        String sql = "DELETE FROM scheduled_appointments WHERE appointment_id = ?";
        return jdbcTemplate.update(sql, appointmentId) > 0;
    }

    private String getDoctorFullName(int clinicianId) {
        String sql = "SELECT s.staff_last_name || ', ' || s.staff_first_name AS doctor_name " +
                "FROM clinician c " +
                "JOIN staff s ON c.staff_id = s.staff_id " +
                "WHERE c.npi_number = ?";
        return jdbcTemplate.queryForObject(sql, String.class, clinicianId);
    }

    private String getPatientFullName(int patientId) {
        String sql = "SELECT p.patient_first_name || ' ' || p.patient_last_name AS patient_name " +
                "FROM patient p " +
                "WHERE p.patient_id = ?";
        return jdbcTemplate.queryForObject(sql, String.class, patientId);
    }

    private Appointment mapRowToAvailableAppointment(SqlRowSet rs) {
        Appointment appointment = new Appointment();
        appointment.setClinicianId(rs.getInt("npi_number"));
        appointment.setDate(rs.getDate("date"));
        appointment.setStartTime(rs.getTime("start_time"));
        appointment.setEndTime(rs.getTime("end_time"));
        appointment.setAppointmentType("Available Slot");
        appointment.setAppointmentStatus("Available");
        return appointment;
    }


}
