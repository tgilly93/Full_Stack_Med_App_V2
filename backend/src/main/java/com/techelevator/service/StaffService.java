package com.techelevator.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.techelevator.dao.ClinicianDao;
import com.techelevator.dao.StaffDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Clinician;
import com.techelevator.model.Staff;

@Service
public class StaffService {
    private final StaffDao staffDao;
    private final UserDao userDao;  
    private final ClinicianDao clinicianDao;

    public StaffService(StaffDao staffDao, UserDao userDao, ClinicianDao clinicianDao) {
        this.staffDao = staffDao;
        this.userDao = userDao;
        this.clinicianDao = clinicianDao;
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

    @Transactional
    public boolean updateStaff(Staff staff) {
        int existingUserId = staffDao.getUserByStaffId(staff.getStaffId());
        staff.setUserId(existingUserId);

        boolean updated = staffDao.updateStaff(staff);

        if (updated) {
          String fullName = staff.getStaffFirstName() + " " + staff.getStaffLastName();
          userDao.updateUserNameAndAddress(existingUserId, fullName, staff.getStaffAddress());
        }
        return updated;
    }

    @Transactional
    public boolean deleteStaff(int staffId) {
        int userId = staffDao.getUserByStaffId(staffId);

        Clinician clinicianToDelete = null;
        for (Clinician clinician : clinicianDao.getAllClinicians()) {
            if (clinician.getStaffId() == staffId) {
                clinicianToDelete = clinician;
                break;
            }
        }
        if (clinicianToDelete != null) {
            clinicianDao.deleteClinician(clinicianToDelete.getNpiNumber());
        }
        staffDao.deleteStaff(staffId);
        userDao.deleteUser(userId);
        return true;
    }
}
