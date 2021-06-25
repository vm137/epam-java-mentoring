package com.epam.tickets.model.dto;

import com.epam.tickets.model.Ticket.Category;
import java.util.Objects;

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

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TicketDto ticketDto = (TicketDto) o;
    return place == ticketDto.place && price == ticketDto.price && Objects
        .equals(id, ticketDto.id) && Objects.equals(eventId, ticketDto.eventId)
        && Objects.equals(userId, ticketDto.userId) && category == ticketDto.category;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, eventId, userId, place, category, price);
  }

  @Override
  public String toString() {
    return "Ticket: { " +
        "id=" + id +
        ", eventId='" + eventId + '\'' +
        ", userId='" + userId + '\'' +
        ", place='" + place + '\'' +
        ", category='" + category + '\'' +
        ", price='" + price + '\'' +
        " }";
  }
}
