package com.epam.tickets.services;

import com.epam.tickets.model.Event;
import com.epam.tickets.model.dto.EventDto;
import com.epam.tickets.model.mappers.EventMapper;
import com.epam.tickets.repositories.EventsRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventServiceImpl implements EventService {

  @Autowired
  EventsRepository eventsRepository;

  @Override
  public EventDto createEvent(String title, LocalDateTime date) {
    EventDto eventDto = new EventDto(title, date);
    Event event = EventMapper.INSTANCE.eventDtoToEvent(eventDto);
    event = eventsRepository.save(event);
    return EventMapper.INSTANCE.eventToEventDto(event);
  }

  @Override
  public EventDto getEventById(Long id) {
    Event event = eventsRepository.findById(id).orElse(null);
    return EventMapper.INSTANCE.eventToEventDto(event);
  }

  @Override
  public List<EventDto> getEventsByTitle(String title) {
    List<Event> eventList = eventsRepository.getEventsByTitle(title);
    return EventMapper.INSTANCE.eventListToEventDtoList(eventList);
  }

  @Override
  public List<EventDto> getEventsForDay(LocalDateTime date) {
    List<Event> eventList = eventsRepository.getEventsByDate(date);
    return EventMapper.INSTANCE.eventListToEventDtoList(eventList);
  }

  @Override
  public List<EventDto> getAllEvents() {
    Iterable<Event> allEvents = eventsRepository.findAll();
    List<Event> eventList = StreamSupport.stream(allEvents.spliterator(), false)
        .collect(Collectors.toList());
    return EventMapper.INSTANCE.eventListToEventDtoList(eventList);
  }

  @Override
  public void updateEvent(EventDto eventDto) {
    Event event = EventMapper.INSTANCE.eventDtoToEvent(eventDto);
    eventsRepository.save(event);
  }

  @Override
  public void delete(EventDto eventDto) {
    Event event = EventMapper.INSTANCE.eventDtoToEvent(eventDto);
    eventsRepository.delete(event);
  }
}
