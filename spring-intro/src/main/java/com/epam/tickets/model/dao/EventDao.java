package com.epam.tickets.model.dao;

import com.epam.tickets.exceptions.InvalidUserException;
import com.epam.tickets.model.dto.Event;
import com.epam.tickets.storage.CommonStorage;

public class EventDao {

  private final CommonStorage commonStorage;

  public EventDao(CommonStorage commonStorage) {
    this.commonStorage = commonStorage;
  }

  public Event createEvent(Event event) {
    return commonStorage.addEvent(event);
  }

  public Event updateEvent(Event event) {
    try {
      return commonStorage.updateEvent(event);
    } catch (InvalidUserException e) {
      e.printStackTrace();
    }
    return null;
  }

  public boolean deleteEvent(Long id) {
    return commonStorage.deleteEvent(id);
  }
}
