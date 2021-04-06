package com.epam.tickets.facade;

import com.epam.tickets.exceptions.InvalidEventException;
import com.epam.tickets.exceptions.InvalidTicketException;
import com.epam.tickets.exceptions.InvalidUserException;
import com.epam.tickets.model.dao.EventDao;
import com.epam.tickets.model.dao.TicketDao;
import com.epam.tickets.model.dao.UserDao;
import com.epam.tickets.model.dto.Event;
import com.epam.tickets.model.dto.Ticket;
import com.epam.tickets.model.dto.TicketI.Category;
import com.epam.tickets.model.dto.User;
import com.epam.tickets.services.EventService;
import com.epam.tickets.services.TicketService;
import com.epam.tickets.services.UserService;
import java.time.LocalDateTime;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BookingFacadeImpl implements BookingFacade {

  private static final Logger logger = LogManager.getLogger(BookingFacadeImpl.class);
  private UserDao userDao;
  private EventDao eventDao;
  private TicketDao ticketDao;
  private UserService userService;
  private EventService eventService;
  private TicketService ticketService;

  public BookingFacadeImpl() {
  }

  public BookingFacadeImpl(
      UserDao userDao,
      EventDao eventDao,
      TicketDao ticketDao,
      UserService userService,
      EventService eventService,
      TicketService ticketService) {
    this.userDao = userDao;
    this.eventDao = eventDao;
    this.ticketDao = ticketDao;
    this.userService = userService;
    this.eventService = eventService;
    this.ticketService = ticketService;
  }

  // Users

  @Override
  public User createUser(User user) throws InvalidUserException {
    String msg = String.format("Create user: %s", user.getName());
    logger.debug(msg);
    return userDao.createUser(user);
  }

  @Override
  public User getUserById(Long userId) throws InvalidUserException {
    return userDao.getUserById(userId);
  }

  @Override
  public User getUserByEmail(String email) throws InvalidUserException {
    return userService.getUserByEmail(email);
  }

  @Override
  public List<User> getUsersByName(String name, int pageSize, int pageNum) {
    return userService.getUsersByName(name, pageSize, pageNum);
  }

  @Override
  public void updateUser(User user) throws InvalidUserException {
    userDao.updateUser(user);
  }

  @Override
  public void deleteUserById(Long userId) throws InvalidUserException {
    userDao.deleteUserById(userId);
  }

  // Events

  @Override
  public Event getEventById(Long eventId) {
    return eventDao.getEventById(eventId);
  }

  @Override
  public List<Event> getEventsByTitle(String title, int pageSize, int pageNum) {
    return eventService.getEventsByTitle(title, pageSize, pageNum);
  }

  @Override
  public List<Event> getEventsForDay(LocalDateTime day, int pageSize, int pageNum) {
    return eventService.getEventsForDay(day, pageSize, pageNum);
  }

  @Override
  public Event createEvent(Event event) {
    return eventDao.createEvent(event);
  }

  @Override
  public void updateEvent(Event event) throws InvalidEventException {
    eventDao.updateEvent(event);
  }

  @Override
  public void deleteEventById(Long eventId) {
    eventDao.deleteEventById(eventId);
  }

  @Override
  public List<Event> getAllEvents() {
    return eventService.getAllEvents();
  }

  // Tickets

  @Override
  public Ticket bookTicket(Long userId, Long eventId, int place, Category category) {
    return ticketService.bookTicket(userId, eventId, place, category);
  }

  @Override
  public List<Ticket> getBookedTickets(User user, int pageSize, int pageNum) {
    return ticketService.getBookedTickets(user, pageSize, pageNum);
  }

  @Override
  public List<Ticket> getBookedTickets(Event event, int pageSize, int pageNum) {
    return ticketService.getBookedTickets(event, pageSize, pageNum);
  }

  @Override
  public void cancelTicket(Long ticketId) throws InvalidTicketException {
    ticketDao.deleteTicketById(ticketId);
  }
}
