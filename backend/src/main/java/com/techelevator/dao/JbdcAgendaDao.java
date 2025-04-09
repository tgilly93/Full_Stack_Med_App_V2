package com.techelevator.dao;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.model.Agenda;

@Component
public class JbdcAgendaDao implements AgendaDao {
    private final JdbcTemplate jdbcTemplate;

    public JbdcAgendaDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private Agenda mapRowToAgenda(SqlRowSet rs) {
        Agenda newAgenda = new Agenda();
        newAgenda.setDate(rs.getDate("Date"));
        newAgenda.setDayOfWeek(rs.getString("Day_of_Week"));
        newAgenda.setDoctorName(rs.getString("Doctor"));
        newAgenda.setPatientName(rs.getString("Patient Name"));
        newAgenda.setPatientId(rs.getInt("Patient"));
        newAgenda.setAppointmentType(rs.getString("type"));
        newAgenda.setAppointmentStatus(rs.getString("status"));
        newAgenda.setScheduleBlock(rs.getInt("Time Block"));
        newAgenda.setAppointmentStartTime(rs.getTime("start_time"));
        newAgenda.setAppointmentEndTime(rs.getTime("end_time"));

        return newAgenda;

    } 
// maybe incorporate authentication.

    @Override
    public List<Agenda> getDailyAgendas() {
        String sql = " SELECT * FROM daily_agenda;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        List<Agenda> agendas = new ArrayList<>();
        while (results.next()) {
            agendas.add(mapRowToAgenda(results));
        }
        return agendas;
    }

