package com.epam.tickets.exceptions;

public class InvalidTicketException extends Exception {
  public InvalidTicketException(String errorMessage) {
    super(errorMessage);
  }
}
