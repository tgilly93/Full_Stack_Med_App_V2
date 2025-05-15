package com.techelevator.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.techelevator.dao.ClinicianOfficeInfoDao;
import com.techelevator.dto.ClinicianOfficeInfoDto;

@Service
public class ClinicianOfficeInfoService {
    private final ClinicianOfficeInfoDao clinicianOfficeInfoDao;

    public ClinicianOfficeInfoService(ClinicianOfficeInfoDao clinicianOfficeInfoDao) {
        this.clinicianOfficeInfoDao = clinicianOfficeInfoDao;
    }

    public List<ClinicianOfficeInfoDto> getAllClinicianOfficesInfo(){
        return clinicianOfficeInfoDao.getAllClinicianOfficesInfo();
    }
}
