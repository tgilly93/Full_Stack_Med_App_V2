package com.techelevator.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.dto.SchedulingBlocksDto;
import com.techelevator.service.SchedulingBlocksService;

@RestController
@CrossOrigin
@RequestMapping("/api/scheduling-blocks")
public class SchedulingBlocksController {
    private final SchedulingBlocksService schedulingBlocksService;

    public SchedulingBlocksController(SchedulingBlocksService schedulingBlocksService) {
        this.schedulingBlocksService = schedulingBlocksService;
    }

    @GetMapping
    public List<SchedulingBlocksDto> getAllSchedulingBlocks() {
        return schedulingBlocksService.getAllSchedulingBlocks();
    }

    @GetMapping("/npi/{npiNumber}")
    public List<SchedulingBlocksDto> getSchedulingBlocksByNpiNumber(@PathVariable int npiNumber) {
        return schedulingBlocksService.getSchedulingBlocksByNpiNumber(npiNumber);
    }
}
