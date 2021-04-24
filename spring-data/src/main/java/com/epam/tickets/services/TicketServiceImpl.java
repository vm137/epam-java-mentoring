package com.epam.tickets.services;

import com.epam.tickets.model.dto.Event;
import com.epam.tickets.model.dto.Ticket;
import com.epam.tickets.model.dto.Ticket.Category;
import com.epam.tickets.model.dto.User;
import com.epam.tickets.repositories.TicketsRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketServiceImpl implements TicketService {

  @Autowired
  TicketsRepository ticketsRepository;

  @Override
  public Ticket bookTicket(Long userId, Long eventId, int place, Category category) {
    return null;
  }

  @Override
  public Ticket getTicketById(Long id) {
    return null;
  }

  @Override
  public List<Ticket> getBookedTickets(User user) {
    return null;
  }

  @Override  public List<Ticket> getBookedTickets(Event event) {
    return null;
  }

  @Override
  public List<Ticket> getAllTickets() {
    return null;
  }

  @Override
  public void cancelTicket(Long ticketId) {

  }
}
