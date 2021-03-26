package com.epam.tickets.services;

import com.epam.tickets.model.dao.EventDao;
import com.epam.tickets.model.dto.Event;
import com.epam.tickets.storage.CommonStorage;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;

public class EventService {

  private CommonStorage commonStorage;
  private EventDao eventDao;


  public List<Event> getEventsByTitle(String title, int pageSize, int pageNum) {
    List<Event> allEvents = commonStorage.getAllEvents();
    return allEvents.stream()
        .filter(event -> StringUtils.containsIgnoreCase(event.getTitle(), title))
        .skip((long) pageSize * pageNum)
        .limit(pageSize)
        .collect(Collectors.toList());
  }

  public List<Event> getEventsForDay(Date day, int pageSize, int pageNum) {
    List<Event> allEvents = commonStorage.getAllEvents();
    return allEvents.stream()
        .filter(event -> day.compareTo(event.getDate()) == 0)
        .skip((long) pageSize * pageNum)
        .limit(pageSize)
        .collect(Collectors.toList());
  }

  public void setCommonStorage(CommonStorage commonStorage) {
    this.commonStorage = commonStorage;
  }

  public void setEventDao(EventDao eventDao) {
    this.eventDao = eventDao;
  }
}
