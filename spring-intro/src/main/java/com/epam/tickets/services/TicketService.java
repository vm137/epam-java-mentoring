package com.epam.tickets.services;

import com.epam.tickets.model.TicketInterface.Category;
import com.epam.tickets.model.dao.UserDao;
import com.epam.tickets.model.dto.Ticket;
import com.epam.tickets.storage.CommonStorage;

public class TicketService {

  private CommonStorage commonStorage;
  private UserDao userDao;


  public Ticket bookTicket(Long userId, Long eventId, int place, Category category) {
    return null;
  }

  public void setCommonStorage(CommonStorage commonStorage) {
    this.commonStorage = commonStorage;
  }

  public void setUserDao(UserDao userDao) {
    this.userDao = userDao;
  }
}
