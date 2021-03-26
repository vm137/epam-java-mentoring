package com.epam.tickets.model.dao;

import com.epam.tickets.exceptions.InvalidTicketException;
import com.epam.tickets.storage.CommonStorage;

public class TicketDao {

  private final CommonStorage commonStorage;

  public TicketDao(CommonStorage commonStorage) {
    this.commonStorage = commonStorage;
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
