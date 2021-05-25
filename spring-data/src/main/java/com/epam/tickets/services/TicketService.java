package com.epam.tickets.services;

import com.epam.tickets.model.Event;
import com.epam.tickets.model.Ticket;
import com.epam.tickets.model.Ticket.Category;
import com.epam.tickets.model.User;
import java.util.List;

public interface TicketService {

  Ticket bookTicket(Long userId, Long eventId, int place, Category category, int price);

  Ticket getTicketById(Long id);

  List<Ticket> getBookedTickets(User user);

  List<Ticket> getBookedTickets(Event event);

  List<Ticket> getAllTickets();

  void cancelTicket(Long ticketId);
}
