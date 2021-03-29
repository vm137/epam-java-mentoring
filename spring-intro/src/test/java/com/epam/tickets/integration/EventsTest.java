package com.epam.tickets.integration;

import static org.junit.Assert.assertEquals;

import com.epam.tickets.facade.BookingFacadeImpl;
import com.epam.tickets.model.dto.Event;
import com.epam.tickets.model.dto.EventImpl;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
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
    Event event = new EventImpl("Poetry Week", date);
    Event createdEvent = facade.createEvent(event);

    assertEquals("Poetry Week", createdEvent.getTitle());
    assertEquals(date, createdEvent.getDate());
  }

  @Test
  public void getEventByIdTest() {
    Event eventById = facade.getEventById(1L);

    assertEquals("Fashion Week", eventById.getTitle());

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    LocalDateTime date = LocalDateTime.parse("01/04/2021 19:00", formatter);
    assertEquals(eventById.getDate(), date);
  }

  @Test
  public void getEventsByIdTitle() {
    List<Event> events3 = facade.getEventsByTitle("week", 10, 0);

    assertEquals(1, events3.size());
  }
}
