package com.techelevator.dao;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import com.techelevator.model.Agenda;

public interface AgendaDao {
    
    Agenda getDailyAgendaByDoctorNameTime(String doctorName, LocalTime appointmentStartTime);
    Agenda getDailyAgendaByPatientNameTime(String patientName, LocalTime appointmentStartTime);
    Agenda getAgendaByDoctorNameDateTime(Date date, String doctorName, LocalTime appointmentStartTime);
    Agenda getAgendaByPatientNameDateTime(Date date, String patientName, LocalTime appointmentStartTime);
    Agenda getAgendaByPatientIdDateTime(int patientId, Date date, LocalTime appointmentStartTime);
    Agenda getAgendaByAppointmentTypeDateTime(Date date, String appointmentType, LocalTime appointmentStartTime);
    Agenda getAgendaByAppointmentStartTime(Date date, LocalTime appointmentStartTime);
    List<Agenda> getDailyAgendaByScheduleBlock(int scheduleBlock);
    List<Agenda> getDailyAgendaByDoctorName(String doctorName);
    List<Agenda> getDailyAgendaByPatientName(String patientName);
    List<Agenda> getDailyAgendaByPatientId(int patientId);
    List<Agenda> getDailyAgendaByAppointmentStatus(String appointmentStatus);
    List<Agenda> getDailyAgendas();
    List<Agenda> getDailyByAppointmentType(String appointmentType);
    List<Agenda> getAllAgendasByWeek(Date date);
    List<Agenda> getAllAgendasByMonth(Date date);
    List<Agenda> getAllConfirmedByDoctor(String doctorName, String appointmentStatus);
    List<Agenda> getAllConfirmedByPatient(String patientName, String appointmentStatus);
    List<Agenda> getAllConfirmedByPatientId(int patientId, String appointmentStatus);
    List<Agenda> getAllUrgentByDoctor(String doctorName, String appointmentType);
    List<Agenda> getAllUrgentByPatient(String patientName, String appointmentType);
    List<Agenda> getAllUrgentByPatientId(int patientId, String appointmentType);
    List<Agenda> getAllAgendas();
    List<Agenda> getAllAgendasByDoctorName(String doctorName);
    List<Agenda> getAllAgendasByPatientName(String patientName);
    List<Agenda> getAllAgendasByPatientId(int patientId);
    List<Agenda> getAllAgendasByAppointmentType(String appointmentType);
    List<Agenda> getAllAgendasByAppointmentStatus(String appointmentStatus);
    boolean updateAgenda(Agenda agenda);
    boolean addAgenda(Agenda agenda);
    boolean deleteAgenda(Agenda agenda);

}
