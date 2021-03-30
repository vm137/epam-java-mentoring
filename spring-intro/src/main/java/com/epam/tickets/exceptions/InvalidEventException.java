package com.epam.tickets.exceptions;

public class InvalidEventException extends Exception {
  public InvalidEventException(String errorMessage) {
    super(errorMessage);
  }
}
