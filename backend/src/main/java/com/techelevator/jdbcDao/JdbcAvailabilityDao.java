package com.techelevator.jdbcDao;

import com.techelevator.dao.AvailabilityDao;
import com.techelevator.model.Availability;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.util.List;


@Component
public class JdbcAvailabilityDao implements AvailabilityDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcAvailabilityDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Availability> availabilityRowMapper = (rs, rowNum) -> {
        Availability availability = new Availability();
        availability.setAvailabilityId(rs.getInt("availability_id"));
        availability.setNpiNumber(rs.getInt("npi_number"));
        availability.setOfficeId(rs.getInt("office_id"));
        availability.setDate(rs.getDate("date").toLocalDate());
        availability.setDayOfWeek(rs.getString("day_of_week"));
        availability.setStartTime(rs.getTime("start_time").toLocalTime());
        availability.setEndTime(rs.getTime("end_time").toLocalTime());
        availability.setAvailable(rs.getBoolean("is_available"));
        return availability;
    };

    @Override
    public Availability addAvailability(Availability availability) {
        String sql = "INSERT INTO availability (npi_number, office_id, date, day_of_week, start_time, end_time, is_available) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?) RETURNING availability_id";

        int newId = jdbcTemplate.queryForObject(sql, Integer.class,
                availability.getNpiNumber(),
                availability.getOfficeId(),
                availability.getDate(),
                availability.getDayOfWeek(),
                availability.getStartTime(),
                availability.getEndTime(),
                availability.isAvailable());

        availability.setAvailabilityId(newId);

        return availability;
    }

    @Override
    public boolean updateAvailability(Availability availability) {
        String sql = "UPDATE availability SET date = ?, day_of_week = ?, start_time = ?, end_time = ?, is_available = ? " +
                "WHERE availability_id = ?";
        return jdbcTemplate.update(sql,
                availability.getDate(),
                availability.getDayOfWeek(),
                availability.getStartTime(),
                availability.getEndTime(),
                availability.isAvailable(),
                availability.getAvailabilityId()) == 1;
    }

    @Override
    public boolean deleteAvailability(int availabilityId) {
        String sql = "DELETE FROM availability WHERE availability_id = ?";

        int rowsAffected = jdbcTemplate.update(sql, availabilityId);

        return rowsAffected > 0;
    }

    @Override
    public List<Availability> getAvailabilityByDoctorId(int npiNumber) {
        String sql = "SELECT * FROM availability WHERE npi_number = ?";
        return jdbcTemplate.query(sql, availabilityRowMapper, npiNumber);
    }

    @Override
    public List<Availability> getAvailabilityByDate(LocalDate date) {
        String sql = "SELECT * FROM availability WHERE date = ?";
        return jdbcTemplate.query(sql, availabilityRowMapper, date);
    }

    @Override
    public List<Availability> getAvailabilityByDoctorIdAndDate(int npiNumber, LocalDate date) {
        String sql = "SELECT * FROM availability WHERE npi_number = ? AND date = ?";
        
        return jdbcTemplate.query(sql, availabilityRowMapper, npiNumber, date);
    }
}
