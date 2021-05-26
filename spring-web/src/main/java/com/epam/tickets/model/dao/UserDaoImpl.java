package com.epam.tickets.model.dao;

import com.epam.tickets.exceptions.InvalidUserException;
import com.epam.tickets.model.User;
import com.epam.tickets.storage.CommonStorage;
import java.util.List;

public class UserDaoImpl implements UserDao {

  private final CommonStorage commonStorage;

  public UserDaoImpl(CommonStorage commonStorage) {
    this.commonStorage = commonStorage;
  }

  // CRUD methods

  @Override
  public User createUser(User user) throws InvalidUserException {
    return commonStorage.addUser(user);
  }

  @Override
  public User getUserById(Long id) throws InvalidUserException {
    return commonStorage.getUserById(id);
  }

  @Override
  public List<User> getAllUsers() {
    return commonStorage.getAllUsers();
  }

  @Override
  public void updateUser(User user) throws InvalidUserException {
    commonStorage.update(user);
  }

  @Override
  public void deleteUserById(Long id) throws InvalidUserException {
    commonStorage.removeUserById(id);
  }
}
