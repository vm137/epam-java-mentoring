package com.epam.tickets.model.dao;

import com.epam.tickets.model.dto.User;
import java.util.List;

public interface UserDao {

  User create(User user);

  User read(Long id);

  List<User> getAllUsers();

  User update(User user);

  boolean delete(Long id);
}
