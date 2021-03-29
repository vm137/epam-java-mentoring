package com.epam.tickets.services;

import com.epam.tickets.model.dao.EventDao;
import com.epam.tickets.model.dto.Event;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class EventServiceImpl implements EventService {
  private EventDao eventDao;

  public List<Event> getEventsByTitle(String title, int pageSize, int pageNum) {
    List<Event> allEvents = eventDao.getAllEvents();
    return allEvents.stream()
        .filter(event -> title.equalsIgnoreCase(event.getTitle()))
        .skip((long) pageSize * pageNum)
        .limit(pageSize)
        .collect(Collectors.toList());
  }

  public List<Event> getEventsForDay(LocalDateTime day, int pageSize, int pageNum) {
    List<Event> allEvents = eventDao.getAllEvents();
    return allEvents.stream()
        .filter(event -> day.equals(event.getDate()))
        .skip((long) pageSize * pageNum)
        .limit(pageSize)
        .collect(Collectors.toList());
  }

  public void setEventDao(EventDao eventDao) {
    this.eventDao = eventDao;
  }
}
