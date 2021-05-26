package com.epam.tickets.exceptions;

public class InvalidEventException extends RuntimeException {

  public InvalidEventException(String errorMessage) {
    super(errorMessage);
  }
}
