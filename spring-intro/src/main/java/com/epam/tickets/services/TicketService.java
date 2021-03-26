package com.epam.tickets.services;

import com.epam.tickets.model.dao.UserDao;
import com.epam.tickets.storage.CommonStorage;

public class TicketService {

  private CommonStorage commonStorage;
  private UserDao userDao;




  public void setCommonStorage(CommonStorage commonStorage) {
    this.commonStorage = commonStorage;
  }

  public void setUserDao(UserDao userDao) {
    this.userDao = userDao;
  }
}
