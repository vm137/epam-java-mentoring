package com.epam.tickets.model.dao;

import com.epam.tickets.exceptions.InvalidEventException;
import com.epam.tickets.model.Event;
import java.util.List;

public interface EventDao {

  Event createEvent(Event event);

  Event getEventById(Long id);

  List<Event> getAllEvents();

  void updateEvent(Event event) throws InvalidEventException;

  void deleteEventById(Long id);
}
