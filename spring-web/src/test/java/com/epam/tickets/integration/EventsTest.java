package com.epam.tickets.integration;

import static org.junit.Assert.assertEquals;

import com.epam.tickets.facade.BookingFacadeImpl;
import com.epam.tickets.model.Event;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class EventsTest {

  @Autowired
  private BookingFacadeImpl facade;

  private static final Logger logger = LogManager.getLogger(EventsTest.class);

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
  public void getEventByIdTest() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    LocalDateTime date = LocalDateTime.parse("01/04/2021 19:00", formatter);
    Event event = new Event(1L, "Fashion Week", date);
    Event createdEvent = facade.createEvent(event);
    Long createdEventId = createdEvent.getId();
    try {
      Event eventById = facade.getEventById(createdEventId);
      assertEquals("Fashion Week", eventById.getTitle());
      assertEquals(eventById.getDate(), date);
    } catch (Exception ex) {
      logger.info("Couldn't get event by id.");
    }
  }
}
