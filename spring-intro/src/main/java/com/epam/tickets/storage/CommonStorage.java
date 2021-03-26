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
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CommonStorage {

  static final long START_INDEX = 100;
  static final String USER_KEY = "user:";
  static final String EVENT_KEY = "event:";
  static final String TICKET_KEY = "ticket:";
  private final AtomicLong userCounter = new AtomicLong(START_INDEX - 1);
  private final AtomicLong eventCounter = new AtomicLong(START_INDEX - 1);
  private final AtomicLong ticketCounter = new AtomicLong(START_INDEX - 1);
  private String initialStorageFilePath;

  private static final Logger logger = LogManager.getLogger(CommonStorage.class);

  private Map<String, Object> storage = new HashMap<>();

  // Ticket

  public Ticket addTicket(Ticket ticket) {
    Long id = ticketCounter.incrementAndGet();
    ticket.setId(id);
    String key = getKey(EVENT_KEY, id);
    storage.put(key, ticket);
    return (Ticket) storage.get(key);
  }

  public boolean deleteTicket(Long id) throws InvalidTicketException {
    String key = getKey(EVENT_KEY, id);
    if (!storage.containsKey(key)) {
      String msg = "Can't cancel (delete) ticket with id:" + id + " because it doesn't exist";
      logger.error(msg);
      throw new InvalidTicketException(msg);
    }
    storage.remove(key);
    return true;
  }

  public List<Ticket> getAllTickets() {
    return storage.entrySet().stream()
        .filter(entry -> entry.getKey().startsWith(TICKET_KEY))
        .map(entry -> (Ticket) entry.getValue())
        .collect(Collectors.toList());
  }

  // Event

  public Event getEvent(Long id) {
    String key = getKey(EVENT_KEY, id);
    return (Event) storage.get(key);
  }

  public List<Event> getAllEvents() {
    return storage.entrySet().stream()
        .filter(entry -> entry.getKey().startsWith(EVENT_KEY))
        .map(entry -> (Event) entry.getValue())
        .collect(Collectors.toList());
  }

  public Event addEvent(Event event) {
    Long id = eventCounter.incrementAndGet();
    event.setId(id);
    String key = getKey(EVENT_KEY, id);
    storage.put(key, event);
    return (Event) storage.get(key);
  }

  public Event updateEvent(Event event) throws InvalidEventException {
    Long id = event.getId();
    String key = getKey(EVENT_KEY, id);
    if (!storage.containsKey(key)) {
      String msg = "Cannot update event with id: %d, event doesn't exist.";
      logger.error(msg);
      throw new InvalidEventException(msg);
    }
    storage.put(key, event);
    return (Event) storage.get(key);
  }

  public boolean deleteEvent(Long id) {
    String key = getKey(EVENT_KEY, id);
    if (storage.containsKey(key)) {
      storage.remove(key);
      return true;
    } else {
      String msg = "Can't remove event with id:" + id + " because it doesn't exist.";
      logger.error(msg);
      return false;
    }
  }

  // User

  public User addUser(User user) throws InvalidUserException {
    Long id = userCounter.incrementAndGet();
    user.setId(id);

    boolean emailExists = storage.entrySet().stream()
        .filter(entry -> entry.getKey().startsWith(USER_KEY))
        .anyMatch(
            entry -> (user.getEmail()).equalsIgnoreCase(((User) entry.getValue()).getEmail()));

    if (emailExists) {
      String msg = "User with the same email already exists.";
      logger.error(msg);
      throw new InvalidUserException(msg);
    }

    String key = getKey(USER_KEY, id);
    storage.put(key, user);
    return (User) storage.get(key);
  }

  public User update(User user) throws InvalidUserException {
    Long id = user.getId();
    String key = getKey(USER_KEY, id);
    if (!storage.containsKey(key)) {
      String msg = "Cannot update user with id: %d, user doesn't exist.";
      logger.error(msg);
      throw new InvalidUserException(msg);
    }
    return (User) storage.put(USER_KEY + id, user);
  }

  public User getUserById(Long id) throws InvalidUserException {
    String key = getKey(USER_KEY, id);
    if (!storage.containsKey(key)) {
      String msg = "Cannot retrieve the  user with id: %d, user doesn't exist.";
      logger.error(msg);
      throw new InvalidUserException(msg);
    }
    return (User) storage.get(USER_KEY + id);
  }

  public List<User> getAllUsers() {
    return storage.entrySet().stream()
        .filter(entry -> entry.getKey().startsWith(USER_KEY))
        .map(entry -> (User) entry.getValue())
        .collect(Collectors.toList());
  }

  public boolean removeUser(Long id) throws InvalidUserException {
    String key = getKey(USER_KEY, id);
    if (!storage.containsKey(key)) {
      String msg = "Cannot delete user with id: %d, user doesn't exist.";
      logger.error(msg);
      throw new InvalidUserException(msg);
    }
    User removedUser = (User) storage.remove(USER_KEY + id);
    return Objects.nonNull(removedUser);
  }

  // Service methods

  private String getKey(String prefix, Long id) {
    return prefix + id;
  }

  public void setInitialStorageFilePath(String initialStorageFilePath) {
    this.initialStorageFilePath = initialStorageFilePath;
  }

  public void initStorage() {
    storage = StorageHelper.readInitialStorage(initialStorageFilePath);
  }
}
