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
  public Event getEventById(Long id) throws InvalidEventException {
    return commonStorage.getEventById(id);
  }

  @Override
  public void updateEvent(Event event) throws InvalidEventException {
    commonStorage.updateEvent(event);
  }

  @Override
  public List<Event> getAllEvents() {
    return commonStorage.getAllEvents();
  }

  @Override
  public void deleteEventById(Long id) {
    commonStorage.deleteEventById(id);
  }
}
