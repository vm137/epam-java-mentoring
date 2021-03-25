package com.epam.tickets;

import com.epam.tickets.facade.BookingFacadeImpl;
import com.epam.tickets.model.dto.User;
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
    String userString = createdUser.toString();
    logger.info(userString);
  }
}
