package com.techelevator.dao;

import com.techelevator.model.Availability;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcAvailabilityDao implements AvailabilityDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcAvailabilityDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean addAvailability(Availability availability) {
        String sql = "INSERT INTO availability (npi_number, office_id, date, day_of_week, start_time, end_time, is_available) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                availability.getNpiNumber(),
                availability.getOfficeId(),
                availability.getAvailabilityDate(),
                availability.getDayOfWeek(),
                availability.getStartTime(),
                availability.getEndTime(),
                availability.isAvailable()) == 1;
    }

    @Override
    public boolean updateAvailability(Availability availability) {
        String sql = "UPDATE availability SET date = ?, day_of_week = ?, start_time = ?, end_time = ?, is_available = ? " +
                "WHERE availability_id = ?";
        return jdbcTemplate.update(sql,
                availability.getAvailabilityDate(),
                availability.getDayOfWeek(),
                availability.getStartTime(),
                availability.getEndTime(),
                availability.isAvailable(),
                availability.getAvailabilityId()) == 1;
    }

    @Override
    public boolean deleteAvailability(int availabilityId) {
        String sql = "DELETE FROM availability WHERE availability_id = ?";
        return jdbcTemplate.update(sql, availabilityId) == 1;
    }

    @Override
    public List<Availability> getAvailabilityByDoctorId(int doctorId) {
        String sql = "SELECT * FROM availability WHERE npi_number = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, doctorId);
        return mapResultsToList(results);
    }

    @Override
    public List<Availability> getAvailabilityByDate(LocalDate date) {
        String sql = "SELECT * FROM availability WHERE date = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, Date.valueOf(date));
        return mapResultsToList(results);
    }

    @Override
    public Availability getAvailabilityByDoctorIdAndDate(int doctorId, LocalDate date) {
        String sql = "SELECT * FROM availability WHERE npi_number = ? AND date = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, doctorId, Date.valueOf(date));
        if (results.next()) {
            return mapRowToAvailability(results);
        }
        return null;
    }

    private List<Availability> mapResultsToList(SqlRowSet results) {
        List<Availability> availabilities = new ArrayList<>();
        while (results.next()) {
            availabilities.add(mapRowToAvailability(results));
        }
        return availabilities;
    }

    private Availability mapRowToAvailability(SqlRowSet rs) {
        Availability availability = new Availability();
        availability.setAvailabilityId(rs.getInt("availability_id"));
        availability.setNpiNumber(rs.getInt("npi_number"));
        availability.setOfficeId(rs.getInt("office_id"));
        availability.setAvailabilityDate(rs.getDate("date"));
        availability.setDayOfWeek(rs.getString("day_of_week"));
        availability.setStartTime(rs.getTime("start_time"));
        availability.setEndTime(rs.getTime("end_time"));
        availability.setAvailable(rs.getString("is_available").equalsIgnoreCase("true"));
        return availability;
    }
}
