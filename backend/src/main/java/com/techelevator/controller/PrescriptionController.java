package com.techelevator.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.techelevator.dao.PrescriptionDao;
import com.techelevator.model.Prescription;


@RestController
@CrossOrigin
@RequestMapping("/api/prescriptions")
public class PrescriptionController {
    @Autowired
    private PrescriptionDao prescriptionDao;
    
        @RequestMapping(path = "/{prescriptionId}", method = RequestMethod.GET)
        public Prescription getPrescriptionById(@PathVariable int prescriptionId) {
            return prescriptionDao.getPrescriptionById(prescriptionId);
        }
    
        @RequestMapping(path = "/patient/{patientId}", method = RequestMethod.GET)
        public Prescription getPrescriptionByPatientId(@RequestParam int patientId) {
            return prescriptionDao.getPrescriptionByPatientId(patientId);
        }
    
        @RequestMapping(path = "/doctor/{doctorId}", method = RequestMethod.GET)
        public Prescription getPrescriptionByDoctorId(@RequestParam int doctorId) {
            return prescriptionDao.getPrescriptionByDoctorId(doctorId);
        }
    
        @RequestMapping(path = "/{prescriptionId}", method = RequestMethod.PUT)
        public boolean updatePrescription(@RequestParam Prescription prescription) {
            return prescriptionDao.updatePrescription(prescription);
        }
    
        @RequestMapping(path = "/add", method = RequestMethod.POST)
        public boolean addPrescription(@RequestParam Prescription prescription) {
            return prescriptionDao.addPrescription(prescription);
        }
    
        @RequestMapping(path = "/{prescriptionId}", method = RequestMethod.DELETE)
        public boolean deletePrescription(@RequestParam int prescriptionId) {
            return prescriptionDao.deletePrescription(prescriptionId);
        }
    
        @RequestMapping(path = "/patient/{patientId}", method = RequestMethod.DELETE)
        public boolean deletePrescriptionByPatientId(@RequestParam int patientId) {
            return prescriptionDao.deletePrescriptionByPatientId(patientId);
        }

        @RequestMapping(path = "patient/{patientId}/prescriptions", method=RequestMethod.GET)
        public List<Prescription> getAllPrescriptionsByPatientId(@PathVariable int patientId) {
            return prescriptionDao.getAllPrescriptionsByPatientId(patientId);
        }

        @RequestMapping(path = "doctor/{doctorId}/prescriptions", method=RequestMethod.GET)
        public List<Prescription> getAllPrescriptionsByDoctorId(@PathVariable int doctorId) {
            return prescriptionDao.getAllPrescriptionsByDoctorId(doctorId);
        }
        
        @RequestMapping(path = "patient/{patientId}/status/{prescriptionStatus}", method=RequestMethod.GET)
        public List<Prescription> getPatientPrescriptionByStatus(@PathVariable int patientId, @PathVariable String prescriptionStatus) {
            return prescriptionDao.getPatientPrescriptionByStatus(patientId, prescriptionStatus);
        }

    
}
