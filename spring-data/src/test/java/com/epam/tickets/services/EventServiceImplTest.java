package com.epam.tickets.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.epam.tickets.model.Event;
import com.epam.tickets.model.dto.EventDto;
import com.epam.tickets.repositories.EventsRepository;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class EventServiceImplTest {

  @Autowired
  EventServiceImpl eventService;

  @MockBean
  EventsRepository eventsRepository;

  Event mockEvent;
  EventDto mockEventDto;
  LocalDateTime mockDate;

  @BeforeEach
  void init() {
    String testDate = "01/03/2020 12:00";
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    mockDate = LocalDateTime.parse(testDate, formatter);
    mockEvent = new Event("Title1", mockDate);
    mockEventDto = new EventDto("Title1", mockDate);
  }

  @Test
  public void createEvent() {
    when(eventsRepository.save(any(Event.class))).thenReturn(mockEvent);

    EventDto event = eventService.createEvent("Title1", mockDate);
    assertEquals(event, mockEventDto);
  }

  @Test
  public void getEventById() {
    when(eventsRepository.findById(any(Long.class))).thenReturn(java.util.Optional.ofNullable(mockEvent));

    EventDto event = eventService.getEventById(1L);
    assertEquals(event, mockEventDto);
  }

  @Test
  public void getEventByTitle() {
    List<Event> eventList = new ArrayList<>();
    List<EventDto> eventDtoList = new ArrayList<>();
    when(eventsRepository.getEventsByTitle(any(String.class))).thenReturn(eventList);

    List<EventDto> events = eventService.getEventsByTitle("Title");
    assertEquals(events, eventDtoList);
  }
}
