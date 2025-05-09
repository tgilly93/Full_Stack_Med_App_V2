package com.techelevator.dao;

import java.time.LocalDate;
import java.util.List;

import com.techelevator.dto.DailyAgendaDto;
import com.techelevator.dto.MonthlyAgendaDto;
import com.techelevator.dto.WeeklyAgendaDto;

public interface AgendaDao {

    List<DailyAgendaDto> getDailyAgenda();

    List<DailyAgendaDto> getDailyAgendaByDate(LocalDate date);

    List<WeeklyAgendaDto> getWeeklyAgenda();

    List<MonthlyAgendaDto> getMonthlyAgenda();

}
