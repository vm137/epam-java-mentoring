package com.epam.tickets.services;

import com.epam.tickets.model.dto.Event;
import com.epam.tickets.repositories.EventsRepository;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class EventServiceImpl implements EventService {

  EventsRepository eventsRepository;

  @Override
  public Event createEvent(String title, LocalDateTime date) {
    return null;
  }

  @Override
  public Event getEventById(Long id) {
    return null;
  }

  @Override
  public List<Event> getEventsByTitle(String title, int pageSize, int pageNum) {
    return null;
  }

  @Override
  public List<Event> getEventsForDay(LocalDateTime day, int pageSize, int pageNum) {
    return null;
  }

  @Override
  public List<Event> getAllEvents() {
    return null;
  }

  @Override
  public void updateEvent(Event event) {

  }

  @Override
  public void delete(Event event) {

  }
}
