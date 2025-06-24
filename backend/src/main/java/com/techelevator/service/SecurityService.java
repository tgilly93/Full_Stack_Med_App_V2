package com.techelevator.service;

import org.springframework.stereotype.Component;

import com.techelevator.dao.StaffDao;
import com.techelevator.model.Availability;
import com.techelevator.model.Clinician;
import com.techelevator.model.Messages;
import com.techelevator.model.Patient;
import com.techelevator.model.Staff;

@Component
public class SecurityService {
    private final PatientService patientService;
    private final ClinicianService clinicianService;
    private final StaffDao staffDao;
    private final AvailabilityService availabilityService;
    private final MessagesService messagesService;

    public SecurityService(PatientService patientService, ClinicianService clinicianService, StaffDao staffDao, AvailabilityService availabilityService, MessagesService messagesService) {
        this.patientService = patientService;
        this.clinicianService = clinicianService;
        this.staffDao = staffDao;
        this.availabilityService = availabilityService;
        this.messagesService = messagesService;
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

    public boolean isAvailabilityOwnedByUser(int availabilityId, int userId) {
        Availability availability = availabilityService.getAvailabilityById(availabilityId);
        if (availability == null) return false;
            
        return isClinicianOwnedByUser(availability.getNpiNumber(), userId);
    }

    public boolean isMessageOwnedByAuthUser(int messageId, int authenticatedUserId, int pathUserId) {
        if (authenticatedUserId != pathUserId) {
            return false; 
        }

        Messages message = messagesService.getMessagesById(messageId);
        return message != null && 
                (message.getSenderId() == authenticatedUserId || message.getReceiverId() == authenticatedUserId);
    }

    public boolean canUserAccessMessage(int messageId, int userId) {
        Messages message = messagesService.getMessagesById(messageId);
        return message != null && 
               (message.getSenderId() == userId || message.getReceiverId() == userId);
    }
}
