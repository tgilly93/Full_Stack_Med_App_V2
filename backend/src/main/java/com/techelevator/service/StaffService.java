package com.techelevator.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.techelevator.dao.StaffDao;
import com.techelevator.model.Staff;

@Service
public class StaffService {
    private final StaffDao staffDao;

    public StaffService(StaffDao staffDao) {
        this.staffDao = staffDao;
    }

    public List<Staff> getAllStaff() {
        return staffDao.getAllStaff();
    }

    public Staff getStaffById(int staffId) {
        return staffDao.getStaffById(staffId);
    }

    public Staff createStaff(Staff staff) {
        return staffDao.createStaff(staff);
    }

    public boolean updateStaff(Staff staff) {
        return staffDao.updateStaff(staff);
    }

    public boolean deleteStaff(int staffId) {
        return staffDao.deleteStaff(staffId);
    }
}
