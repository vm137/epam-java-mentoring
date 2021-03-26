package com.epam.tickets.facade;

import com.epam.tickets.exceptions.InvalidUserException;
import com.epam.tickets.model.TicketInterface;
import com.epam.tickets.model.TicketInterface.Category;
import com.epam.tickets.model.dao.EventDao;
import com.epam.tickets.model.dao.UserDao;
import com.epam.tickets.model.dto.Event;
import com.epam.tickets.model.dto.User;
import com.epam.tickets.services.EventService;
import com.epam.tickets.services.UserService;
import java.util.Date;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BookingFacadeImpl implements BookingFacade {

  UserDao userDao;
  EventDao eventDao;

  UserService userService;
  EventService eventService;

  private static final Logger logger = LogManager.getLogger(BookingFacadeImpl.class);

  public BookingFacadeImpl() {
  }

  public BookingFacadeImpl(UserDao userDao, EventDao eventDao,
      UserService userService, EventService eventService) {
    this.userDao = userDao;
    this.eventDao = eventDao;
    this.userService = userService;
    this.eventService = eventService;
  }

  // Users

  @Override
  public User createUser(User user) {
    String msg = String.format("Create user: %s", user.getName());
    logger.debug(msg);
    return userDao.create(user);
  }

  @Override
  public User getUserById(Long userId) {
    return userDao.read(userId);
  }

  @Override
  public User getUserByEmail(String email) {
    try {
      return userService.getUserByEmail(email);
    } catch (InvalidUserException e) {
      String msg = "User with email: " + email + " is not found.";
      logger.warn(msg);
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public List<User> getUsersByName(String name, int pageSize, int pageNum) {
    return userService.getUsersByName(name, pageSize, pageNum);
  }

  @Override
  public User updateUser(User user) {
    return userDao.update(user);
  }

  @Override
  public boolean deleteUser(Long userId) {
    return userDao.delete(userId);
  }

  // Events

  @Override
  public Event getEventById(Long eventId) {
    return null;
  }

  @Override
  public List<Event> getEventsByTitle(String title, int pageSize, int pageNum) {
    return null;
  }

  @Override
  public List<Event> getEventsForDay(Date day, int pageSize, int pageNum) {
    return null;
  }

  @Override
  public Event createEvent(Event event) {
    return eventDao.createEvent(event);
  }

  @Override
  public Event updateEvent(Event event) {
    return eventDao.updateEvent(event);
  }

  @Override
  public boolean deleteEvent(Long eventId) {
    return eventDao.deleteEvent(eventId);
  }

  // Tickets

  @Override
  public TicketInterface bookTicket(Long userId, Long eventId, int place, Category category) {
    return null;
  }

  @Override
  public List<TicketInterface> getBookedTickets(User user, int pageSize, int pageNum) {
    return null;
  }

  @Override
  public List<TicketInterface> getBookedTickets(Event event, int pageSize, int pageNum) {
    return null;
  }

  @Override
  public boolean cancelTicket(Long ticketId) {
    return false;
  }
}
