package com.techelevator.controller;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.dao.AgendaDao;
import com.techelevator.model.Agenda;

@RestController
@CrossOrigin
@RequestMapping("/api/agendas")
public class AgendaController {
    @Autowired
    private AgendaDao agendaDao;

    @RequestMapping(path = "/schedule/today/all", method = RequestMethod.GET)
    public List<Agenda> getDailyAgendas(){
        return agendaDao.getDailyAgendas();
    }

    @RequestMapping(path = "/schedule/week", method = RequestMethod.GET)
    public List<Agenda> getAllAgendasByWeek(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        return agendaDao.getAllAgendasByWeek(date);
    }

    @RequestMapping(path = "/schedule/month", method=RequestMethod.GET)
    public List<Agenda> getAllAgendasByMonth(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        return agendaDao.getAllAgendasByMonth(date);
    }
    

    @RequestMapping(path = "/doctor/daily/time/{doctorName}", method = RequestMethod.GET)
    public Agenda getDailyAgendaByDoctorNameTime(@PathVariable String doctorName, @RequestParam @DateTimeFormat(pattern = "HH:mm") LocalTime appointmentStartTime) {
        return agendaDao.getDailyAgendaByDoctorNameTime(doctorName, appointmentStartTime);
    }

    @RequestMapping(path = "/patient/daily/time/{patientName}", method = RequestMethod.GET)
    public Agenda getDailyAgendaByPatientNameTime(@PathVariable String patientName, @RequestParam @DateTimeFormat(pattern = "HH:mm") LocalTime appointmentStartTime) {
        return agendaDao.getDailyAgendaByPatientNameTime(patientName, appointmentStartTime);
    }
    
    @RequestMapping(path = "/patient/daily/all/id/{patientId}", method = RequestMethod.GET)
    public List<Agenda> getDailyAgendaByPatientId(@PathVariable int patientId) {
        return agendaDao.getDailyAgendaByPatientId(patientId);
    }

    @RequestMapping(path = "/doctor/daily/all/{doctorName}", method = RequestMethod.GET)
    public List<Agenda> getDailyAgendaByDoctorName(@PathVariable String doctorName) {
        return agendaDao.getDailyAgendaByDoctorName(doctorName);
    }

    @RequestMapping(path = "/patient/daily/all/name/{patientName}", method = RequestMethod.GET)
    public List<Agenda> getDailyAgendaByPatientName(@PathVariable String patientName) {
        return agendaDao.getDailyAgendaByPatientName(patientName);
    }

    @RequestMapping(path = "/appointment/daily/all/status/{appointmentStatus}", method = RequestMethod.GET)
    public List<Agenda> getDailyAgendaByAppointmentStatus(@PathVariable String appointmentStatus) {
        return agendaDao.getDailyAgendaByAppointmentStatus(appointmentStatus);
    }

    @RequestMapping(path = "/appointment/daily/all/type/{appointmentType}", method = RequestMethod.GET)
    public List<Agenda> getDailyByAppointmentType(@PathVariable String appointmentType) {
        return agendaDao.getDailyByAppointmentType(appointmentType);
    }

    @RequestMapping(path = "/appointment/daily/all/schedule/{scheduleBlock}", method = RequestMethod.GET)
    public List<Agenda> getDailyAgendaByScheduleBlock(@PathVariable int scheduleBlock) {
        return agendaDao.getDailyAgendaByScheduleBlock(scheduleBlock);
    }

    @RequestMapping(path = "/appointment/date/{date}/start/{appointmentStartTime}", method = RequestMethod.GET)
    public Agenda getAgendaByAppointmentStartTime(@PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date, @PathVariable @DateTimeFormat(pattern = "HH:mm") LocalTime appointmentStartTime) {
        return agendaDao.getAgendaByAppointmentStartTime(date, appointmentStartTime);
    }

    @RequestMapping(path = "/appointment/date/{date}/type/{appointmentType}", method = RequestMethod.GET)
    public Agenda getAgendaByAppointmentTypeDateTime(@PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date, @PathVariable String appointmentType, @PathVariable @DateTimeFormat(pattern = "HH:mm") LocalTime appointmentStartTime) {
        return agendaDao.getAgendaByAppointmentTypeDateTime(date, appointmentType, appointmentStartTime );
    }

