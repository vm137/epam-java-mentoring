package com.epam.tickets.integration;

import static org.junit.Assert.assertEquals;

import com.epam.tickets.exceptions.InvalidEventException;
import com.epam.tickets.facade.BookingFacadeImpl;
import com.epam.tickets.model.dto.Event;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class EventsTest {

  BookingFacadeImpl facade;

  @Before
  public void shouldAnswerWithTrue() {
    ApplicationContext ctx = new ClassPathXmlApplicationContext("test-context.xml");
    facade = (BookingFacadeImpl) ctx.getBean("facade");
  }

  @Test
  public void createNewEventTest() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    LocalDateTime date = LocalDateTime.parse("12/02/2020 12:00", formatter);
    Event event = new Event("Poetry Week", date);
    Event createdEvent = facade.createEvent(event);

    assertEquals("Poetry Week", createdEvent.getTitle());
    assertEquals(date, createdEvent.getDate());
  }

  @Test
  public void getEventByIdTest() throws InvalidEventException {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    LocalDateTime date = LocalDateTime.parse("01/04/2021 19:00", formatter);
    Event event = new Event(1L, "Fashion Week", date);
    Event createdEvent = facade.createEvent(event);
    Long createdEventId = createdEvent.getId();
    Event eventById = facade.getEventById(createdEventId);

    assertEquals("Fashion Week", eventById.getTitle());
    assertEquals(eventById.getDate(), date);
  }
}
