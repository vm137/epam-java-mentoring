package com.epam.tickets.services;

import com.epam.tickets.model.TicketInterface.Category;
import com.epam.tickets.model.dao.TicketDao;
import com.epam.tickets.model.dto.Event;
import com.epam.tickets.model.dto.Ticket;
import com.epam.tickets.model.dto.User;
import java.util.List;
import java.util.stream.Collectors;

public class TicketServiceImpl implements TicketService {

  private TicketDao ticketDao;

  public Ticket createTicket(Long userId, Long eventId, int place, Category category) {
    Ticket ticket = new Ticket(userId, eventId, place, category);
    return ticketDao.addTicket(ticket);
  }

  public List<Ticket> getBookedTickets(User user, int pageSize, int pageNum) {
    List<Ticket> allTickets = ticketDao.getAllTickets();
    Long lookedUserId = user.getId();
    return allTickets.stream()
        .filter(ticket -> ticket.getUserId() == lookedUserId)
        .skip((long) pageSize * pageNum)
        .limit(pageSize)
        .collect(Collectors.toList());
  }

  public List<Ticket> getBookedTickets(Event event, int pageSize, int pageNum) {
    List<Ticket> allTickets = ticketDao.getAllTickets();
    Long lookedEventId = event.getId();
    return allTickets.stream()
        .filter(ticket -> ticket.getEventId().equals(lookedEventId))
        .skip((long) pageSize * pageNum)
        .limit(pageSize)
        .collect(Collectors.toList());
  }

  public void setTicketDao(TicketDao ticketDao) {
    this.ticketDao = ticketDao;
  }
}
