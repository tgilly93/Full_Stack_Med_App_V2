package com.techelevator.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.dto.TimeBlocksDto;
import com.techelevator.service.TimeBlocksService;

@RestController
@CrossOrigin
@RequestMapping("/api/timeblocks")
public class TimeBlocksController {
    private final TimeBlocksService timeBlocksService;

    public TimeBlocksController(TimeBlocksService timeBlocksService) {
        this.timeBlocksService = timeBlocksService;
    }

    @GetMapping
    public List<TimeBlocksDto> getAllTimeBlocks() {
        return timeBlocksService.getAllTimeBlocks();
    }

    @GetMapping("/npi/{npiNumber}")
    public List<TimeBlocksDto> getTimeBlocksByNpiNumber(@PathVariable int npiNumber) {
        return timeBlocksService.getTimeBlocksByNpiNumber(npiNumber);
    }
}
