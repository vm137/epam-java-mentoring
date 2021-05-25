package com.epam.tickets.services;

import com.epam.tickets.model.User;
import java.util.List;

public interface UserService {

  User createUser(String name, String email);

  User getUserById(Long id);

  List<User> getUsersByName(String name);

  void updateUser(Long id, String name, String email);

  void deleteUserById(Long id);
}
