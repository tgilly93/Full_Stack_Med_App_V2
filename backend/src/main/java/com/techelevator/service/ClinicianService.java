package com.techelevator.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.techelevator.dao.ClinicianDao;
import com.techelevator.dao.StaffDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Clinician;

@Service
public class ClinicianService {
    private final ClinicianDao clinicianDao;
    private final StaffDao staffDao;
    private final UserDao userDao;

    public ClinicianService(ClinicianDao clinicianDao, StaffDao staffDao, UserDao userDao) {
        this.clinicianDao = clinicianDao;
        this.staffDao = staffDao;
        this.userDao = userDao;
    }

    public List<Clinician> getAllClinicians() {
        return clinicianDao.getAllClinicians();
    }

    public Clinician getClinicianByNpi(int npiNumber) {
        return clinicianDao.getClinicianByNpi(npiNumber);
    }

    public Clinician createClinician(Clinician clinician) {
        return clinicianDao.createClinician(clinician);
    }

    public boolean updateClinician(Clinician clinician) {
        return clinicianDao.updateClinician(clinician);
    }

    @Transactional
    public boolean deleteClinician(int npiNumber) {
        Clinician clinician = clinicianDao.getClinicianByNpi(npiNumber);
        if (clinician == null) {
            return false; 
        }

        boolean clinicianDeleted = clinicianDao.deleteClinician(npiNumber);
        boolean staffDeleted = staffDao.deleteStaff(clinician.getStaffId());
        boolean deleteUser = userDao.deleteUser(clinician.getUserId());

        return clinicianDeleted && staffDeleted && deleteUser;
    }
}
