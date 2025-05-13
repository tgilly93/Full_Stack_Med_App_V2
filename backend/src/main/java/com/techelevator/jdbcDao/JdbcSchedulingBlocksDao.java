package com.techelevator.jdbcDao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.techelevator.dao.SchedulingBlocksDao;
import com.techelevator.dto.SchedulingBlocksDto;

@Component
public class JdbcSchedulingBlocksDao implements SchedulingBlocksDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcSchedulingBlocksDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<SchedulingBlocksDto> schedulingBlocksRowMapper = (rs, rowNum) -> {
        SchedulingBlocksDto schedulingBlocks = new SchedulingBlocksDto();
        schedulingBlocks.setBlockId(rs.getInt("block_id"));
        schedulingBlocks.setNpiNumber(rs.getInt("npi_number"));
        schedulingBlocks.setTimeBlock(rs.getString("Time Block"));
        schedulingBlocks.setStartTime(rs.getTime("start_time").toLocalTime());
        schedulingBlocks.setEndTime(rs.getTime("end_time").toLocalTime());
        return schedulingBlocks;
    };

    @Override
    public List<SchedulingBlocksDto> getAllSchedulingBlocks() {
        String sql = "SELECT * FROM scheduling_blocks";
        return jdbcTemplate.query(sql, schedulingBlocksRowMapper);
    }

    @Override
    public List<SchedulingBlocksDto> getSchedulingBlocksByNpiNumber(int npiNumber) {
        String sql = "SELECT * FROM scheduling_blocks WHERE npi_number = ?";
        return jdbcTemplate.query(sql, schedulingBlocksRowMapper, npiNumber);
    }
}
