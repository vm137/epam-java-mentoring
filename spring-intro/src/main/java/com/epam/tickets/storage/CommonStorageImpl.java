package com.epam.tickets.storage;

import com.epam.tickets.exceptions.InvalidEventException;
import com.epam.tickets.exceptions.InvalidTicketException;
import com.epam.tickets.exceptions.InvalidUserException;
import com.epam.tickets.model.dto.Event;
import com.epam.tickets.model.dto.Ticket;
import com.epam.tickets.model.dto.User;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CommonStorageImpl implements CommonStorage {

  private final static long START_INDEX = 100;

  private String initialStorageFilePath;
  private final AtomicLong userCounter = new AtomicLong(START_INDEX - 1);
  private final AtomicLong eventCounter = new AtomicLong(START_INDEX - 1);
  private final AtomicLong ticketCounter = new AtomicLong(START_INDEX - 1);

  private static final Logger logger = LogManager.getLogger(CommonStorageImpl.class);

  private Map<String, Object> storage = new HashMap<>();

  // User

  @Override
  public User addUser(User user) throws InvalidUserException {
    Long id = userCounter.incrementAndGet();
    String email = user.getEmail();
    user.setId(id);

    boolean emailExists = storage.entrySet().stream()
        .filter(entry -> entry.getKey().startsWith(USER_KEY))
        .anyMatch(entry -> (email.equalsIgnoreCase(((User) entry.getValue()).getEmail())));

    if (emailExists) {
      String msg = "User with the same email already exists.";
      logger.error(msg);
      throw new InvalidUserException(msg);
    }

    String key = getKey(USER_KEY, id);
    storage.put(key, user);
    return (User) storage.get(key);
  }

  @Override
  public User getUserById(Long id) throws InvalidUserException {
    String key = getKey(USER_KEY, id);
    if (!storage.containsKey(key)) {
      String msg = String.format("Cannot retrieve the  user with id: %d, user doesn't exist.", id);
      logger.error(msg);
      throw new InvalidUserException(msg);
    }
    return (User) storage.get(USER_KEY + id);
  }

  @Override
  public List<User> getAllUsers() {
    return storage.entrySet().stream()
        .filter(entry -> entry.getKey().startsWith(USER_KEY))
        .map(entry -> (User) entry.getValue())
        .collect(Collectors.toList());
  }

  @Override
  public void update(User user) throws InvalidUserException {
    Long id = user.getId();
    String key = getKey(USER_KEY, id);
    if (!storage.containsKey(key)) {
      String msg = String.format("Cannot update user with id: %d, user doesn't exist.", id);
      logger.error(msg);
      throw new InvalidUserException(msg);
    }
    storage.put(USER_KEY + id, user);
  }

  @Override
  public void removeUser(Long id) throws InvalidUserException {
    String key = getKey(USER_KEY, id);
    if (!storage.containsKey(key)) {
      String msg = String.format("Cannot delete user with id: %d, user doesn't exist.", id);
      logger.error(msg);
      throw new InvalidUserException(msg);
    }
    storage.remove(USER_KEY + id);
  }

  // Event

  @Override
  public Event addEvent(Event event) {
    Long id = eventCounter.incrementAndGet();
    event.setId(id);
    String key = getKey(EVENT_KEY, id);
    storage.put(key, event);
    return (Event) storage.get(key);
  }

  @Override
  public Event getEvent(Long id) {
    String key = getKey(EVENT_KEY, id);
    return (Event) storage.get(key);
  }

  @Override
  public List<Event> getAllEvents() {
    return storage.entrySet().stream()
        .filter(entry -> entry.getKey().startsWith(EVENT_KEY))
        .map(entry -> (Event) entry.getValue())
        .collect(Collectors.toList());
  }

  @Override
  public void updateEvent(Event event) throws InvalidEventException {
    Long id = event.getId();
    String key = getKey(EVENT_KEY, id);
    if (!storage.containsKey(key)) {
      String msg = String.format("Cannot update event with id:%d because it doesn't exist.", id);
      logger.error(msg);
      throw new InvalidEventException(msg);
    }
    storage.put(key, event);
  }

  @Override
  public void deleteEvent(Long id) {
    String key = getKey(EVENT_KEY, id);
    if (storage.containsKey(key)) {
      storage.remove(key);
    } else {
      String msg = String.format("Can't remove event with id:%d because it doesn't exist.", id);
      logger.error(msg);
    }
  }

  // Ticket

  @Override
  public Ticket addTicket(Ticket ticket) {
    Long id = ticketCounter.incrementAndGet();
    ticket.setId(id);
    String key = getKey(EVENT_KEY, id);
    storage.put(key, ticket);
    return (Ticket) storage.get(key);
  }

  @Override
  public List<Ticket> getAllTickets() {
    return storage.entrySet().stream()
        .filter(entry -> entry.getKey().startsWith(TICKET_KEY))
        .map(entry -> (Ticket) entry.getValue())
        .collect(Collectors.toList());
  }

  @Override
  public void deleteTicket(Long id) throws InvalidTicketException {
    String key = getKey(EVENT_KEY, id);
    if (!storage.containsKey(key)) {
      String msg = String.format("Can not delete ticket with id:%d because it doesn't exist", id);
      logger.error(msg);
      throw new InvalidTicketException(msg);
    }
    storage.remove(key);
  }

  // Service methods

  private String getKey(String prefix, Long id) {
    return prefix + id;
  }

  @Override
  public void setInitialStorageFilePath(String initialStorageFilePath) {
    this.initialStorageFilePath = initialStorageFilePath;
  }

  @Override
  public void initStorage() {
    storage = StorageHelper.readInitialStorage(initialStorageFilePath);
  }
}
