package com.epam.tickets.services;

import com.epam.tickets.model.TicketInterface.Category;
import com.epam.tickets.model.dao.TicketDao;
import com.epam.tickets.model.dto.Ticket;
import com.epam.tickets.storage.CommonStorage;

public class TicketService {

  private TicketDao ticketDao;
  private CommonStorage commonStorage;

  public Ticket createTicket(Long userId, Long eventId, int place, Category category) {
    Ticket ticket = new Ticket(userId, eventId, place, category);
    return commonStorage.addTicket(ticket);
  }

  public void setCommonStorage(CommonStorage commonStorage) {
    this.commonStorage = commonStorage;
  }

  public void setTicketDao(TicketDao ticketDao) {
    this.ticketDao = ticketDao;
  }
}
