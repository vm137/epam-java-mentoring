package com.epam.tickets.model.dao;

import com.epam.tickets.exceptions.InvalidUserException;
import com.epam.tickets.model.dto.User;
import com.epam.tickets.storage.CommonStorage;

public class UserDao {

  public UserDao(CommonStorage storage) {
    this.storage = storage;
  }

  CommonStorage storage;

  // CRUD methods
  public User create(User user) {
    try {
      return storage.addUser(user);
    } catch (InvalidUserException e) {
      e.printStackTrace();
    }
    return null;
  }

  public User read(Long id) {
    try {
      return storage.getUserById(id);
    } catch (InvalidUserException e) {
      e.printStackTrace();
    }
    return null;
  }

  public User update(User user) {
    try {
      return storage.update(user);
    } catch (InvalidUserException e) {
      e.printStackTrace();
    }
    return null;
  }

  public boolean delete(Long id) {
    try {
      return storage.removeUser(id);
    } catch (InvalidUserException e) {
      e.printStackTrace();
    }
    return false;
  }
}
