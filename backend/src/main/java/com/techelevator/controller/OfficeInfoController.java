package com.techelevator.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.dto.OfficeInfoDto;
import com.techelevator.service.OfficeInfoService;

@RestController
@CrossOrigin
@RequestMapping("/api/office-info")
public class OfficeInfoController {
    private final OfficeInfoService officeInfoService;

    public OfficeInfoController(OfficeInfoService officeInfoService) {
        this.officeInfoService = officeInfoService;
    }

    @GetMapping
    public List<OfficeInfoDto> getAllOfficesInfo() {
        return officeInfoService.getAllOfficesInfo();
    }
}
