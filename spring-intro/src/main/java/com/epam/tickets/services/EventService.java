package com.epam.tickets.services;

import com.epam.tickets.model.Event;
import java.time.LocalDateTime;
import java.util.List;

public interface EventService {

  Event createEvent(String title, LocalDateTime date);

  List<Event> getEventsByTitle(String title, int pageSize, int pageNum);

  List<Event> getEventsForDay(LocalDateTime day, int pageSize, int pageNum);
}
