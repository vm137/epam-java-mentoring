package com.epam.tickets.exceptions;

public class InvalidUserException extends Exception {

  public InvalidUserException(String errorMessage) {
    super(errorMessage);
  }
}
