package com.epam.tickets.model.dto;

import java.util.Objects;

public class UserDto {

  private Long id;
  private String name;
  private String email;

  public UserDto() {}

  public UserDto(String name, String email) {
    this.name = name;
    this.email = email;
  }

  public UserDto(Long id, String name, String email) {
    this.id = id;
    this.name = name;
    this.email = email;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, email);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserDto userDto = (UserDto) o;
    return Objects.equals(id, userDto.id) && Objects.equals(name, userDto.name)
        && Objects.equals(email, userDto.email);
  }

  @Override
  public String toString() {
    return "User: { " +
        "id=" + id +
        ", name='" + name + '\'' +
        ", email='" + email + '\'' + " }";
  }
}
