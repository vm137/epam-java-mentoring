package com.epam.tickets.model.dto;

public class UserImpl implements User {

  private Long id;
  private String name;
  private String email;

  public UserImpl() {
  }

  public UserImpl(String name, String email) {
    this.name = name;
    this.email = email;
  }

  public UserImpl(Long id, String name, String email) {
    this.id = id;
    this.name = name;
    this.email = email;
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
  public String getName() {
    return name;
  }

  @Override
  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String getEmail() {
    return email;
  }

  @Override
  public void setEmail(String email) {
    this.email = email;
  }

  @Override
  public String toString() {
    return "User: { " +
        "id=" + id +
        ", name='" + name + '\'' +
        ", email='" + email + '\'' + " }";
  }
}
