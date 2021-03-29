package com.epam.tickets.model.dto;

import com.epam.tickets.model.UserInterface;

public interface User extends UserInterface {

  Long getId();

  void setId(Long id);

  String getName();

  void setName(String name);

  String getEmail();

  void setEmail(String email);

  @Override
  String toString();
}
