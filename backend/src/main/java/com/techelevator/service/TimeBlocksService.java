package com.techelevator.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.techelevator.dao.TimeBlocksDao;
import com.techelevator.dto.TimeBlocksDto;

@Service
public class TimeBlocksService {
    private final TimeBlocksDao timeBlocksDao;

    public TimeBlocksService(TimeBlocksDao timeBlocksDao) {
        this.timeBlocksDao = timeBlocksDao;
    }

    public List<TimeBlocksDto> getAllTimeBlocks() {
        return timeBlocksDao.getAllTimeBlocks();
    }

    public List<TimeBlocksDto> getTimeBlocksByNpiNumber(int npiNumber) {
        return timeBlocksDao.getTimeBlocksByNpiNumber(npiNumber);
    }
}
