package com.epam.tickets.model.dao;

import com.epam.tickets.exceptions.InvalidEventException;
import com.epam.tickets.model.dto.Event;
import com.epam.tickets.storage.CommonStorage;
import java.util.List;

public class EventDaoImpl implements EventDao {

  private final CommonStorage commonStorage;

  public EventDaoImpl(CommonStorage commonStorage) {
    this.commonStorage = commonStorage;
  }

  @Override
  public Event createEvent(Event event) {
    return commonStorage.addEvent(event);
  }

  @Override
  public Event getEvent(Long id) {
    return commonStorage.getEvent(id);
  }

  @Override
  public Event updateEvent(Event event) {
    try {
      return commonStorage.updateEvent(event);
    } catch (InvalidEventException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public List<Event> getAllEvents() {
    return commonStorage.getAllEvents();
  }

  @Override
  public boolean deleteEvent(Long id) {
    return commonStorage.deleteEvent(id);
  }
}
