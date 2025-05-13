package com.techelevator.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.techelevator.dao.SchedulingBlocksDao;
import com.techelevator.dto.SchedulingBlocksDto;

@Service
public class SchedulingBlocksService {
    private final SchedulingBlocksDao schedulingBlocksDao;

    public SchedulingBlocksService(SchedulingBlocksDao schedulingBlocksDao) {
        this.schedulingBlocksDao = schedulingBlocksDao;
    }

    public List<SchedulingBlocksDto> getAllSchedulingBlocks() {
        return schedulingBlocksDao.getAllSchedulingBlocks();
    }

    public List<SchedulingBlocksDto> getSchedulingBlocksByNpiNumber(int npiNumber) {
        return schedulingBlocksDao.getSchedulingBlocksByNpiNumber(npiNumber);
    }
}
