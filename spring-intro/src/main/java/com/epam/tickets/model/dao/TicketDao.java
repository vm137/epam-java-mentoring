package com.epam.tickets.model.dao;

import com.epam.tickets.storage.CommonStorage;

public class TicketDao {

  private final CommonStorage commonStorage;

  public TicketDao(CommonStorage commonStorage) {
    this.commonStorage = commonStorage;
  }


}
