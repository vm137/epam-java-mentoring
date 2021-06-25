package com.epam.tickets.exceptions;

public class UserAccountException extends RuntimeException {

  public UserAccountException(String errorMessage) {
    super(errorMessage);
  }
}
