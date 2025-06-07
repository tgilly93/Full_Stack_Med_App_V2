package com.techelevator.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.dto.ClinicianOfficeInfoDto;
import com.techelevator.service.ClinicianOfficeInfoService;

@RestController
@CrossOrigin
@RequestMapping("/api/clinician-office-info")
public class ClinicianOfficeInfoController {
    private final ClinicianOfficeInfoService clinicianOfficeInfoService;

    public ClinicianOfficeInfoController(ClinicianOfficeInfoService clinicianOfficeInfoService) {
        this.clinicianOfficeInfoService = clinicianOfficeInfoService;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLINICIAN', 'ROLE_RECEPTIONIST', 'ROLE_PATIENT')")
    @GetMapping
    public List<ClinicianOfficeInfoDto> getAllClinicianOfficesInfo() {
        return clinicianOfficeInfoService.getAllClinicianOfficesInfo();
    }
}
