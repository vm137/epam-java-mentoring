package com.epam.tickets.services;

import com.epam.tickets.exceptions.InvalidUserException;
import com.epam.tickets.model.dto.User;
import java.util.List;

public interface UserService {

  User getUserByEmail(String email) throws InvalidUserException;

  List<User> getUsersByName(String name, int pageSize, int pageNum);
}
