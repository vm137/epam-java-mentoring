package com.epam.tickets.exceptions;

public class InvalidUserException extends RuntimeException {

  public InvalidUserException(String errorMessage) {
    super(errorMessage);
  }
}
