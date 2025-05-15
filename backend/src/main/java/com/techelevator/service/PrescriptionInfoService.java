package com.techelevator.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.techelevator.dao.PrescriptionInfoDao;
import com.techelevator.dto.PrescriptionInfoDto;

@Service
public class PrescriptionInfoService {
    private final PrescriptionInfoDao prescriptionInfoDao;

    public PrescriptionInfoService(PrescriptionInfoDao prescriptionInfoDao) {
        this.prescriptionInfoDao = prescriptionInfoDao;
    }

    public List<PrescriptionInfoDto> getAllPrescriptionInfo() {
        return prescriptionInfoDao.getAllPrescriptionInfo();
    }
}
