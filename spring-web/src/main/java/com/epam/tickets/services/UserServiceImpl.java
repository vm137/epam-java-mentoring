package com.epam.tickets.services;

import com.epam.tickets.exceptions.InvalidUserException;
import com.epam.tickets.model.User;
import com.epam.tickets.model.dao.UserDao;
import java.util.List;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {

  private UserDao userDao;

  @Override
  public User createUser(String name, String email) throws InvalidUserException {
    User user = new User(name, email);
    return userDao.createUser(user);
  }

  @Override
  public User getUserById(Long id) throws InvalidUserException {
    return userDao.getUserById(id);
  }

  public User getUserByEmail(String email) throws InvalidUserException {
    List<User> allUsers = userDao.getAllUsers();
    return allUsers.stream()
        .filter(user -> email.equalsIgnoreCase(user.getEmail()))
        .findFirst()
        .orElseThrow(() -> new InvalidUserException("User not found"));
  }

  public List<User> getUsersByName(String name, int pageSize, int pageNum) {
    List<User> allUsers = userDao.getAllUsers();
    return allUsers.stream()
        .filter(user -> name.equalsIgnoreCase(user.getName()))
        .skip((long) pageSize * pageNum)
        .limit(pageSize)
        .collect(Collectors.toList());
  }

  public void setUserDao(UserDao userDao) {
    this.userDao = userDao;
  }
}
