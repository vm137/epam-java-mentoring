package com.epam.tickets;

import com.epam.tickets.facade.BookingFacadeImpl;
import com.epam.tickets.model.TicketInterface.Category;
import com.epam.tickets.model.dto.Event;
import com.epam.tickets.model.dto.Ticket;
import com.epam.tickets.model.dto.User;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

  private static final Logger logger = LogManager.getLogger(App.class);

  public static void main(String[] args) {

    ApplicationContext ctx = new ClassPathXmlApplicationContext("context.xml");
    BookingFacadeImpl facade = (BookingFacadeImpl) ctx.getBean("facade");

    logger.warn("== Ticket service started ==");

    User user = new User("Alex", "alex@gmail.com");
    User createdUser = facade.createUser(user);
    logger.info(createdUser);

    User userByEmail = facade.getUserByEmail("jhon@gmail.com");
    String userEmail = userByEmail.toString();
    logger.info(userEmail);

    DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    Date date1 = null;
    try {
      date1 = formatter.parse("12/02/2020 12:00");
    } catch (ParseException e) { e.printStackTrace(); }
    Event event1 = new Event("Poetry Week", date1);
    Event createdEvent = facade.createEvent(event1);
    logger.info(createdEvent);

    Event event2 = facade.getEventById(1L);
    logger.info(event2);

    List<Event> events3 = facade.getEventsByTitle("week", 10, 0);
    logger.info(events3);

    Ticket ticket1 = new Ticket(1L, 2L, 3L, Category.PREMIUM, 10);
    logger.info(ticket1);
  }
}
