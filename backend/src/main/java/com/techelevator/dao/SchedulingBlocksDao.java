package com.techelevator.dao;

import java.util.List;

import com.techelevator.dto.SchedulingBlocksDto;

public interface SchedulingBlocksDao {
    List<SchedulingBlocksDto> getAllSchedulingBlocks();

    List<SchedulingBlocksDto> getSchedulingBlocksByNpiNumber(int npiNumber);
}