    @Override
    public List<Agenda> getAllAgendasByWeek(Date date) {
        String sql = "SELECT \"Date\", \"Day_of_Week\", \"Doctor\", \"Patient\", \"Patient Name\", type, status, \"Time Block\", start_time, end_time FROM weekly_agenda WHERE \"Week_Start_Date\" = date_trunc('week', ?::date);";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, date);
        List<Agenda> agendas = new ArrayList<>();
        while (results.next()) {
             agendas.add(mapRowToAgenda(results));
        }
        return agendas;
    }

    @Override
    public List<Agenda> getAllAgendasByMonth(Date date) {
        String sql = "SELECT \"Date\", \"Day_of_Week\", \"Doctor\", \"Patient\", \"Patient Name\", type, status, \"Time Block\", start_time, end_time FROM monthly_agenda WHERE \"Month_Start_Date\" = date_trunc('month', ?::date);";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, date);
        List<Agenda> agendas = new ArrayList<>();
        while (results.next()) {
            agendas.add(mapRowToAgenda(results));
        }
        return agendas;
    }

    @Override 
    public Agenda getDailyAgendaByDoctorNameTime(String doctorName, LocalTime appointmentStartTime) {
        doctorName = "%" + doctorName + "%";
        String sql = "SELECT *\n" +
                "FROM public.daily_agenda WHERE \"Doctor\" ILIKE ? AND start_time = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, doctorName, appointmentStartTime);
        if (results.next()) {
            return mapRowToAgenda(results);
        }
        return null;
    }

    @Override
    public Agenda getDailyAgendaByPatientNameTime(String patientName, LocalTime appointmentStartTime) {
        patientName = "%" + patientName + "%";
        String sql = "SELECT *\n" +
                "FROM public.daily_agenda WHERE \"Patient Name\" ILIKE ? AND start_time = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, patientName, appointmentStartTime);
        if (results.next()) {
            return mapRowToAgenda(results);
        }
        return null;
    }

    @Override
    public Agenda getAgendaByDoctorNameDateTime(Date date, String doctorName, LocalTime appointmentStartTime) {
        doctorName = "%" + doctorName + "%";
        String sql = "SELECT *\n" +
                "FROM get_daily_agenda_for_date('?') WHERE \"Doctor\" ILIKE ? AND start_time = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, date, doctorName, appointmentStartTime);
        if (results.next()) {
            return mapRowToAgenda(results);
        }
        return null;
    }

    @Override
    public Agenda getAgendaByPatientNameDateTime(Date date, String patientName, LocalTime appointmentStartTime) {
        patientName = "%" + patientName + "%";
        String sql = "SELECT *\n" +
                "FROM get_daily_agenda_for_date('?') WHERE \"Patient Name\" ILIKE ? AND start_time = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, patientName, date, appointmentStartTime);
        if (results.next()) {
            return mapRowToAgenda(results);
        }
        return null;
    }

    @Override
    public Agenda getAgendaByPatientIdDateTime(int patientId, Date date, LocalTime appointmentStartTime) {
        String sql = "SELECT *\n" +
                "FROM get_daily_agenda_for_date('?') WHERE \"Patient\" = ? AND start_time = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, date, patientId, appointmentStartTime);
        if (results.next()) {
            return mapRowToAgenda(results);
        }
        return null;
    }

    @Override
    public Agenda getAgendaByAppointmentTypeDateTime(Date date, String appointmentType, LocalTime appointmentStartTime) {
        appointmentType = "%" + appointmentType + "%";
        String sql = "SELECT *\n" +
                "FROM get_daily_agenda_for_date('?') WHERE type ILIKE ? AND start_time = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, date, appointmentType, appointmentStartTime);
        if (results.next()) {
            return mapRowToAgenda(results);
        }
        return null;
    }

    @Override
    public List<Agenda> getDailyAgendaByDoctorName(String doctorName){
        doctorName = "%" + doctorName + "%";
        String sql = "SELECT *\n" +
                "FROM public.daily_agenda WHERE \"Doctor\" ILIKE ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, doctorName);
        List<Agenda> agendas = new ArrayList<>();
        while (results.next()) {
            agendas.add(mapRowToAgenda(results));
        }
        return agendas;
    }

    @Override
    public List<Agenda> getDailyAgendaByPatientName(String patientName) {
        patientName = "%" + patientName + "%";
        String sql = "SELECT *\n" +
                "FROM public.daily_agenda WHERE \"Patient Name\" ILIKE ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, patientName);
        List<Agenda> agendas = new ArrayList<>();
        while (results.next()) {
            agendas.add(mapRowToAgenda(results));
        }
        return agendas;
    }

    @Override
    public List<Agenda> getDailyAgendaByPatientId(int patientId) {
        String sql = "SELECT *\n" +
                "FROM public.daily_agenda WHERE \"Patient\" = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, patientId);
        List<Agenda> agendas = new ArrayList<>();
        while (results.next()) {
             agendas.add(mapRowToAgenda(results));
        }
        return agendas;
    }

    @Override
    public List<Agenda> getDailyByAppointmentType(String appointmentType) {
        String sql = "SELECT *\n" +
                "FROM public.daily_agenda WHERE type = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, appointmentType);
        List<Agenda> agendas = new ArrayList<>();
        while (results.next()) {
            agendas.add(mapRowToAgenda(results));
        }
        return agendas;
    }

    @Override
    public List<Agenda> getDailyAgendaByAppointmentStatus(String appointmentStatus) {
        String sql = "SELECT *\n" +
                "FROM public.daily_agenda WHERE status = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, appointmentStatus);
        List<Agenda> agendas = new ArrayList<>();
        while (results.next()) {
            agendas.add(mapRowToAgenda(results));
        }
        return agendas;
    }

    @Override
    public List<Agenda> getDailyAgendaByScheduleBlock(int scheduleBlock) {
        String sql = "SELECT *\n" +
                "FROM get_daily_agenda_for_date(?) WHERE \"Time Block\" = ?;";  
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, scheduleBlock);
        List<Agenda> agendas = new ArrayList<>();
        while (results.next()) {
            agendas.add(mapRowToAgenda(results));
        }
        return agendas;
    }

    @Override
    public Agenda getAgendaByAppointmentStartTime(Date date, LocalTime appointmentStartTime) {
        String sql = "SELECT *\n" +
                "FROM get_daily_agenda_for_date(?) WHERE \"start_time\" = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, date, appointmentStartTime);
        if (results.next()) {
            return mapRowToAgenda(results);
        }
        return null;
    }

    @Override
    public List<Agenda> getAllConfirmedByDoctor(String doctorName, String appointmentStatus) {
        doctorName = "%" + doctorName + "%";
        String sql = "SELECT *\n" +
                "FROM public.daily_agenda WHERE \"Doctor\" ILIKE ? AND status = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, doctorName, appointmentStatus);
        List<Agenda> agendas = new ArrayList<>();
        while (results.next()) {
            agendas.add(mapRowToAgenda(results));
        }
        return agendas;
    }

    @Override
    public List<Agenda> getAllConfirmedByPatient(String patientName, String appointmentStatus) {
        patientName = "%" + patientName + "%";
        String sql = "SELECT *\n" +
                "FROM public.daily_agenda WHERE \"Patient Name\" ILIKE ? AND status = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, patientName, appointmentStatus);
        List<Agenda> agendas = new ArrayList<>();
        while (results.next()) {
            agendas.add(mapRowToAgenda(results));
        }
        return agendas;
    }

    @Override
    public List<Agenda> getAllConfirmedByPatientId(int patientId, String appointmentStatus) {
        String sql = "SELECT *\n" +
                "FROM public.daily_agenda WHERE \"Patient\" = ? AND status = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, patientId, appointmentStatus);
        List<Agenda> agendas = new ArrayList<>();
        while (results.next()) {
            agendas.add(mapRowToAgenda(results));
        }
        return agendas;
    }

    @Override
    public List<Agenda> getAllUrgentByDoctor(String doctorName, String appointmentType) {
        doctorName = "%" + doctorName + "%";
        appointmentType = "%" + appointmentType + "%";
        String sql = "SELECT *\n" +
                "FROM public.daily_agenda WHERE \"Doctor\" ILIKE ? AND type ILIKE ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, doctorName, appointmentType);
        List<Agenda> agendas = new ArrayList<>();
        while (results.next()) {
            agendas.add(mapRowToAgenda(results));
        }
        return agendas;
    }

    @Override
    public List<Agenda> getAllUrgentByPatient(String patientName, String appointmentType) {
        patientName = "%" + patientName + "%";
        appointmentType = "%" + appointmentType + "%";
        String sql = "SELECT *\n" +
                "FROM public.daily_agenda WHERE \"Patient Name\" ILIKE ? AND type ILIKE ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, patientName, appointmentType);
        List<Agenda> agendas = new ArrayList<>();
        while (results.next()) {
            agendas.add(mapRowToAgenda(results));
        }
        return agendas;
    }

    @Override
    public List<Agenda> getAllUrgentByPatientId(int patientId, String appointmentType) {
        appointmentType = "%" + appointmentType + "%";
        String sql = "SELECT * FROM public.daily_agenda WHERE \"Patient\" = ? AND type ILIKE ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, patientId, appointmentType);
        List<Agenda> agendas = new ArrayList<>();
        while (results.next()) {
            agendas.add(mapRowToAgenda(results));
        }
        return agendas;
    }

    @Override
    public List<Agenda> getAllAgendas() {
        String sql = "SELECT * FROM scheduled_appointments;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        List<Agenda> agendas = new ArrayList<>();
        while (results.next()) {
            agendas.add(mapRowToAgenda(results));
        }
        return agendas;
    }

    @Override
    public List<Agenda> getAllAgendasByDoctorName(String doctorName) {
        doctorName = "%" + doctorName + "%";
        String sql = "SELECT * FROM scheduled_appointments WHERE \"Doctor\" ILIKE ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, doctorName);
        List<Agenda> agendas = new ArrayList<>();
        while (results.next()) {
            agendas.add(mapRowToAgenda(results));
        }
        return agendas;
    }

    @Override
    public List<Agenda> getAllAgendasByPatientName(String patientName) {
        patientName = "%" + patientName + "%";
        String sql = "SELECT * FROM scheduled_appointments WHERE \"Patient Name\" ILIKE ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, patientName);
        List<Agenda> agendas = new ArrayList<>();
        while (results.next()) {
            agendas.add(mapRowToAgenda(results));
        }
        return agendas;
    }

    @Override
    public List<Agenda> getAllAgendasByPatientId(int patientId) {
        String sql = "SELECT * FROM scheduled_appointments WHERE \"Patient\" = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, patientId);
        List<Agenda> agendas = new ArrayList<>();
        while (results.next()) {
            agendas.add(mapRowToAgenda(results));
        }
        return agendas;
    }

    @Override
    public List<Agenda> getAllAgendasByAppointmentType(String appointmentType) {
        appointmentType = "%" + appointmentType + "%";
        String sql = "SELECT * FROM scheduled_appointments WHERE type ILIKE ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, appointmentType);
        List<Agenda> agendas = new ArrayList<>();
        while (results.next()) {
            agendas.add(mapRowToAgenda(results));
        }
        return agendas;
    }

    @Override
    public List<Agenda> getAllAgendasByAppointmentStatus(String appointmentStatus) {
        String sql = "SELECT * FROM scheduled_appointments WHERE status = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, appointmentStatus);
        List<Agenda> agendas = new ArrayList<>();
        while (results.next()) {
            agendas.add(mapRowToAgenda(results));
        }
        return agendas;
    }

    @Override
    public boolean updateAgenda(Agenda agenda) {
        String sql = "UPDATE scheduled_appointments SET \"Date\" = ?, \"Doctor\" = ?, \"Patient Name\" = ?, \"Patient\" = ?, type = ?, status = ?, start_time = ?, end_time = ? WHERE \"Date\" = ? AND \"Doctor\" = ? AND \"Patient\" = ?;";
        return jdbcTemplate.update(sql, agenda.getDate(), agenda.getDoctorName(), agenda.getPatientName(), agenda.getPatientId(), agenda.getAppointmentType(), agenda.getAppointmentStatus(), agenda.getAppointmentStartTime(), agenda.getAppointmentEndTime(), agenda.getDate(), agenda.getDoctorName(), agenda.getPatientId()) == 1;
    }

    @Override
    public boolean addAgenda(Agenda agenda) {
        String sql = "INSERT INTO scheduled_appointments (\"Date\", \"Doctor\", \"Patient Name\", \"Patient\", type, status, start_time, end_time) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
        return jdbcTemplate.update(sql, agenda.getDate(), agenda.getDoctorName(), agenda.getPatientName(), agenda.getPatientId(), agenda.getAppointmentType(), agenda.getAppointmentStatus(), agenda.getAppointmentStartTime(), agenda.getAppointmentEndTime()) == 1;
    }

    @Override
    public boolean deleteAgenda(Agenda agenda) {
        String sql = "DELETE FROM scheduled_appointments WHERE \"Date\" = ? AND \"Doctor\" ILIKE ?  AND \"Patient\" = ? AND start_time = ?;";
        return jdbcTemplate.update(sql, agenda.getDate(), agenda.getDoctorName(), agenda.getPatientId(), agenda.getAppointmentStartTime()) == 1;
    }


}