    @RequestMapping(path = "/appointment/doctor/name/{doctorName}/status/confirmed/{appointmentStatus}", method = RequestMethod.GET)
    public List<Agenda> getAllConfirmedByDoctor(@PathVariable String doctorName, @PathVariable String appointmentStatus) {
        return agendaDao.getAllConfirmedByDoctor(doctorName, appointmentStatus);
    }

    @RequestMapping(path = "/appointment/patient/name/{patientName}/status/confirmed/{appointmentStatus}", method = RequestMethod.GET)
    public List<Agenda> getAllConfirmedByPatient(@PathVariable String patientName, @PathVariable String appointmentStatus) {
        return agendaDao.getAllConfirmedByPatient(patientName, appointmentStatus);
    }

    @RequestMapping(path = "/appointment/patient/id/{patientId}/status/confirmed/{appointmentStatus}", method = RequestMethod.GET)
    public List<Agenda> getAllConfirmedByPatientId(@PathVariable int patientId, @PathVariable String appointmentStatus) {
        return agendaDao.getAllConfirmedByPatientId(patientId, appointmentStatus);
    }

    @RequestMapping(path = "/appointment/doctor/name/{doctorName}/type/urgent/{appointmentType}", method = RequestMethod.GET)
    public List<Agenda> getAllUrgentByDoctor(@PathVariable String doctorName, @PathVariable String appointmentType) {
        return agendaDao.getAllUrgentByDoctor(doctorName, appointmentType);
    }

    @RequestMapping(path = "/appointment/patient/name/{patientName}/type/urgent/{appointmentType}", method = RequestMethod.GET)
    public List<Agenda> getAllUrgentByPatient(@PathVariable String patientName, @PathVariable String appointmentType) {
        return agendaDao.getAllUrgentByPatient(patientName, appointmentType);
    }

    @RequestMapping(path = "/appointment/patient/id/{patientId}/urgent/{appointmentType}", method = RequestMethod.GET)
    public List<Agenda> getAllUrgentByPatientId(@PathVariable int patientId, @PathVariable String appointmentType) {
        return agendaDao.getAllUrgentByPatientId(patientId, appointmentType);
    }

    @RequestMapping(path = "/all", method = RequestMethod.GET)
    public List<Agenda> getAllAgendas() {
        return agendaDao.getAllAgendas();
    }

    @RequestMapping(path = "/agenda/all/doctor/{doctorName}", method = RequestMethod.GET)
    public List<Agenda> getAllAgendasByDoctorName(@PathVariable String doctorName) {
        return agendaDao.getAllAgendasByDoctorName(doctorName);
    }

    @RequestMapping(path = "/agenda/all/patient/name/{patientName}", method = RequestMethod.GET)
    public List<Agenda> getAllAgendasByPatientName(@PathVariable String patientName) {
        return agendaDao.getAllAgendasByPatientName(patientName);
    }

    @RequestMapping(path = "/agenda/all/patient/id/{patientId}", method = RequestMethod.GET)
    public List<Agenda> getAllAgendasByPatientId(@PathVariable int patientId) {
        return agendaDao.getAllAgendasByPatientId(patientId);
    }

    @RequestMapping(path = "/agenda/all/appointment/type/{appointmentType}", method = RequestMethod.GET)
    public List<Agenda> getAllAgendasByAppointmentType(@PathVariable String appointmentType) {
        return agendaDao.getAllAgendasByAppointmentType(appointmentType);
    }

    @RequestMapping(path = "/agenda/all/appointment/status/{appointmentStatus}", method = RequestMethod.GET)
    public List<Agenda> getAllAgendasByAppointmentStatus(@PathVariable String appointmentStatus) {
        return agendaDao.getAllAgendasByAppointmentStatus(appointmentStatus);
    }

    @RequestMapping(path = "/change/agenda", method = RequestMethod.PUT)
    public boolean updateAgenda(@RequestBody Agenda agenda) {
        return agendaDao.updateAgenda(agenda);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/create/agenda", method = RequestMethod.POST)
    public boolean addAgenda(@RequestBody Agenda agenda) {
        return agendaDao.addAgenda(agenda);
    }

    @RequestMapping(path = "/remove/agenda", method = RequestMethod.DELETE)
    public boolean deleteAgenda(@RequestBody Agenda agenda) {
        return agendaDao.deleteAgenda(agenda);
    }

}
