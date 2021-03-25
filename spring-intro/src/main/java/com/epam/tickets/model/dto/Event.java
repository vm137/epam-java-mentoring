package com.epam.tickets.model.dto;

import com.epam.tickets.model.EventInterface;
import java.util.Date;

public class Event implements EventInterface {

  private Long id;
  private String title;
  private Date date;

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
  public Date getDate() {
    return date;
  }

  @Override
  public void setDate(Date date) {
    this.date = date;
  }
}
