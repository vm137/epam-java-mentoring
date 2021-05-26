package com.epam.tickets.model.dto;

import com.epam.tickets.model.Ticket.Category;

public class TicketDto {

  private Long id;
  private Long eventId;
  private Long userId;
  private int place;
  private Category category;
  private int price;

  public TicketDto() {
  }

  public TicketDto(Long eventId, Long userId, int place, Category category) {
    this.eventId = eventId;
    this.userId = userId;
    this.place = place;
    this.category = category;
  }

  public TicketDto(Long id, Long eventId, Long userId, int place, Category category) {
    this.id = id;
    this.eventId = eventId;
    this.userId = userId;
    this.place = place;
    this.category = category;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getEventId() {
    return eventId;
  }

  public void setEventId(Long eventId) {
    this.eventId = eventId;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
    this.category = category;
  }

  public int getPlace() {
    return place;
  }

  public void setPlace(int place) {
    this.place = place;
  }

  @Override
  public String toString() {
    return "Ticket: { " +
        "id=" + id +
        ", eventId='" + eventId + '\'' +
        ", userId='" + userId + '\'' +
        ", place='" + place + '\'' +
        ", category='" + category + '\'' +
        " }";
  }
}
