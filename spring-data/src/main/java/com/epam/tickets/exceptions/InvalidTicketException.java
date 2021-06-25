package com.epam.tickets.exceptions;

public class InvalidTicketException extends RuntimeException {

  public InvalidTicketException(String errorMessage) {
    super(errorMessage);
  }
}
