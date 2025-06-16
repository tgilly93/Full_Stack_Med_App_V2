package com.techelevator.service;

import org.springframework.stereotype.Component;

import com.techelevator.dao.StaffDao;
import com.techelevator.model.Clinician;
import com.techelevator.model.Patient;
import com.techelevator.model.Staff;

@Component
public class SecurityService {
    private final PatientService patientService;
    private final ClinicianService clinicianService;
    private final StaffDao staffDao;

    public SecurityService(PatientService patientService, ClinicianService clinicianService, StaffDao staffDao) {
        this.patientService = patientService;
        this.clinicianService = clinicianService;
        this.staffDao = staffDao;
    }

    public boolean isPatientOwnedByUser(int patientId, int userId) {
        Patient patient = patientService.getPatientByPatientId(patientId);
        return patient != null && patient.getUserId() == userId;
    }

    public boolean isClinicianOwnedByUser(int npiNumber, int userId) {
        Clinician clinician = clinicianService.getClinicianByNpi(npiNumber);
        return clinician != null && clinician.getUserId() == userId;
    }

    public boolean isStaffOwnedByUser(int staffId, int userId) {
        Staff staff = staffDao.getStaffById(staffId);
        return staff != null && staff.getUserId() == userId;
    }

    public boolean isUserIdMatching(int id, int userId) {
        return id == userId;
    }
}
