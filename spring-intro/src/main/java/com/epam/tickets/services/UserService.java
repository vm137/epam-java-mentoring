package com.epam.tickets.services;

import com.epam.tickets.model.dao.UserDao;
import com.epam.tickets.model.dto.User;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  UserDao userDao;

  public UserService(UserDao userDao) {
    this.userDao = userDao;
  }

  public List<User> getUsersByName(String name, int pageSize, int pageNum) {
    return null;
  }
}
