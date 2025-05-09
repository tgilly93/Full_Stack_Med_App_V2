package com.techelevator.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.techelevator.dao.AgendaDao;
import com.techelevator.dto.DailyAgendaDto;
import com.techelevator.dto.MonthlyAgendaDto;
import com.techelevator.dto.WeeklyAgendaDto;

@Service
public class AgendaService {
    private final AgendaDao agendaDao;

    public AgendaService(AgendaDao agendaDao) {
        this.agendaDao = agendaDao;
    }

    public List<DailyAgendaDto> getDailyAgenda() {
        return agendaDao.getDailyAgenda();
    }

    public List<DailyAgendaDto> getDailyAgendaByDate(LocalDate date) {
        return agendaDao.getDailyAgendaByDate(date);
    }

    public List<WeeklyAgendaDto> getWeeklyAgenda() {
        return agendaDao.getWeeklyAgenda();
    }

    public List<MonthlyAgendaDto> getMonthlyAgenda() {
        return agendaDao.getMonthlyAgenda();
    }
}
