package com.epam.tickets.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event {

  private Long id;
  private String title;
  private LocalDateTime date;

  public Event(String title, LocalDateTime date) {
    this.title = title;
    this.date = date;
  }

  public Event(Long id, String title, LocalDateTime date) {
    this.id = id;
    this.title = title;
    this.date = date;
  }

  public Event(Long id, String title, String dateText) {
    this.id = id;
    this.title = title;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    this.date = LocalDateTime.parse(dateText, formatter);
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public LocalDateTime getDate() {
    return date;
  }

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
