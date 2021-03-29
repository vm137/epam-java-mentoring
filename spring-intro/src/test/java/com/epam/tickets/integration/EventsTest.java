package com.epam.tickets.integration;

import static org.junit.Assert.assertEquals;

import com.epam.tickets.facade.BookingFacadeImpl;
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

    assertEquals(true, true);
  }
}
