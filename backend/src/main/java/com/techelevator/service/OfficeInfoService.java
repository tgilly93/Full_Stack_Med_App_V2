package com.techelevator.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.techelevator.dao.OfficeInfoDao;
import com.techelevator.dto.OfficeInfoDto;

@Service
public class OfficeInfoService {
    private final OfficeInfoDao officeInfoDao;

    public OfficeInfoService(OfficeInfoDao officeInfoDao) {
        this.officeInfoDao = officeInfoDao;
    }

    public List<OfficeInfoDto> getAllOfficesInfo() {
        return officeInfoDao.getAllOfficesInfo();
    }
}
