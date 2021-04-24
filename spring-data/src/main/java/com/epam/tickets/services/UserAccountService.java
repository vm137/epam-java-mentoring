package com.epam.tickets.services;

public interface UserAccountService {

  void refill(Long userId, int amount);

  void withdraw(Long userId, int amount);
}
