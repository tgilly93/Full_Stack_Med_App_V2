package com.techelevator.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.dto.PrescriptionInfoDto;
import com.techelevator.service.PrescriptionInfoService;

@RestController
@CrossOrigin
@RequestMapping("/api/rx-info")
public class PrescriptionInfoController {
    private final PrescriptionInfoService prescriptionInfoService;

    public PrescriptionInfoController(PrescriptionInfoService prescriptionInfoService) {
        this.prescriptionInfoService = prescriptionInfoService;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLINICIAN', 'ROLE_RECEPTIONIST', 'ROLE_PATIENT')")
    @GetMapping
    public List<PrescriptionInfoDto> getAllPrescriptionInfo() {
        return prescriptionInfoService.getAllPrescriptionInfo();
    }
}
