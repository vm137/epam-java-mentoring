package com.epam.tickets.storage;

import com.epam.tickets.exceptions.InvalidEventException;
import com.epam.tickets.exceptions.InvalidTicketException;
import com.epam.tickets.exceptions.InvalidUserException;
import com.epam.tickets.model.dto.Event;
import com.epam.tickets.model.dto.Ticket;
import com.epam.tickets.model.dto.User;
import java.util.List;

public interface CommonStorage {

  User addUser(User user) throws InvalidUserException;

  User getUserById(Long id) throws InvalidUserException;

  List<User> getAllUsers();

  void update(User user) throws InvalidUserException;

  void removeUserById(Long id) throws InvalidUserException;

  Event addEvent(Event event);

  Event getEventById(Long id) throws InvalidEventException;

  List<Event> getAllEvents();

  void updateEvent(Event event) throws InvalidEventException;

  void deleteEventById(Long id);

  Ticket addTicket(Ticket ticket);

  List<Ticket> getAllTickets();

  void deleteTicketById(Long id) throws InvalidTicketException;
}
