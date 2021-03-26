package com.epam.tickets.model.dto;

import com.epam.tickets.model.TicketInterface;

public class Ticket implements TicketInterface {
  private Long id;
  private Long eventId;
  private Long userId;
  private Category category;
  private int place;

  public Ticket() {
  }

  public Ticket(Long id, Long eventId, Long userId, Category category, int place) {
    this.id = id;
    this.eventId = eventId;
    this.userId = userId;
    this.category = category;
    this.place = place;
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
  public Long getEventId() {
    return eventId;
  }

  @Override
  public void setEventId(Long eventId) {
    this.eventId = eventId;
  }

  @Override
  public Long getUserId() {
    return userId;
  }

  @Override
  public void setUserId(Long userId) {
    this.userId = userId;
  }

  @Override
  public Category getCategory() {
    return category;
  }

  @Override
  public void setCategory(Category category) {
    this.category = category;
  }

  @Override
  public int getPlace() {
    return place;
  }

  @Override
  public void setPlace(int place) {
    this.place = place;
  }

  @Override
  public String toString() {
    return "Ticket: { " +
        "id=" + id +
        ", eventId='" + eventId + '\'' +
        ", userId='" + userId + '\'' +
        ", category='" + category + '\'' +
        ", place='" + place + '\'' +
        " }";
  }
}
