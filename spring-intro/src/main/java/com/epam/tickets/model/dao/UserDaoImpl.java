package com.epam.tickets.model.dao;

import com.epam.tickets.exceptions.InvalidUserException;
import com.epam.tickets.model.dto.User;
import com.epam.tickets.storage.CommonStorage;
import java.util.List;

public class UserDaoImpl implements UserDao {

  private final CommonStorage commonStorage;

  public UserDaoImpl(CommonStorage commonStorage) {
    this.commonStorage = commonStorage;
  }

  // CRUD methods

  @Override
  public User createUser(User user) {
    try {
      return commonStorage.addUser(user);
    } catch (InvalidUserException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public User getUserById(Long id) {
    try {
      return commonStorage.getUserById(id);
    } catch (InvalidUserException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public List<User> getAllUsers() {
    return commonStorage.getAllUsers();
  }

  @Override
  public void update(User user) throws InvalidUserException {
    commonStorage.update(user);
  }

  @Override
  public void delete(Long id) throws InvalidUserException {
    commonStorage.removeUser(id);
  }
}
