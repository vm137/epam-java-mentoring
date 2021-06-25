package com.epam.tickets.model;

import java.time.LocalDateTime;

public interface Event {

  Long getId();

  void setId(Long id);

  String getTitle();

  void setTitle(String title);

  LocalDateTime getDate();

  void setDate(LocalDateTime date);

  String toString();
}
