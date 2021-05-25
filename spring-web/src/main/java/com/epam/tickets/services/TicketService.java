package com.epam.tickets.services;

import com.epam.tickets.exceptions.InvalidTicketException;
import com.epam.tickets.model.Event;
import com.epam.tickets.model.Ticket;
import com.epam.tickets.model.Ticket.Category;
import com.epam.tickets.model.User;
import java.util.List;

public interface TicketService {

  Ticket bookTicket(Long userId, Long eventId, int place, Category category);

  List<Ticket> getBookedTickets(User user, int pageSize, int pageNum);

  List<Ticket> getBookedTickets(Event event, int pageSize, int pageNum);

  List<Ticket> getAllTickets();

  void cancelTicket(Long ticketId) throws InvalidTicketException;
}
