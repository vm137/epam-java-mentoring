package com.epam.tickets.services;

import com.epam.tickets.model.Event;
import com.epam.tickets.model.Ticket;
import com.epam.tickets.model.Ticket.Category;
import com.epam.tickets.model.User;
import com.epam.tickets.repositories.TicketsRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketServiceImpl implements TicketService {

  @Autowired
  TicketsRepository ticketsRepository;

  @Autowired
  UserAccountService userAccountService;

  @Override
  public Ticket bookTicket(Long eventId, Long userId, int place, Category category, int price) {
    Ticket ticket = new Ticket(eventId, userId, place, category);
    userAccountService.withdraw(userId, price);
    return ticketsRepository.save(ticket);
  }

  @Override
  public Ticket getTicketById(Long id) {
    Optional<Ticket> ticket = ticketsRepository.findById(id);
    return ticket.orElse(null);
  }

  @Override
  public List<Ticket> getBookedTickets(User user) {
    return ticketsRepository.findByUserId(user.getId());
  }

  @Override  public List<Ticket> getBookedTickets(Event event) {
    return ticketsRepository.findByEventId(event.getId());
  }

  @Override
  public List<Ticket> getAllTickets() {
    Iterable<Ticket> allTickets = ticketsRepository.findAll();
    return StreamSupport.stream(allTickets.spliterator(), false)
        .collect(Collectors.toList());
  }

  @Override
  public void cancelTicket(Long ticketId) {
    ticketsRepository.findById(ticketId).ifPresent(ticket -> ticketsRepository.delete(ticket));
  }
}
