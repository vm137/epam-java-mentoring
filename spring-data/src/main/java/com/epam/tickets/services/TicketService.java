package com.epam.tickets.services;

import com.epam.tickets.model.Ticket;
import com.epam.tickets.model.Ticket.Category;
import com.epam.tickets.model.User;
import com.epam.tickets.model.dto.EventDto;
import java.util.List;

public interface TicketService {

  Ticket bookTicket(Long userId, Long eventId, int place, Category category, int price);

  Ticket getTicketById(Long id);

  List<Ticket> getBookedTickets(User user);

  List<Ticket> getBookedTickets(EventDto eventDto);

  List<Ticket> getAllTickets();

  void cancelTicket(Long ticketId);
}
