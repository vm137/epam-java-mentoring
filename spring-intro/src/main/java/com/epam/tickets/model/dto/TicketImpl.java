package com.epam.tickets.model.dto;

public class TicketImpl implements Ticket {

  private Long id;
  private Long eventId;
  private Long userId;
  private int place;
  private Category category;

  public TicketImpl() {
  }

  public TicketImpl(Long eventId, Long userId, int place, Category category) {
    this.eventId = eventId;
    this.userId = userId;
    this.place = place;
    this.category = category;
  }

  public TicketImpl(Long id, Long eventId, Long userId, int place, Category category) {
    this.id = id;
    this.eventId = eventId;
    this.userId = userId;
    this.place = place;
    this.category = category;
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
        ", place='" + place + '\'' +
        ", category='" + category + '\'' +
        " }";
  }
}
