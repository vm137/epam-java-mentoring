package com.epam.tickets.model.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class EventDto {

  private Long id;
  private String title;
  private Integer ticketPrice;
  private LocalDateTime date;

  public EventDto() {}

  public EventDto(String title, LocalDateTime date) {
    this.title = title;
    this.date = date;
  }

  public EventDto(Long id, String title, LocalDateTime date) {
    this.id = id;
    this.title = title;
    this.date = date;
  }

  public EventDto(Long id, String title, String dateText) {
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

  public Integer getTicketPrice() {
    return ticketPrice;
  }

  public void setTicketPrice(Integer ticketPrice) {
    this.ticketPrice = ticketPrice;
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
  public int hashCode() {
    return Objects.hash(title, ticketPrice, date);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EventDto eventDto = (EventDto) o;
    return Objects.equals(title, eventDto.title) && Objects
        .equals(ticketPrice, eventDto.ticketPrice) && Objects.equals(date, eventDto.date);
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
