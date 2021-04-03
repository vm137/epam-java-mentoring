package com.epam.tickets.model.dto;

import java.time.LocalDateTime;

public interface EventI {

  Long getId();

  void setId(Long id);

  String getTitle();

  void setTitle(String title);

  LocalDateTime getDate();

  void setDate(LocalDateTime date);

  String toString();
}
