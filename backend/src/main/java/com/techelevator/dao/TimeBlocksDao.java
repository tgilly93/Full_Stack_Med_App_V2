package com.techelevator.dao;

import java.util.List;

import com.techelevator.dto.TimeBlocksDto;

public interface TimeBlocksDao {
    List<TimeBlocksDto> getAllTimeBlocks();

    List<TimeBlocksDto> getTimeBlocksByNpiNumber(int npiNumber);
}
