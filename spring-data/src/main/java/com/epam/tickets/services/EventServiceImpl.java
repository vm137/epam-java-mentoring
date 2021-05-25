package com.epam.tickets.services;

import com.epam.tickets.model.Event;
import com.epam.tickets.repositories.EventsRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventServiceImpl implements EventService {

  @Autowired
  EventsRepository eventsRepository;

  @Override
  public Event createEvent(String title, LocalDateTime date) {
    Event event = new Event(title, date);
    return eventsRepository.save(event);
  }

  @Override
  public Event getEventById(Long id) {
    Optional<Event> event = eventsRepository.findById(id);
    return event.orElse(null);
  }

  @Override
  public List<Event> getEventsByTitle(String title) {
    return eventsRepository.getEventsByTitle(title);
  }

  @Override
  public List<Event> getEventsForDay(LocalDateTime date) {
    return eventsRepository.getEventsByDate(date);
  }

  @Override
  public List<Event> getAllEvents() {
    Iterable<Event> allEvents = eventsRepository.findAll();
    return StreamSupport.stream(allEvents.spliterator(), false)
        .collect(Collectors.toList());
  }

  @Override
  public void updateEvent(Event event) {
    eventsRepository.save(event);
  }

  @Override
  public void delete(Event event) {
    eventsRepository.delete(event);
  }
}
