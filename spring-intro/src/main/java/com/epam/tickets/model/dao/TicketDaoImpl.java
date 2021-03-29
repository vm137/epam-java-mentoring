package com.epam.tickets.model.dao;

import com.epam.tickets.exceptions.InvalidTicketException;
import com.epam.tickets.model.dto.Ticket;
import com.epam.tickets.storage.CommonStorage;
import java.util.List;

public class TicketDaoImpl implements TicketDao {

  private final CommonStorage commonStorage;

  public TicketDaoImpl(CommonStorage commonStorage) {
    this.commonStorage = commonStorage;
  }

  @Override
  public List<Ticket> getAllTickets() {
    return commonStorage.getAllTickets();
  }

  @Override
  public Ticket addTicket(Ticket ticket) {
    return commonStorage.addTicket(ticket);
  }

  @Override
  public boolean deleteTicket(Long id) {
    try {
      return commonStorage.deleteTicket(id);
    } catch (InvalidTicketException e) {
      e.printStackTrace();
    }
    return false;
  }
}
