package com.epam.tickets.model.dto;

public interface Ticket {
  enum Category { STANDARD, PREMIUM, BAR }

  Long getId();

  void setId(Long id);

  Long getEventId();

  void setEventId(Long eventId);

  Long getUserId();

  void setUserId(Long userId);

  Category getCategory();

  void setCategory(Category category);

  int getPlace();

  void setPlace(int place);

  String toString();
}
