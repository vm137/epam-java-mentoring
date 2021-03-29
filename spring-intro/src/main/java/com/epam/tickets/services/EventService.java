package com.epam.tickets.services;

import com.epam.tickets.model.dto.Event;
import java.time.LocalDateTime;
import java.util.List;

public interface EventService {

  List<Event> getEventsByTitle(String title, int pageSize, int pageNum);

  List<Event> getEventsForDay(LocalDateTime day, int pageSize, int pageNum);
}
