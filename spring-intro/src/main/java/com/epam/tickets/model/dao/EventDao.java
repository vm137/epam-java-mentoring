package com.epam.tickets.model.dao;

import com.epam.tickets.model.dto.Event;
import java.util.List;

public interface EventDao {

  Event createEvent(Event event);

  Event getEvent(Long id);

  Event updateEvent(Event event);

  List<Event> getAllEvents();

  boolean deleteEvent(Long id);
}
