package com.epam.tickets.services;

import com.epam.tickets.model.dto.EventDto;
import java.time.LocalDateTime;
import java.util.List;

public interface EventService {

  EventDto createEvent(String title, LocalDateTime date);

  EventDto getEventById(Long id);

  List<EventDto> getEventsByTitle(String title);

  List<EventDto> getEventsForDay(LocalDateTime day);

  List<EventDto> getAllEvents();

  void updateEvent(EventDto eventDto);

  void delete(EventDto eventDto);
}
