package com.epam.tickets.model.dao;

import com.epam.tickets.exceptions.InvalidUserException;
import com.epam.tickets.model.dto.User;
import java.util.List;

public interface UserDao {

  User createUser(User user);

  User getUserById(Long id);

  List<User> getAllUsers();

  void update(User user) throws InvalidUserException;

  void delete(Long id) throws InvalidUserException;
}
