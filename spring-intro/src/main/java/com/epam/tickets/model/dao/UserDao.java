package com.epam.tickets.model.dao;

import com.epam.tickets.exceptions.InvalidUserException;
import com.epam.tickets.model.dto.User;
import java.util.List;

public interface UserDao {

  User createUser(User user) throws InvalidUserException;

  User getUserById(Long id) throws InvalidUserException;

  List<User> getAllUsers();

  void update(User user) throws InvalidUserException;

  void delete(Long id) throws InvalidUserException;
}
