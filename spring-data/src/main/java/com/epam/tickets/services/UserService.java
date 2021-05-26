package com.epam.tickets.services;

import com.epam.tickets.model.dto.UserDto;
import java.util.List;

public interface UserService {

  UserDto createUser(String name, String email);

  UserDto getUserById(Long id);

  List<UserDto> getUsersByName(String name);

  void updateUser(Long id, String name, String email);

  void deleteUserById(Long id);
}
