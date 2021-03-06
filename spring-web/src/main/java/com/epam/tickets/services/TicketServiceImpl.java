package com.epam.tickets.services;

import com.epam.tickets.exceptions.InvalidTicketException;
import com.epam.tickets.model.Event;
import com.epam.tickets.model.Ticket;
import com.epam.tickets.model.Ticket.Category;
import com.epam.tickets.model.User;
import com.epam.tickets.model.dao.TicketDao;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class TicketServiceImpl implements TicketService {

  private TicketDao ticketDao;

  public Ticket bookTicket(Long userId, Long eventId, int place, Category category) {
    Ticket ticket = new Ticket(userId, eventId, place, category);
    return ticketDao.addTicket(ticket);
  }

  public List<Ticket> getBookedTickets(User user, int pageSize, int pageNum) {
    List<Ticket> allTickets = ticketDao.getAllTickets();
    Long lookedUserId = user.getId();
    return allTickets.stream()
        .filter(ticket -> Objects.nonNull(ticket.getUserId()))
        .filter(ticket -> ticket.getUserId().equals(lookedUserId))
        .skip((long) pageSize * pageNum)
        .limit(pageSize)
        .collect(Collectors.toList());
  }

  public List<Ticket> getBookedTickets(Event event, int pageSize, int pageNum) {
    List<Ticket> allTickets = ticketDao.getAllTickets();
    Long lookedEventId = event.getId();
    return allTickets.stream()
        .filter(ticket -> Objects.nonNull(ticket.getEventId()))
        .filter(ticket -> ticket.getEventId().equals(lookedEventId))
        .skip((long) pageSize * pageNum)
        .limit(pageSize)
        .collect(Collectors.toList());
  }

  @Override
  public List<Ticket> getAllTickets() {
    return ticketDao.getAllTickets();
  }

  @Override
  public void cancelTicket(Long ticketId) throws InvalidTicketException {
    ticketDao.deleteTicketById(ticketId);
  }

  public void setTicketDao(TicketDao ticketDao) {
    this.ticketDao = ticketDao;
  }
}
