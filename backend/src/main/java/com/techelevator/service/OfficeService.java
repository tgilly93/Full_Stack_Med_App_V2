package com.techelevator.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.techelevator.dao.OfficeDao;
import com.techelevator.model.Office;

@Service
public class OfficeService {
    private final OfficeDao officeDao;

    public OfficeService(OfficeDao officeDao) {
        this.officeDao = officeDao;
    }

    public List<Office> getAllOffices() {
        return officeDao.getAllOffices();
    }

    public Office getOfficeById(int officeId) {
        return officeDao.getOfficeById(officeId);
    }

    public Office createOffice(Office office) {
        return officeDao.createOffice(office);
    }

    public boolean updateOffice(Office office) {
        return officeDao.updateOffice(office);
    }

    public boolean deleteOffice(int officeId) {
        return officeDao.deleteOffice(officeId);
    }
}
