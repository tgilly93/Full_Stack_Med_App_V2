package com.techelevator.jdbcDao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.techelevator.dao.TimeBlocksDao;
import com.techelevator.dto.TimeBlocksDto;

@Component
public class JdbcTimeBlocksDao implements TimeBlocksDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcTimeBlocksDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<TimeBlocksDto> timeBlocksRowMapper = (rs, rowNum) -> {
        TimeBlocksDto timeBlock = new TimeBlocksDto();
        timeBlock.setNpiNumber(rs.getInt("npi_number"));
        timeBlock.setTimeBlock(rs.getString("Time Block"));
        timeBlock.setStartTime(rs.getTime("start_time").toLocalTime());
        timeBlock.setEndTime(rs.getTime("end_time").toLocalTime());
        return timeBlock;
    };

    @Override
    public List<TimeBlocksDto> getAllTimeBlocks() {
        String sql = "SELECT * FROM time_blocks";
        return jdbcTemplate.query(sql, timeBlocksRowMapper);
    }

    @Override
    public List<TimeBlocksDto> getTimeBlocksByNpiNumber(int npiNumber) {
        String sql = "SELECT * FROM time_blocks WHERE npi_number = ?";
        return jdbcTemplate.query(sql, timeBlocksRowMapper, npiNumber);
    }
}
