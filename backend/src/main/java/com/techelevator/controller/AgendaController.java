package com.techelevator.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.dto.DailyAgendaDto;
import com.techelevator.dto.MonthlyAgendaDto;
import com.techelevator.dto.WeeklyAgendaDto;
import com.techelevator.service.AgendaService;

@RestController
@CrossOrigin
@RequestMapping("/api/agenda")
public class AgendaController {
    private final AgendaService agendaService;

    public AgendaController(AgendaService agendaService) {
        this.agendaService = agendaService;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_RECEPTIONIST', 'ROLE_CLINICIAN')")
    @GetMapping("/daily")
    public List<DailyAgendaDto> getDailyAgenda() {
        return agendaService.getDailyAgenda();
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_RECEPTIONIST', 'ROLE_CLINICIAN')")
    @GetMapping("/daily/{date}")
    public List<DailyAgendaDto> getDailyAgendaByDate(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return agendaService.getDailyAgendaByDate(date);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_RECEPTIONIST', 'ROLE_CLINICIAN')")
    @GetMapping("/weekly")
    public List<WeeklyAgendaDto> getWeeklyAgenda() {
        return agendaService.getWeeklyAgenda();
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_RECEPTIONIST', 'ROLE_CLINICIAN')")
    @GetMapping("/monthly")
    public List<MonthlyAgendaDto> getMonthlyAgenda() {
        return agendaService.getMonthlyAgenda();
    }
}
