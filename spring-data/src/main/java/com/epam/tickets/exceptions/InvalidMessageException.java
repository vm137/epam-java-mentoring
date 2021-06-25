package com.epam.tickets.exceptions;

public class InvalidMessageException extends RuntimeException {

  public InvalidMessageException(String errorMessage) {
    super(errorMessage);
  }
}
