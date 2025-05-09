package com.techelevator.dao;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.dto.DailyAgendaDto;
import com.techelevator.dto.WeeklyAgendaDto;
import com.techelevator.dto.MonthlyAgendaDto;
import com.techelevator.model.Agenda;

@Component
public class JbdcAgendaDao implements AgendaDao {
    private final JdbcTemplate jdbcTemplate;

    public JbdcAgendaDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<DailyAgendaDto> dailyMapper = (rs, rowNum) -> new DailyAgendaDto(
            rs.getDate("Date").toLocalDate(),
            rs.getString("Day_of_Week"),
            rs.getString("Doctor"),
            rs.getInt("Patient"),
            rs.getString("Patient Name"),
            rs.getInt("Time Block"),
            rs.getTime("start_time").toLocalTime(),
            rs.getTime("end_time").toLocalTime(),
            rs.getString("Type"),
            rs.getString("Status"),
            rs.getInt("appointment_id"));

    private final RowMapper<WeeklyAgendaDto> weeklyMapper = (rs, rowNum) -> new WeeklyAgendaDto(
            rs.getDate("Week_Start_Date").toLocalDate(),
            rs.getString("Week_Label"),
            rs.getDate("Date").toLocalDate(),
            rs.getString("Day_of_Week"),
            rs.getString("Doctor"),
            rs.getInt("Patient"),
            rs.getString("Patient Name"),
            rs.getInt("Time Block"),
            rs.getTime("start_time").toLocalTime(),
            rs.getTime("end_time").toLocalTime(),
            rs.getString("Type"),
            rs.getString("Status"),
            rs.getInt("appointment_id"));

    private final RowMapper<MonthlyAgendaDto> monthlyMapper = (rs, rowNum) -> new MonthlyAgendaDto(
            rs.getDate("Month_Start_Date").toLocalDate(),
            rs.getString("Month_Label"),
            rs.getDate("Date").toLocalDate(),
            rs.getString("Day_of_Week"),
            rs.getString("Doctor"),
            rs.getInt("Patient"),
            rs.getString("Patient Name"),
            rs.getInt("Time Block"),
            rs.getTime("start_time").toLocalTime(),
            rs.getTime("end_time").toLocalTime(),
            rs.getString("Type"),
            rs.getString("Status"),
            rs.getInt("appointment_id"));

    @Override
    public List<DailyAgendaDto> getDailyAgenda() {
        String sql = "SELECT * FROM daily_agenda";
        return jdbcTemplate.query(sql, dailyMapper);
    }

    @Override
    public List<DailyAgendaDto> getDailyAgendaByDate(LocalDate date) {
        String sql = "SELECT * FROM get_daily_agenda_for_date(?)";
        return jdbcTemplate.query(sql, dailyMapper, date);
    }

    @Override
    public List<WeeklyAgendaDto> getWeeklyAgenda() {
        String sql = "SELECT * FROM weekly_agenda";
        return jdbcTemplate.query(sql, weeklyMapper);
    }

    @Override
    public List<MonthlyAgendaDto> getMonthlyAgenda() {
        String sql = "SELECT * FROM monthly_agenda";
        return jdbcTemplate.query(sql, monthlyMapper);
    }
}
