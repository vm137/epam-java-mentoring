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

  public User read(long id) {
    return storage.getUserById(id);
  }

  public User update(User user) {
    storage.update(user);
    return null;
  }

  public boolean delete(long id) {
    return storage.removeUser(id);
  }
}
