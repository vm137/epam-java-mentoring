package com.epam.tickets.model.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EventImpl implements Event {

  private Long id;
  private String title;
  private LocalDateTime date;

  public EventImpl(String title, LocalDateTime date) {
    this.title = title;
    this.date = date;
  }

  public EventImpl(Long id, String title, LocalDateTime date) {
    this.id = id;
    this.title = title;
    this.date = date;
  }

  @Override
  public Long getId() {
    return id;
  }

  @Override
  public void setId(Long id) {
    this.id = id;
  }

  @Override
  public String getTitle() {
    return title;
  }

  @Override
  public void setTitle(String title) {
    this.title = title;
  }

  @Override
  public LocalDateTime getDate() {
    return date;
  }

  @Override
  public void setDate(LocalDateTime date) {
    this.date = date;
  }

  @Override
  public String toString() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    return "Event : { " +
        "id='" + id + '\'' +
        ", title='" + title + '\'' +
        ", date='" + formatter.format(date) +
        "' }";
  }
}
