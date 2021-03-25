package com.epam.tickets.services;

import com.epam.tickets.model.dao.EventDao;
import com.epam.tickets.storage.CommonStorage;

public class EventService {

  private CommonStorage commonStorage;
  private EventDao eventDao;



  public void setCommonStorage(CommonStorage commonStorage) {
    this.commonStorage = commonStorage;
  }

  public void setEventDao(EventDao eventDao) {
    this.eventDao = eventDao;
  }
}
