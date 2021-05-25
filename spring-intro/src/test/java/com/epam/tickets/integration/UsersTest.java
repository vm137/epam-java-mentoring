package com.epam.tickets.integration;

import static org.junit.Assert.assertEquals;

import com.epam.tickets.exceptions.InvalidUserException;
import com.epam.tickets.facade.BookingFacadeImpl;
import com.epam.tickets.model.User;
import com.epam.tickets.model.UserImpl;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UsersTest {

  BookingFacadeImpl facade;

  @Before
  public void shouldAnswerWithTrue() {
    ApplicationContext ctx = new ClassPathXmlApplicationContext("test-context.xml");
    facade = (BookingFacadeImpl) ctx.getBean("facade");
  }

  @Test
  public void createNewUserTest() throws InvalidUserException {
    User user = new UserImpl("Alex", "alex@gmail.com");
    User createdUser = facade.createUser(user);

    assertEquals(createdUser.getName(), "Alex");
    assertEquals(createdUser.getEmail(), "alex@gmail.com");
  }

  @Test
  public void getUserByEmailTest() throws InvalidUserException {
    User userByEmail = facade.getUserByEmail("john@gmail.com");

    assertEquals(userByEmail.getEmail(), "john@gmail.com");
    assertEquals(userByEmail.getName(), "John Foo");
  }
}
