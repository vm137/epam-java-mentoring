package com.epam.tickets.model.dao;

import com.epam.tickets.exceptions.InvalidTicketException;
import com.epam.tickets.model.dto.Ticket;
import com.epam.tickets.storage.CommonStorage;
import java.util.List;

public class TicketDao {

  private final CommonStorage commonStorage;

  public TicketDao(CommonStorage commonStorage) {
    this.commonStorage = commonStorage;
  }

  public List<Ticket> getAllTickets() {
    return commonStorage.getAllTickets();
  }

  public Ticket addTicket(Ticket ticket) {
    return commonStorage.addTicket(ticket);
  }

  public boolean deleteTicket(Long id) {
    try {
      return commonStorage.deleteTicket(id);
    } catch (InvalidTicketException e) {
      e.printStackTrace();
    }
    return false;
  }
}
