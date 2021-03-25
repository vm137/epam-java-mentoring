package com.epam.tickets.model.dao;

import com.epam.tickets.exceptions.InvalidUserException;
import com.epam.tickets.model.dto.User;
import com.epam.tickets.storage.CommonStorage;
import java.util.List;

public class UserDao {

  private final CommonStorage commonStorage;

  public UserDao(CommonStorage commonStorage) {
    this.commonStorage = commonStorage;
  }

  // CRUD methods

  public User create(User user) {
    try {
      return commonStorage.addUser(user);
    } catch (InvalidUserException e) {
      e.printStackTrace();
    }
    return null;
  }

  public User read(Long id) {
    try {
      return commonStorage.getUserById(id);
    } catch (InvalidUserException e) {
      e.printStackTrace();
    }
    return null;
  }

  public List<User> getAllUsers() {
    return commonStorage.getAllUsers();
  }

  public User update(User user) {
    try {
      return commonStorage.update(user);
    } catch (InvalidUserException e) {
      e.printStackTrace();
    }
    return null;
  }

  public boolean delete(Long id) {
    try {
      return commonStorage.removeUser(id);
    } catch (InvalidUserException e) {
      e.printStackTrace();
    }
    return false;
  }
}
