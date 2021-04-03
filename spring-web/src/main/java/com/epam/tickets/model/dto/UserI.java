package com.epam.tickets.model.dto;

public interface UserI {

  Long getId();

  void setId(Long id);

  String getName();

  void setName(String name);

  String getEmail();

  void setEmail(String email);

  @Override
  String toString();
}
