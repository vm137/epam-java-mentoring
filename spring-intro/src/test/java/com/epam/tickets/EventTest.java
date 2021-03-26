package com.epam.tickets;

import com.epam.tickets.facade.BookingFacadeImpl;
import com.epam.tickets.model.dto.Event;
import java.util.Date;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class EventTest {

  BookingFacadeImpl facade;

  @Before
  public void shouldAnswerWithTrue() {
    ApplicationContext ctx = new ClassPathXmlApplicationContext("context.xml");
    facade = (BookingFacadeImpl) ctx.getBean("facade");
  }

  @Test
  public void createNewEventTest() {
    Event event = new Event("Event 1", new Date());
    Event createdEvent = facade.createEvent(event);

//    assertEquals("Alex", savedUser.getName());
//    assertEquals("al@gmail.com", savedUser.getEmail());
  }

}
