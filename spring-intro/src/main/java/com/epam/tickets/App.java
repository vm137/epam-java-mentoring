package com.epam.tickets;

import static com.epam.tickets.model.dto.Ticket.Category.PREMIUM;

import com.epam.tickets.facade.BookingFacadeImpl;
import com.epam.tickets.model.dto.Event;
import com.epam.tickets.model.dto.EventImpl;
import com.epam.tickets.model.dto.Ticket;
import com.epam.tickets.model.dto.User;
import com.epam.tickets.model.dto.UserImpl;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    logger.info("== Ticket service started ==");

    User user = new UserImpl("Alex", "alex@gmail.com");
    User createdUser = facade.createUser(user);
    logger.info(createdUser);

    User userByEmail = facade.getUserByEmail("jhon@gmail.com");
    String userEmail = userByEmail.toString();
    logger.info(userEmail);

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    LocalDateTime date1 = LocalDateTime.parse("12/02/2020 12:00", formatter);
    Event event1 = new EventImpl("Poetry Week", date1);
    Event createdEvent = facade.createEvent(event1);
    logger.info(createdEvent);

    Event event2 = facade.getEventById(1L);
    logger.info(event2);

    List<Event> events3 = facade.getEventsByTitle("week", 10, 0);
    logger.info(events3);

    Ticket ticket1 = facade.bookTicket(2L, 3L, 10, PREMIUM);
    logger.info(ticket1);
  }
}
