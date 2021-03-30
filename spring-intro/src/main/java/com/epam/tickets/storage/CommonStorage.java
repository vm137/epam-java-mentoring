package com.epam.tickets.storage;

import com.epam.tickets.exceptions.InvalidEventException;
import com.epam.tickets.exceptions.InvalidTicketException;
import com.epam.tickets.exceptions.InvalidUserException;
import com.epam.tickets.model.dto.Event;
import com.epam.tickets.model.dto.Ticket;
import com.epam.tickets.model.dto.User;
import java.util.List;

public interface CommonStorage {

  String USER_KEY = "user:";
  String EVENT_KEY = "event:";
  String TICKET_KEY = "ticket:";

  User addUser(User user) throws InvalidUserException;

  User getUserById(Long id) throws InvalidUserException;

  List<User> getAllUsers();

  void update(User user) throws InvalidUserException;

  void removeUser(Long id) throws InvalidUserException;

  Event addEvent(Event event);

  Event getEvent(Long id);

  List<Event> getAllEvents();

  void updateEvent(Event event) throws InvalidEventException;

  void deleteEvent(Long id);

  Ticket addTicket(Ticket ticket);

  List<Ticket> getAllTickets();

  void deleteTicket(Long id) throws InvalidTicketException;

  void setInitialStorageFilePath(String initialStorageFilePath);

  void initStorage();
}
