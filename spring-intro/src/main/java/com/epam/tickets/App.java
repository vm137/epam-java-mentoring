package com.epam.tickets;

import com.epam.tickets.facade.BookingFacadeImpl;
import com.epam.tickets.model.dto.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

  public static void main(String[] args) {

    ApplicationContext ctx = new ClassPathXmlApplicationContext("context.xml");
    BookingFacadeImpl facade = (BookingFacadeImpl) ctx.getBean("facade");

    System.out.println("Ticket service.");

    User user = new User("Alex", "al@gmail.com");
    User user_ = facade.createUser(user);
    System.out.println(user_.toString());
  }
}
