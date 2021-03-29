package com.epam.tickets.services;

import com.epam.tickets.model.dto.Event;
import java.util.Date;
import java.util.List;

public interface EventService {

  List<Event> getEventsByTitle(String title, int pageSize, int pageNum);

  List<Event> getEventsForDay(Date day, int pageSize, int pageNum);
}
